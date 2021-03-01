package gov.usgs.wma.wqp.dao;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gov.usgs.wma.wqp.dao.intfc.ILogDao;

/*
 *   This class accumulates properties of a http request (endpoint, mimeType, json posted,
 * time to first row, etc) in a json object as it goes thru the various stages. The json is logged
 * at the end of the request.
 *
 */
@RequestScope
@Component
public class LogDao implements ILogDao {
	protected static final Logger LOG = LoggerFactory.getLogger(LogDao.class);
	protected static final AtomicInteger counter = new AtomicInteger(1);

	private Map<Object, ObjectNode> requests = new ConcurrentHashMap<>();

	// auto wired, managed by Spring
	private ObjectMapper mapper;

	public static final String WEB_REQUEST_STARTED = "started";
	public static final String WEB_REQUEST_HEAD_COMPLETE = "head complete";
	public static final String WEB_REQUEST_FIRST_ROW = "first row";
	public static final String WEB_REQUEST_COMPLETED = "complete";

	public static final String ID = "id";
	public static final String ORIGIN = "origin";
	public static final String CALL_TYPE = "callType";
	public static final String ENDPOINT = "endpoint";
	public static final String TOTAL_ROWS_EXPECTED = "totalRowsExpected";
	public static final String DATA_STORE_COUNTS = "dataStoreCounts";
	public static final String HTTP_STATUS_CODE = "httpStatusCode";
	public static final String POST_DATA = "postData";
	public static final String USER_AGENT = "userAgent";
	public static final String DOWNLOAD_DETAILS = "downloadDetails";

	@Autowired
	public LogDao(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Integer addLog(Map<String, Object> parameterMap) {
		int id = counter.getAndIncrement(); // first time, create id used to identify request
		Map<String, Object> parameterMapWithId = new HashMap<String, Object>();
		parameterMapWithId.putAll(parameterMap);
		parameterMapWithId.put(ID, id);
		logRequestParams(WEB_REQUEST_STARTED, parameterMapWithId);
		ObjectNode json = getRequestJson(id);
		addTimestamp(json, "started");
		return id;
	}

	@Override
	public void setHeadComplete(Map<String, Object> parameterMap) {
		logRequestParams(WEB_REQUEST_HEAD_COMPLETE, parameterMap);
	}

	@Override
	public void setFirstRow(Map<String, Object> parameterMap) {
		logRequestParams(WEB_REQUEST_FIRST_ROW, parameterMap);
	}

	@Override
	public void setRequestComplete(Map<String, Object> parameterMap) {
		logRequestParams(WEB_REQUEST_COMPLETED, parameterMap);
		removeRequestJson(parameterMap.get(ID));
	}

	// return the json representation of the request parameters, this is what will be logged
	private ObjectNode getRequestJson(Object id) {
		ObjectNode objectNode = requests.get(id);
		if (objectNode == null) {
			requests.put(id, mapper.createObjectNode());
			objectNode = requests.get(id);
		}
		return objectNode;
	}

	// return the json representation of the request parameters
	private void removeRequestJson(Object id) {
		if (id != null) {
			requests.remove(id);
		}
	}

	/*
	 * accumulate request properties as json. log if request is done.
	 */
	private void logRequestParams(String stage, Map<String, Object> parameterMap) {
		String parms = parameterMap == null ? "[parameters null]" : parameterMap.toString();
		if (parameterMap == null || parameterMap.get(ID) == null) {
			LOG.error(String.format("Web Request %s stage received with no id: %s", stage, parms));
		} else {
			addParamsToJson(parameterMap);
			if(WEB_REQUEST_COMPLETED.equals(stage)) {
				ObjectNode json = getRequestJson(parameterMap.get(ID));
				addTimestamp(json, WEB_REQUEST_COMPLETED);
				LOG.info(String.format("Web Request|%s|%s|%s", parameterMap.get(ID), stage, json.toString()));
			}
		}
	}

	private void addParamsToJson(Map<String, Object> parameterMap) {
		ObjectNode json = getRequestJson(parameterMap.get(ID));
		for (String param : parameterMap.keySet()) {
			Object valueObj = parameterMap.get(param);
			if (valueObj == null) {
				json.set(param, NullNode.getInstance());
			} else if (valueObj instanceof String) {
				setJsonStringField(json, param, (String) valueObj);
			} else if (valueObj instanceof Integer) {
				json.put(param, (Integer) valueObj);
			} else {
				String value = String.format("param '%s' type (%s) is unknown : %s", 
						param, valueObj.getClass().getName(), valueObj.toString());
				json.put(param, value);
			}
		}
	}

	private void setJsonStringField(ObjectNode json, String param, String value) {
		// If string is potentially json, try to add as JsonNode
		if (value.trim().startsWith("{")) {
			JsonNode jsonNode = toJson(value);
			if (jsonNode == null) {
				json.put(param, value);
			} else {
				json.set(param, jsonNode);
			}
		} else {
			json.put(param, value);
		}
	}

	private JsonNode toJson(String jsonStr) {
		JsonNode jsonObj;
		try {
			jsonObj = mapper.readTree(jsonStr);
		} catch (JsonProcessingException e) {
			// Not logging error because likely it is a perfectly valid string that just was not json.
			// The raw string will be saved in the json logged
			jsonObj = null;
		}
		return jsonObj;
	}

	private void addTimestamp(ObjectNode json, String param) {
		String timestamp =  DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		json.put(param, timestamp);
	}

}
