package gov.usgs.cida.wqp.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import gov.usgs.cida.wqp.dao.NameSpace;

public enum Profile {

	ORGANIZATION ("organization", "organization", NameSpace.ORGANIZATION),
	SUMMARY_ORGANIZATION("summaryorganization", "summaryorganization", NameSpace.SUMMARY_ORGANIZATION),

	STATION ("station", "station", NameSpace.STATION),
	SIMPLE_STATION ("simplestation", "simplestation", NameSpace.SIMPLE_STATION),
	SUMMARY_MONITORING_LOCATION ("summaryMonitoringLocation","summarymonitoringlocation", NameSpace.SUMMARY_MONITORING_LOCATION),
	
	PERIOD_OF_RECORD ("periodOfRecord", "periodofrecord", NameSpace.PERIOD_OF_RECORD),

	PROJECT ("project", "project", NameSpace.PROJECT),
	PROJECT_MONITORING_LOCATION_WEIGHTING ("projectMonitoringLocationWeighting", "projectmonitoringlocationweighting",
			NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING),

	ACTIVITY ("activity", "activity", NameSpace.ACTIVITY),
	ACTIVITY_ALL("activityAll", "activityall", NameSpace.ACTIVITY_ALL),
	ACTIVITY_METRIC ("activityMetric", "activitymetric", NameSpace.ACTIVITY_METRIC),
	
	SUMMARY_BIOLOGICAL_METRIC ("biologicalMetric", "biologicalmetric", NameSpace.SUMMARY_BIOLOGICAL_METRIC),

	BIOLOGICAL ("biological", "biologicalresult", NameSpace.BIOLOGICAL_RESULT),
	PC_RESULT ("pcresult", "result", NameSpace.RESULT),
	NARROW_RESULT ("narrowResult", "narrowresult", NameSpace.NARROW_RESULT),
	RESULT_PHYS_CHEM ("resultPhysChem", "resultphyschem", NameSpace.RESULT_PHYS_CHEM),
	RESULT_PRIMARY ("resultPrimary", "resultprimary", NameSpace.RESULT_PRIMARY),
	RESULT_BROAD ("resultBroad", "resultbroad", NameSpace.RESULT_BROAD),

	RES_DETECT_QNT_LMT ("resDetectQntLmt", "resdetectqntlmt", NameSpace.RES_DETECT_QNT_LMT);

	private final String name;
	private final String baseFileName;
	private final NameSpace defaultNameSpace;
	private static Map<String, Profile> profileMap = new HashMap<>();

	static {
		for (Profile profile : Profile.values()) {
			profileMap.put(profile.name, profile);
		}
	}

	private Profile(String name, String baseFileName, NameSpace defaultNameSpace) {
		this.name = name;
		this.baseFileName = baseFileName;
		this.defaultNameSpace = defaultNameSpace;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getBaseFileName() {
		return baseFileName;
	}

	public NameSpace getDefaultNameSpace() {
		return defaultNameSpace;
	}

	public static Profile fromString(String profile) {
		if (profile == null || profile.isEmpty()) {
			return null;
		}
		try {
			return profileMap.get(profile);
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Set<String> getValues() {
		return profileMap.keySet();
	}
}
