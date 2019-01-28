package gov.usgs.cida.wqp.transform;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import gov.usgs.cida.wqp.service.ILogService;

public abstract class BaseMapToJsonTransformer extends Transformer {

	protected JsonFactory f;
	protected JsonGenerator g;
	protected String siteUrlBase;

	public BaseMapToJsonTransformer(OutputStream target, Map<String, String> mapping, ILogService logService, Integer logId, String siteUrlBase) {
		super(target, mapping, logService, logId);
		this.siteUrlBase = siteUrlBase;
		init();
	}

	@Override
	protected void init() {
		f = new JsonFactory();
		try {
			g = f.createGenerator(target);
		} catch (IOException e) {
			throw new RuntimeException("Error initializing json document", e);
		}
		writeHeader();
	}

	protected String getValue(Map<String, Object> resultMap, String key) {
		if (resultMap.containsKey(key) && null != resultMap.get(key)) {
			return encode(Transformer.getStringValue(resultMap.get(key)));
		} else {
			return "";
		}
	}

	@Override
	public String encode(String value) {
		//The jackson code takes care of encoding these values.
		return value;
	}
}
