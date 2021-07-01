package gov.usgs.wma.wqp.parameter;

import java.util.*;

/**
 * Util class for de-duplicating FilterParameters that are logical duplicate entries.
 */
public class FilterParametersUtil {


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


		if (! countries.isEmpty() && ! (states.isEmpty() && counties.isEmpty())) {
			//countries AND one or both of states and counties has an entries

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


			//Now remove all the country filters if there is some state or county criteria left
			if (otherCriteriaOverlapsCountries) {
				countries.clear();
			}

		} // else states and counties are empty, so don't modify/dedup countries

		if (! states.isEmpty() && ! counties.isEmpty()) {

			boolean otherCriteriaOverlapsStates = false;	//If true, at least some duplicating criteria was found

			//remove counties that are not in states
			//BUT, only do this if there is at least one match to preserve conflicting criteria.
			if (counties.stream().anyMatch(c -> states.stream().anyMatch(s -> c.startsWith(s)))) {
				otherCriteriaOverlapsStates = true;
				counties.removeIf(c -> !states.stream().anyMatch(s -> c.startsWith(s)));
			}

			//Now remove all the state filters if there is some county criteria left
			if (otherCriteriaOverlapsStates) {
				states.clear();
			}

		} // else counties are empty, so don't modify/dedup states


		//When all done, update to the new lists (its possible there was no change)
		filter.setCountrycode(countries);
		filter.setStatecode(states);
		filter.setCountycode(counties);

	}

	/**
	 * Create a list that is non-null and for-sure modifiable.
	 * List created via Arrays.asList() are not modifiable, so always need
	 * to copy values to a new list.
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
