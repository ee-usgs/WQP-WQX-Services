package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class BaseStationCountDaoTest extends BaseCountDaoTest {

	protected boolean includeActivity = false;
	protected boolean includeResults = false;

	protected void mimeTypeTest(NameSpace nameSpace) {
		FilterParameters filter = new FilterParameters();

		filter.setMimeType(JSON);
		List<Map<String, Object>> counts = countDao.getCounts(nameSpace, filter);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		filter.setMimeType(GEOJSON);
		counts = countDao.getCounts(nameSpace, filter);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		filter.setMimeType(KML);
		counts = countDao.getCounts(nameSpace, filter);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		filter.setMimeType(KMZ);
		counts = countDao.getCounts(nameSpace, filter);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		filter.setMimeType(CSV);
		counts = countDao.getCounts(nameSpace, filter);
		assertStationResults(counts, 5, TOTAL_SITE_COUNT, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT);
	}

}
