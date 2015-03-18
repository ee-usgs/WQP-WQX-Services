package gov.usgs.cida.wqp;
import gov.usgs.cida.wqp.springinit.SpringConfig;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/testContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
public abstract class BaseSpringTest {
	public static String XML_PREFIX_XML = "<?xml version=\"1.0\" ?>";
	public static final String BACKSPACE = (new Character((char) 8)).toString();
	public static final String ESCAPED_BACKSPACE = "&#8;";
	public static final String DOUBLE_ESCAPED_BACKSPACE = "&amp;#8;";
	public static XMLInputFactory INPUT_FACTORY = XMLInputFactory.newInstance();
	public static XMLOutputFactory OUTPUT_FACTORY = XMLOutputFactory.newInstance();
	public static String TEST_ELEMENT = "<xyz abc=\"123\" def=\"qrs\"/>";
	public static String TEST_FRAGMENT = "<xyz abc=\"123\" def=\"qrs\">mytext</xyz>";
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
}