package gov.usgs.cida.wqp;
import static org.junit.Assert.*;
import gov.usgs.cida.wqp.springinit.SpringConfig;
import gov.usgs.cida.wqp.util.WqpEnv;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import org.junit.runner.RunWith;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/testContext.xml" })
@TestPropertySource(value = {"/wqp-test.properties"})
@ComponentScan(basePackages = {WqpEnv.BASE_PACKAGE})
public abstract class BaseSpringTest implements EnvironmentAware {
	
	public static final String BACKSPACE = (new Character((char) 8)).toString();
	public static final String ESCAPED_BACKSPACE = "&#8;";
	public static final String DOUBLE_ESCAPED_BACKSPACE = "&amp;#8;";
	public static final String XML_PREFIX_XML = "<?xml version=\"1.0\" ?>";
	public static final String TEST_ELEMENT = "<xyz abc=\"123\" def=\"qrs\"/>";
	public static final String TEST_FRAGMENT = "<xyz abc=\"123\" def=\"qrs\">mytext</xyz>";
	
	public static final XMLInputFactory INPUT_FACTORY = XMLInputFactory.newInstance();
	public static final XMLOutputFactory OUTPUT_FACTORY = XMLOutputFactory.newInstance();
	
	
	@Override
	public void setEnvironment(Environment environment) {
		WqpEnv.setEnv(environment);
	}
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
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