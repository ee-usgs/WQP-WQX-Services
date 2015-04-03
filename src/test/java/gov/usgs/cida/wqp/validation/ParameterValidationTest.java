package gov.usgs.cida.wqp.validation;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.util.HttpConstants;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
public class ParameterValidationTest extends BaseSpringTest implements HttpConstants {
	@Resource
	HashMapParameterHandler parameterHandler;
	@Test
	public void test_invalid() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
		assertFalse(pm.isValid());
	}
	@Test
	public void test_valid() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
		assertTrue(pm.isValid());
		assertEquals(1, pm.getQueryParameters().size());
		assertTrue(  pm.getQueryParameters().containsKey("countrycode") );
		String[] paramValue = (String[])pm.getQueryParameters().get("countrycode");
		assertEquals( "US", paramValue[0] );
	}
}