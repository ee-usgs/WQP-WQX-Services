package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.mapping.ResultColumn.*;
import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.cida.wqp.BaseSpringTest;

public class TestResDetectQntLmtMap {

	public static final Map<String, Object> RES_DETECT_QNT_LMT;
	static {
		RES_DETECT_QNT_LMT = new LinkedHashMap<String, Object>();
		RES_DETECT_QNT_LMT.put(KEY_DATA_SOURCE_ID, BaseSpringTest.STORET_ID);
		RES_DETECT_QNT_LMT.put(KEY_DATA_SOURCE, BaseSpringTest.STORET);
		RES_DETECT_QNT_LMT.put(KEY_SITE_ID, "WIDNR_WQX-113086");
		RES_DETECT_QNT_LMT.put(KEY_ACTIVITY, "WIDNR_WQX-7788475");
		RES_DETECT_QNT_LMT.put(KEY_CHARACTERISTIC_NAME, "Fecal Streptococcus Group Bacteria");
		RES_DETECT_QNT_LMT.put(KEY_ORGANIZATION_NAME, "Wisconsin Department of Natural Resources");
		RES_DETECT_QNT_LMT.put(KEY_RESULT_ID, BigDecimal.valueOf(5));
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT, "39");
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT_UNIT, "cfu/100ml");
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT_DESC, "Reporting limit");
	}

	private TestResDetectQntLmtMap() {
	}
}
