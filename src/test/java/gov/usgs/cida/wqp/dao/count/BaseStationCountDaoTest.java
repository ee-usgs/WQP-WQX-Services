package gov.usgs.cida.wqp.dao.count;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.parameter.Parameters;

public abstract class BaseStationCountDaoTest extends BaseCountDaoTest {

	protected boolean includeActivity = false;
	protected boolean includeResults = false;

	protected void mimeTypeTest(NameSpace nameSpace) {
		Map<String, Object> parms = new HashMap<>();

		parms.put(Parameters.MIMETYPE.toString(), JSON);
		List<Map<String, Object>> counts = countDao.getCounts(nameSpace, parms);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		parms.put(Parameters.MIMETYPE.toString(), GEOJSON);
		counts = countDao.getCounts(nameSpace, parms);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		parms.put(Parameters.MIMETYPE.toString(), KML);
		counts = countDao.getCounts(nameSpace, parms);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		parms.put(Parameters.MIMETYPE.toString(), KMZ);
		counts = countDao.getCounts(nameSpace, parms);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		parms.put(Parameters.MIMETYPE.toString(), CSV);
		counts = countDao.getCounts(nameSpace, parms);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT);
	}

}
