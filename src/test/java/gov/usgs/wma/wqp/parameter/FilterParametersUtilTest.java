package gov.usgs.wma.wqp.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

class FilterParametersUtilTest {

	FilterParameters params;

	@BeforeEach
	void setUp() {
		params = new FilterParameters();
	}

	@Test
	void nonNullModifiableList() {
		List orgList = Arrays.asList("US");
		List newList = FilterParametersUtil.nonNullModifiableList(orgList);

		assertTrue(orgList != newList);
		assertThat(orgList).containsAnyElementsOf(newList);
		newList.clear();	//should be OK

		newList = FilterParametersUtil.nonNullModifiableList(null);
		assertTrue(newList.isEmpty());
	}

	@Test
	void emptyToNullParametersTest() {
		//Set to all empty lists
		params.setCountrycode(FilterParametersUtil.nonNullModifiableList(null));
		params.setStatecode(FilterParametersUtil.nonNullModifiableList(null));
		params.setCountycode(FilterParametersUtil.nonNullModifiableList(null));

		FilterParametersUtil.emptyToNullParameters(params);

		assertNull(params.getCountrycode());
		assertNull(params.getStatecode());
		assertNull(params.getCountycode());
	}

	@Test
	void dedupParametersOneCountryOnly() {

		params.setCountrycode(Arrays.asList("US"));
		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertEquals(1, params.getCountrycode().size());
		assertThat(params.getCountrycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountrycode());
		assertTrue(params.getStatecode().isEmpty());
		assertTrue(params.getCountycode().isEmpty());
	}

	@Test
	void dedupParametersTwoCountryOnly() {

		params.setCountrycode(Arrays.asList("US","CH"));
		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertEquals(2, params.getCountrycode().size());
		assertThat(params.getCountrycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountrycode());
		assertTrue(params.getStatecode().isEmpty());
		assertTrue(params.getCountycode().isEmpty());
	}

	@Test
	void dedupParametersOneStateOnly() {

		params.setStatecode(Arrays.asList("US:06"));
		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertEquals(1, params.getStatecode().size());
		assertThat(params.getStatecode()).containsExactlyInAnyOrderElementsOf(orgParams.getStatecode());
		assertTrue(params.getCountycode().isEmpty());
	}

	@Test
	void dedupParametersTwoStateOnly() {

		params.setStatecode(Arrays.asList("US:06", "US:07"));
		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertEquals(2, params.getStatecode().size());
		assertThat(params.getStatecode()).containsExactlyInAnyOrderElementsOf(orgParams.getStatecode());
		assertTrue(params.getCountycode().isEmpty());
	}

