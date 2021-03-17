package gov.usgs.wma.wqp.dao;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PreDestroy;

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

	private ObjectNode requestProperies;

	// auto wired, managed by Spring
	private ObjectMapper mapper;

	public static final String WEB_REQUEST_START = "start";
	public static final String WEB_REQUEST_HEAD_COMPLETE = "headComplete";
	public static final String WEB_REQUEST_FIRST_ROW = "firstRow";
	public static final String WEB_REQUEST_COMPLETE = "complete";

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
		this.requestProperies = mapper.createObjectNode();
	}

	@Override
	public Integer addLog(Map<String, Object> parameterMap) {
		// The returned id is only used internally by the Log Service and the Controller. When logged,
		// this request will be assigned a uuid for its identifier.
		int id = counter.getAndIncrement(); // first time, create id used to identify request
		Map<String, Object> parameterMapWithCount = new HashMap<String, Object>();
		parameterMapWithCount.putAll(parameterMap);
		parameterMapWithCount.put("requestCount", id);
		addRequestParams(WEB_REQUEST_START, parameterMapWithCount);
		return id;
	}

	@Override
	public void setHeadComplete(Map<String, Object> parameterMap) {
		addRequestParams(WEB_REQUEST_HEAD_COMPLETE, parameterMap);
	}

	@Override
	public void setFirstRow(Map<String, Object> parameterMap) {
		addRequestParams(WEB_REQUEST_FIRST_ROW, parameterMap);
	}

	@Override
	public void setRequestComplete(Map<String, Object> parameterMap) {
		addRequestParams(WEB_REQUEST_COMPLETE, parameterMap);
		log(requestProperies);
		requestProperies = null;
	}

	// log the request if it has not been already
	private void log() {
		if(requestProperies != null) {
			log(requestProperies);
		}
	}

	private void log(ObjectNode json) {
		JsonNode field;
		if (json != null) {
			json.put(ID, UUID.randomUUID().toString());
			field = json.get(ID);
			String id = field == null || field.isNull() ? null : field.asText();
			field = json.get("stage");
			String stage = field == null || field.isNull() ? null : field.asText();
			LOG.info(String.format("Web Request|%s|%s|%s", id, stage, json.toString()));
		}
	}

	/*
	 * accumulate request properties as json.
	 */
	private void addRequestParams(String stage, Map<String, Object> parameterMap) {
		addStage(stage, parameterMap);
		addParamsToJson(parameterMap);
	}

	private void addStage(String stage, Map<String, Object> parameterMap) {
		requestProperies.put("stage", stage);
		addTimestamp(requestProperies, stage);
	}

	private void addParamsToJson(Map<String, Object> parameterMap) {
		for (String param : parameterMap.keySet()) {
			Object valueObj = parameterMap.get(param);
			if (valueObj == null) {
				requestProperies.set(param, NullNode.getInstance());
			} else if (valueObj instanceof String) {
				setJsonStringField(requestProperies, param, (String) valueObj);
			} else if (valueObj instanceof Integer) {
				requestProperies.put(param, (Integer) valueObj);
			} else {
				String value = String.format("param '%s' type (%s) is unknown : %s", 
						param, valueObj.getClass().getName(), valueObj.toString());
				requestProperies.put(param, value);
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

	private void addTimestamp(ObjectNode json, String stage) {
		String timestamp =  DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		json.put(stage, timestamp);
	}

	@PreDestroy
	public void cleanup() {
		log();
	}

}
