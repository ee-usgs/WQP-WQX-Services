package gov.usgs.cida.wqp.dao.streaming;

import java.util.LinkedList;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class TestResultHandler implements ResultHandler<Object> {

	public LinkedList<Map<String, Object>> results = new LinkedList<Map<String, Object>>();

	@SuppressWarnings("unchecked")
	@Override
	public void handleResult(ResultContext<?> context) {
		results.add((Map<String, Object>) context.getResultObject());
	}

	public LinkedList<Map<String, Object>> getResults() {
		return results;
	}

}
