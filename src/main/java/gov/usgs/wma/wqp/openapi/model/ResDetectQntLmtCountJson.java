package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class ResDetectQntLmtCountJson extends ResultCountJson {

	public static final String HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT = NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RES_DETECT_QNT_LMT_COUNT;
	public static final String HEADER_STEWARDS_RES_DETECT_QNT_LMT_COUNT = STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RES_DETECT_QNT_LMT_COUNT;
	public static final String HEADER_STORET_RES_DETECT_QNT_LMT_COUNT = STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RES_DETECT_QNT_LMT_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT)
	public String totalResDetectQntLmtCount;
	@JsonProperty(HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT)
	public String nwisResDetectQntLmtCount;
	@JsonProperty(HEADER_STEWARDS_RES_DETECT_QNT_LMT_COUNT)
	public String stewardsResDetectQntLmtCount;
	@JsonProperty(HEADER_STORET_RES_DETECT_QNT_LMT_COUNT)
	public String storetResDetectQntLmtCount;

}