	@Test
	void dedupParametersOneCountyOnly() {

		params.setCountycode(Arrays.asList("US:06:047"));
		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertEquals(1, params.getCountycode().size());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersTwoCountyOnly() {

		params.setCountycode(Arrays.asList("US:06:047", "US:06:048"));
		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertEquals(2, params.getCountycode().size());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersSimpleTree() {

		params.setCountrycode(Arrays.asList("US"));
		params.setStatecode(Arrays.asList("US:06"));
		params.setCountycode(Arrays.asList("US:06:047"));

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersAllThreeInSeparateTrees() {

		params.setCountrycode(Arrays.asList("US"));
		params.setStatecode(Arrays.asList("XX:06"));
		params.setCountycode(Arrays.asList("XX:07:047"));	//None of these match - they should be all kept

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertThat(params.getCountrycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountrycode());
		assertThat(params.getStatecode()).containsExactlyInAnyOrderElementsOf(orgParams.getStatecode());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersCountryInSeparateTree() {

		params.setCountrycode(Arrays.asList("US"));	//Keep country and county to preserved clash
		params.setStatecode(Arrays.asList("XX:06"));
		params.setCountycode(Arrays.asList("XX:06:047"));

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertThat(params.getCountrycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountrycode());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersStateInSeparateTree() {

		params.setCountrycode(Arrays.asList("US"));
		params.setStatecode(Arrays.asList("XX:06"));	//State and county kept to preserve clash
		params.setCountycode(Arrays.asList("US:07:047"));

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertThat(params.getStatecode()).containsExactlyInAnyOrderElementsOf(orgParams.getStatecode());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersCountyInSeparateTree() {

		params.setCountrycode(Arrays.asList("US"));	//Country can be removed
		params.setStatecode(Arrays.asList("US:06"));	//State and county preserved to keep 'clash'
		params.setCountycode(Arrays.asList("US:07:047"));

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertThat(params.getStatecode()).containsExactlyInAnyOrderElementsOf(orgParams.getStatecode());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersSimpleTreeWithExtraCountry() {

		params.setCountrycode(Arrays.asList("US", "CH")); //Drop all - covered by county
		params.setStatecode(Arrays.asList("US:06")); //Drop all - covered by county
		params.setCountycode(Arrays.asList("US:06:047"));

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersSimpleTreeWithExtraCountryAndExtraState() {

		params.setCountrycode(Arrays.asList("US", "CH")); //Drop all - covered by county
		params.setStatecode(Arrays.asList("US:06", "US:07")); //Drop all - covered by county
		params.setCountycode(Arrays.asList("US:06:047"));

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(orgParams.getCountycode());
	}

	@Test
	void dedupParametersSimpleTreeWithExtraCountryAndExtraStateAndExtraCounty() {

		params.setCountrycode(Arrays.asList("US", "CH")); //Drop all - covered by county
		params.setStatecode(Arrays.asList("US:06", "US:07")); //Drop all - covered by county
		params.setCountycode(Arrays.asList("US:06:047", "US:06:048", "US:08:999"));	//US:08:999 should be dropped

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(Arrays.asList("US:06:047", "US:06:048"));
	}

	@Test
	void dedupParametersReallyComplexAllThreeTiers() {

		params.setCountrycode(Arrays.asList("US", "CH")); //Drop all - covered by county
		params.setStatecode(Arrays.asList("US:06", "US:07", "XY:01")); //Drop all - covered by county
		params.setCountycode(Arrays.asList("US:06:047", "US:06:048", "US:08:999", "ZZ:02:999"));	//US:08:999 & ZZ:02:999 should be dropped

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(Arrays.asList("US:06:047", "US:06:048"));
	}

	@Test
	void dedupParametersReallyComplexCountryAndCountyOnly() {

		params.setCountrycode(Arrays.asList("US", "CH")); //Drop all - covered by county
		params.setCountycode(Arrays.asList("US:06:047", "US:06:048", "US:08:999", "ZZ:02:999"));	//US:08:999 & ZZ:02:999 should be dropped

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(Arrays.asList("US:06:047", "US:06:048", "US:08:999"));
	}

	@Test
	void dedupParametersReallyComplexStateAndCountyOnly() {

		params.setStatecode(Arrays.asList("US:06", "US:07", "XY:01")); //Drop all - covered by county
		params.setCountycode(Arrays.asList("US:06:047", "US:06:048", "US:08:999", "ZZ:02:999"));	//US:08:999 & ZZ:02:999 should be dropped

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertTrue(params.getStatecode().isEmpty());
		assertThat(params.getCountycode()).containsExactlyInAnyOrderElementsOf(Arrays.asList("US:06:047", "US:06:048"));
	}

	@Test
	void dedupParametersReallyComplexCountryAndStateOnly() {

		params.setCountrycode(Arrays.asList("US", "CH")); //Drop all - covered by county
		params.setStatecode(Arrays.asList("US:06", "US:07", "XY:01")); //Drop all - covered by county

		FilterParameters orgParams = fpClone(params);

		FilterParametersUtil.dedupParameters(params);

		assertTrue(params.getCountrycode().isEmpty());
		assertThat(params.getStatecode()).containsExactlyInAnyOrderElementsOf(Arrays.asList("US:06", "US:07"));
		assertTrue(params.getCountycode().isEmpty());
	}

	/**
	 * Clones the FilterParameters by copying just the country, state, and county lists.
	 * The new FP has detached lists so they can be separately modified.
	 * @param params
	 * @return
	 */
	public FilterParameters fpClone(FilterParameters params) {
		FilterParameters newParams = new FilterParameters();
		newParams.setCountrycode(new ArrayList(FilterParametersUtil.nonNullModifiableList(params.getCountrycode())));
		newParams.setStatecode(new ArrayList(FilterParametersUtil.nonNullModifiableList(params.getStatecode())));
		newParams.setCountycode(new ArrayList(FilterParametersUtil.nonNullModifiableList(params.getCountycode())));

		return newParams;
	}


}
