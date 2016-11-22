package gov.usgs.cida.wqp.dao.streaming;


import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.parameter.Parameters;

@Category(DBIntegrationTest.class)
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/simpleStation.xml")
})
public class StreamingDaoTest extends BaseSpringTest {

	@Autowired 
	IStreamingDao streamingDao;
	
	TestResultHandler handler;

	@Before
	public void init() {
		handler = new TestResultHandler();
	}

	@After
	public void cleanup() {
		handler = null;
	}

	@Test
	public void stationTests() {
		singleParameterTests(BaseDao.STATION_NAMESPACE);
		multipleParameterTests(BaseDao.STATION_NAMESPACE);
	}

	@Test
	public void stationKmlTests() {
		singleParameterTests(BaseDao.STATION_KML_NAMESPACE);
		multipleParameterTests(BaseDao.STATION_KML_NAMESPACE);
	}

	@Test
	public void simpleStationTests() {
		singleParameterTests(BaseDao.SIMPLE_STATION_NAMESPACE);
		multipleParameterTests(BaseDao.SIMPLE_STATION_NAMESPACE);
	}

	@Test
	public void resultTests() {
		singleParameterTests(BaseDao.RESULT_NAMESPACE);
		multipleParameterTests(BaseDao.RESULT_NAMESPACE);
	}

	@Test
	public void bioResultTests() {
		singleParameterTests(BaseDao.BIOLOGICAL_RESULT_NAMESPACE);
		multipleParameterTests(BaseDao.BIOLOGICAL_RESULT_NAMESPACE);
	}

	@Test
	public void activityTests() {
		singleParameterTests(BaseDao.ACTIVITY_NAMESPACE);
		multipleParameterTests(BaseDao.ACTIVITY_NAMESPACE);
	}

	private void singleParameterTests(String nameSpace) {
		//TODO - Real test data and verification. 
		//TODO - These tests just validate that the queries have no syntax errors, not that they are logically correct.
		Map<String, Object> parms = new HashMap<>();

		//MyBatis is happy with no parms or ResultHandler - it will read the entire database, load up the list,
		// and not complain or expose it to you (unless you run out of memory). We have a check to make sure the 
		//resultHandler is not null. (The tests were failing on Jenkins with "java.lang.OutOfMemoryError: Java heap space")
		try {
			streamingDao.stream(null, null, null);
		} catch (RuntimeException e) {
			if (!"A ResultHandler is required for the StreamingDao.stream".equalsIgnoreCase(e.getMessage())) {
				fail("Expected a RuntimeException, but got " + e.getLocalizedMessage());
			}
		}
		try {
		streamingDao.stream(nameSpace, null, null);
		} catch (RuntimeException e) {
			if (!"A ResultHandler is required for the StreamingDao.stream".equalsIgnoreCase(e.getMessage())) {
				fail("Expected a RuntimeException, but got " + e.getLocalizedMessage());
			}
		}
		try {
		streamingDao.stream(nameSpace, parms, null);
		} catch (RuntimeException e) {
			if (!"A ResultHandler is required for the StreamingDao.stream".equalsIgnoreCase(e.getMessage())) {
				fail("Expected a RuntimeException, but got " + e.getLocalizedMessage());
			}
		}

		streamingDao.stream(nameSpace, parms, handler);

		parms.put(Parameters.BBOX.toString(), getBBox());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.COUNTY.toString(), getCounty());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"0708"});
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"070801"});
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07090002"});
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		streamingDao.stream(nameSpace, parms, handler);

		try {
			parms.clear();
			parms.put(Parameters.NLDIURL.toString(), getSourceFile("manySites.txt").split(","));
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		parms.clear();
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.SITEID.toString(), getSiteid());
		streamingDao.stream(nameSpace, parms, handler);

		try {
			parms.clear();
			parms.put(Parameters.SITEID.toString(), getSourceFile("manySites.txt").split(","));
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		parms.clear();
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.STATE.toString(), getState());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.PCODE.toString(), getPcode());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.PROJECT.toString(), getProject());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		streamingDao.stream(nameSpace, parms, handler);

		//TODO - Activity is not currently supported
		//parms.put(Parameters.ACTIVITY_ID.toString(), new String[]{"abc"});
		//streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		parms.clear();
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

	}

	private void multipleParameterTests(String nameSpace) {
		//TODO - Real test data and verification. 
		//TODO - These tests just validate that the queries have no syntax errors, not that they are logically correct.
		Map<String, Object> parms = new HashMap<>();

		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		streamingDao.stream(nameSpace, parms, handler);
	}

}
