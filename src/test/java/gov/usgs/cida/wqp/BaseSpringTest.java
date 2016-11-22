package gov.usgs.cida.wqp;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.springinit.TestSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public abstract class BaseSpringTest {

	@Autowired
	@Qualifier("TEST_NLDI_URL")
	protected URL testNldiUrl;

	public String harmonizeXml(String xmlDoc) {
		return xmlDoc.replace("\r", "").replace("\n", "").replace("\t", "").replaceAll("> *<", "><");
	}

	public String getSourceFile(String file) throws IOException {
		return new String(FileCopyUtils.copyToByteArray(new ClassPathResource("testData/" + file).getInputStream()));
	}

	public String getCompareFile(String file) throws IOException {
		return new String(FileCopyUtils.copyToByteArray(new ClassPathResource("testResult/" + file).getInputStream()));
	}

	public String extractZipContent(byte[] content, String expectedEntryName) throws IOException {
		ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(content));
		ZipEntry e = in.getNextEntry();
		assertEquals(expectedEntryName, e.getName());

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int len;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			os.write(buffer, 0, len);
		}
		return os.toString();
	}

	public String[] getAnalyticalMethod() {
		return new String[]{"https://www.nemi.gov/methods/method_summary/4665/", "https://www.nemi.gov/methods/method_summary/8896/", "analyticalMethod"};
	}

	public String[] getAvoid() {
		return new String[]{"NWIS"};
	}

	public String[] getBBox() {
		return new String[]{"-111", "39", "-77", "47"};
	}

	public String[] getAssemblage() {
		return new String[]{"Fish/Nekton", "Benthic Macroinvertebrates", "assemblageSampledName"};
	}

	public String[] getCharacteristicName() {
		return new String[]{"Beryllium", "Nitrate", "characteristicName"};
	}

	public String[] getCharacteristicType() {
		return new String[]{"Inorganics, Minor, Metals", "Nutrient", "Population/Community", "characteristicType"};
	}

	public String[] getCountry() {
		return new String[]{"MX", "US", "XX"};
	}

	public String[] getCounty() {
		return new String[]{"US:19:015", "US:30:003", "US:55:017", "US:55:021", "US:55:027", "XX:44:555"};
	}

	public String[] getHuc() {
		return new String[]{"07*", "0708*", "070801*", "07090002", "07080105", "0000"};
	}

	public String[] getLatitude() {
		return new String[]{"43.3836014"};
	}

	public String[] getLongitude() {
		return new String[]{"-88.9773314"};
	}

	public String[] getMinResults() {
		return new String[]{"3"};
	}

	public String[] getNldiSites() {
		return new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
				"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410", "USGS-11421000", "organization-siteId2", "organization-siteId3"};
	}

	public String[] getNldiurl() {
		return new String[]{testNldiUrl.toString()};
	}

	public String[] getOrganization() {
		return new String[]{"ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX", "organization"};
	}

	public String[] getPcode() {
		return new String[]{"00032", "00004", "99999"};
	}

	public String[] getProject() {
		return new String[]{"projectId", "CEAP", "NAWQA", "SACR BioTDB"};
	}

	public String[] getProviders() {
		return new String[]{"NWIS", "STEWARDS", "STORET"};
	}

	public String[] getSampleMedia() {
		return new String[]{"sampleMedia", "Other", "Sediment", "Water"};
	}

	public String[] getSiteid() {
		return new String[]{"organization-siteId", "organization-siteId2", "organization-siteId3", "11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700", "USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410"};
	}

	public String[] getSiteType() {
		return new String[]{"siteType", "Lake, Reservoir, Impoundment", "Land", "Stream"};
	}

	public String[] getStartDateHi() {
		return new String[]{"10-11-2012"};
	}

	public String[] getStartDateLo() {
		return new String[]{"10-11-1995"};
	}

	public String[] getState() {
		return new String[]{"XX:44", "US:19", "US:30", "US:55"};
	}

	public String[] getSubjectTaxonomicName() {
		return new String[]{"Acipenser", "Lota lota", "sampleTissueTaxonomicName"};
	}

	public String[] getWithin() {
		return new String[]{"650"};
	}

}
