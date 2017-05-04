package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.KEY_ACTIVITY;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_STATION_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_EXTERNAL_RESULT_ID;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.cida.wqp.BaseSpringTest;

public class TestResDetectQntLmtMap {

	public static final Map<String, Object> RES_DETECT_QNT_LMT;
	static {
		RES_DETECT_QNT_LMT = new LinkedHashMap<String, Object>();
		RES_DETECT_QNT_LMT.put(KEY_DATA_SOURCE_ID, BaseSpringTest.STORET_ID);
		RES_DETECT_QNT_LMT.put(KEY_DATA_SOURCE, STORET);
		RES_DETECT_QNT_LMT.put(KEY_SITE_ID, "WIDNR_WQX-113086");
		RES_DETECT_QNT_LMT.put(KEY_STATION_ID, BigDecimal.valueOf(1383));
		RES_DETECT_QNT_LMT.put(KEY_ACTIVITY, "WIDNR_WQX-7788475");
		RES_DETECT_QNT_LMT.put(KEY_CHARACTERISTIC_NAME, "Fecal Streptococcus Group Bacteria");
		RES_DETECT_QNT_LMT.put(KEY_ORGANIZATION, "WIDNR_WQX");
		RES_DETECT_QNT_LMT.put(KEY_ORGANIZATION_NAME, "Wisconsin Department of Natural Resources");
		RES_DETECT_QNT_LMT.put(KEY_EXTERNAL_RESULT_ID, STORET + "-5");
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT_ID, BigDecimal.valueOf(7439));
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT, "39");
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT_UNIT, "cfu/100ml");
		RES_DETECT_QNT_LMT.put(KEY_DETECTION_LIMIT_DESC, "Reporting limit");
	}

	private TestResDetectQntLmtMap() {
	}
}
