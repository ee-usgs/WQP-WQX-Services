package gov.usgs.cida.wqp.dao.summary;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.FilteredDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/organization.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class SummaryOrganizationStreamingIT extends FilteredDaoTest {
 	private static final Logger LOG = LoggerFactory.getLogger(BaseStationStreamingTest.class);
	protected NameSpace nameSpace = NameSpace.SUMMARY_ORGANIZATION;
	
	public static final String ORG_ID_WIDNR = "WIDNR_WQX_TEST";
	
	public static final String SUMMARY_YEARS_12_MONTHS = "1";
	public static final String SUMMARY_YEARS_60_MONTHS = "5";
	public static final String SUMMARY_YEARS_ALL_MONTHS = "all";

	@Autowired 
	IStreamingDao streamingDao;
   
	@Test
	public void testHarness() {
	    organizationTest(nameSpace);
//	    sortedAllSummaryTest(nameSpace);
//	    sortedFiveYearsSummaryTest(nameSpace);
//	    sortedOneYearSummaryTest(nameSpace);
	}

    @Override
    public List<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void assertSiteUrlBase(Map<String, Object> row) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void organizationTest(NameSpace nameSpace) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sortedAllSummaryTest(NameSpace nameSpace) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sortedFiveYearsSummaryTest(NameSpace nameSpace) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sortedOneYearSummaryTest(NameSpace nameSpace) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
