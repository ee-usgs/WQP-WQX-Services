package gov.usgs.wma.wqp.parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Util class for de-duplicating FilterParameters that are logical duplicate entries.
 */
public class FilterParametersUtil {
	private static final Logger LOG = LoggerFactory.getLogger(FilterParametersUtil.class);

	/**
	 * Convert some list params from empty lists to null.
	 * These should match the params affected by dedupParameters.
	 * Its much easier to test with empty lists, but the way we have our MyBatis
	 * SQL templates, it assumes that criteria is only skipped if lists are null, not empty.
	 * Empty lists result in criteria entries with no actual criteria, e.g.: 'state in ()'.
	 * @param filter
	 */
	public static void emptyToNullParameters(FilterParameters filter) {
		//When all done, update to the new lists (its possible there was no change)
		//Really these need to be null to count as empty to MyBatis
		filter.setCountrycode(nonNullModifiableList(filter.getCountrycode()).isEmpty()?null:filter.getCountrycode());
		filter.setStatecode(nonNullModifiableList(filter.getStatecode()).isEmpty()?null:filter.getStatecode());
		filter.setCountycode(nonNullModifiableList(filter.getCountycode()).isEmpty()?null:filter.getCountycode());
	}
	/**
	 * Modify a FilterParameter object to remove logical duplicate criteria.
	 * For instance, if the params contains Country: US and State: US:06,
	 * remove the country, since the state criteria completely captures this.
	 * @param filter
	 */
	public static void dedupParameters(FilterParameters filter) {
		List<String> countries = nonNullModifiableList(filter.getCountrycode());
		List<String> states = nonNullModifiableList(filter.getStatecode());
		List<String> counties = nonNullModifiableList(filter.getCountycode());

		boolean haveGovUnitCriteria = ! countries.isEmpty() || ! states.isEmpty() || ! counties.isEmpty();


		if (! countries.isEmpty() && ! (states.isEmpty() && counties.isEmpty())) {
			//We have Countries AND (some states and/or counties)

			boolean otherCriteriaOverlapsCountries = false;	//If true, at least some duplicating criteria was found

			//remove states and counties that are not in countries b/c they will not add any rows to the result.
			//BUT, only do this if there is at least one match for each to preserve conflicting criteria.
			if (states.stream().anyMatch(s -> countries.stream().anyMatch(cr -> s.startsWith(cr)))) {
				otherCriteriaOverlapsCountries = true;
				states.removeIf(s -> ! countries.stream().anyMatch(cr -> s.startsWith(cr)));
			}

			if (counties.stream().anyMatch(c -> countries.stream().anyMatch(cr -> c.startsWith(cr)))) {
				otherCriteriaOverlapsCountries = true;
				counties.removeIf(c -> ! countries.stream().anyMatch(cr -> c.startsWith(cr)));
			}


			//Now remove all the Country filters if there are at least some state or county criteria that overlaps a Country
			if (otherCriteriaOverlapsCountries) {
				countries.clear();
			}

		} // else there are no Contries, OR, states and counties are both empty.  Thus don't modify/dedup WRT Countries.

		if (! states.isEmpty() && ! counties.isEmpty()) {
			//We have some states AND counties

			boolean otherCriteriaOverlapsStates = false;	//If true, at least some duplicating criteria was found

			//remove counties that are not in states
			//BUT, only do this if there is at least one match to preserve conflicting criteria.
			if (counties.stream().anyMatch(c -> states.stream().anyMatch(s -> c.startsWith(s)))) {
				otherCriteriaOverlapsStates = true;
				counties.removeIf(c -> !states.stream().anyMatch(s -> c.startsWith(s)));
			}

			//Now remove all the state filters if there were at least some states that overlapped some counties.
			if (otherCriteriaOverlapsStates) {
				states.clear();
			}

		} // else counties are empty, so don't modify/dedup states

		boolean haveResultingGovUnitCriteria = ! countries.isEmpty() || ! states.isEmpty() || ! counties.isEmpty();

		//Update the gov unit criteria only if there was initial criteria and there is at least some resulting criteria.
		//This is to protect against logic errors in this complex code, which might unintentionally remove all gov criteria
		//and tie up the db fetching all records.
		if (haveGovUnitCriteria && haveResultingGovUnitCriteria) {

			filter.setCountrycode(countries);
			filter.setStatecode(states);
			filter.setCountycode(counties);

		} else if (haveGovUnitCriteria && ! haveResultingGovUnitCriteria) {
			//Oops, we really botched the logic!
			LOG.error("The de-dup logic in {} is not working as expected and " +
					"would have inadvertently removed all gov unit criteria, so the original gov params were kept.  " +
					"The next log statement contains all the original FilterParameters.",
					FilterParametersUtil.class.getName());
			LOG.error("Complete FilterParameter list: " + filter.toJson());
		}

	}

	/**
	 * Create a list that is non-null and for-sure modifiable.
	 * List created via Arrays.asList() are not modifiable, so always need
	 * to copy values to a new list.
	 *
	 * If the list is null, a non-modifiable empty list is returned.
	 *
	 * @param list
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> nonNullModifiableList(List<T> list) {
		if (list != null) {
			return new ArrayList(list);	//Force create new detached list b/c some lists are non-modifiable
		} else {
			return Collections.emptyList();
		}
	}
}
