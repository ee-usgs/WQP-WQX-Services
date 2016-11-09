package gov.usgs.cida.wqp.parameter.transform;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.springinit.ParameterValidationConfig;
import gov.usgs.cida.wqp.validation.AbstractValidator;

public class SplitAndReplaceTransformerTest extends BaseSpringTest {
	@Test
	public void testSplitAndReplace_default() {
		SplitAndReplaceTransformer transformer = new SplitAndReplaceTransformer(AbstractValidator.DEFAULT_DELIMITER, ParameterValidationConfig.REGEX_HUC_WILDCARD_IN, ParameterValidationConfig.REGEX_HUC_WILDCARD_OUT);
		String[] split = transformer.transform("a;b*;c");
		assertEquals(3, split.length);
		assertEquals("a", split[0]);
		assertEquals("b", split[1]);
		assertEquals("c", split[2]);
	}
	@Test
	public void testSplitAndReplace_custom() {
		SplitAndReplaceTransformer transformer = new SplitAndReplaceTransformer("x", "duck", "quack");
		String[] split = transformer.transform("axbxduckxd");
		assertEquals(4, split.length);
		assertEquals("a", split[0]);
		assertEquals("b", split[1]);
		assertEquals("quack", split[2]);
		assertEquals("d", split[3]);
	}
}