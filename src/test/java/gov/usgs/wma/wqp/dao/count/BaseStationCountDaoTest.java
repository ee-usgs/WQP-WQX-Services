package gov.usgs.wma.wqp.dao.count;

import java.util.List;
import java.util.Map;

import gov.usgs.wma.wqp.dao.NameSpace;

public abstract class BaseStationCountDaoTest extends BaseCountDaoTest {

	protected boolean includeActivity = false;
	protected boolean includeResults = false;

	@Override
	protected void mimeTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT_GEOM, STEWARDS_SITE_COUNT, STORET_SITE_COUNT);

		counts = mimeTypeGeoJsonTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT_GEOM, STEWARDS_SITE_COUNT, STORET_SITE_COUNT);

		counts = mimeTypeKmlTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT_GEOM, STEWARDS_SITE_COUNT, STORET_SITE_COUNT);

		counts = mimeTypeKmzTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_COUNT_GEOM, NWIS_SITE_COUNT_GEOM, STEWARDS_SITE_COUNT, STORET_SITE_COUNT);

		counts = mimeTypeCsvTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeTsvTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXmlTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXlsxTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
	}

}
