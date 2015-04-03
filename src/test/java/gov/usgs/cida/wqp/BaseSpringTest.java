package gov.usgs.cida.wqp;
import static org.junit.Assert.fail;

import java.io.IOException;

import gov.usgs.cida.wqp.springinit.SpringConfig;
import gov.usgs.cida.wqp.springinit.TestSpringConfig;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration //(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public abstract class BaseSpringTest {
	public static final String BACKSPACE = (new Character((char) 8)).toString();
	public static final String ESCAPED_BACKSPACE = "&#8;";
	public static final String DOUBLE_ESCAPED_BACKSPACE = "&amp;#8;";
	public static final String XML_PREFIX_XML = "<?xml version=\"1.0\" ?>";
	public static final String TEST_ELEMENT = "<xyz abc=\"123\" def=\"qrs\"/>";
	public static final String TEST_FRAGMENT = "<xyz abc=\"123\" def=\"qrs\">mytext</xyz>";
	
	public static final XMLInputFactory INPUT_FACTORY = XMLInputFactory.newInstance();
	public static final XMLOutputFactory OUTPUT_FACTORY = XMLOutputFactory.newInstance();
	
	
//	@Override
//	public void setEnvironment(Environment environment) {
//		WqpEnv.setEnv(environment);
//	}
//	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
	
	
	public String harmonizeXml(String xmlDoc) {
		return xmlDoc.replace("\r", "").replace("\n", "").replace("\t", "").replaceAll("> *<", "><");
	}
	
	
	public BaseSpringTest() {
		try {
			Class.forName(SpringConfig.class.getName());
		} catch (ClassNotFoundException e) {
			fail("Need to load the config for parameter validation mappings.");
		}
	}
	
	public String getCompareFile(String file) throws IOException {
		return new String(FileCopyUtils.copyToByteArray(new ClassPathResource("testResult/" + file).getInputStream()));
	}
}