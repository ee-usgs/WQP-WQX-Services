package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import gov.usgs.cida.wqp.dao.NameSpace;

public abstract class BaseStationCountDaoTest extends BaseCountDaoTest {

	protected boolean includeActivity = false;
	protected boolean includeResults = false;

	@Override
	protected void mimeTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, 5);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		counts = mimeTypeGeoJsonTest(nameSpace, 5);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		counts = mimeTypeKmlTest(nameSpace, 5);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		counts = mimeTypeKmzTest(nameSpace, 5);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT_GEOM);

		counts = mimeTypeCsvTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeTsvTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXmlTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXlsxTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
	}

}
