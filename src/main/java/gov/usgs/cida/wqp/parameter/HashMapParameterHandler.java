package gov.usgs.cida.wqp.parameter;

import gov.usgs.cida.wqp.validation.AbstractValidator;
import gov.usgs.cida.wqp.validation.ValidationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashMapParameterHandler implements IParameterHandler {
	private static final Logger LOG = LoggerFactory.getLogger(HashMapParameterHandler.class);

	private static Map<Parameters, AbstractValidator<?>> VALIDATOR_MAP;
	private static final Set<Parameters> WITHIN_GROUP;

	static final List<Set<Parameters>> GROUP_LIST;
	static {
		WITHIN_GROUP = new HashSet<Parameters>();
		WITHIN_GROUP.add(Parameters.LATITUDE);
		WITHIN_GROUP.add(Parameters.LONGITUDE);
		WITHIN_GROUP.add(Parameters.WITHIN);

		GROUP_LIST = new ArrayList<Set<Parameters>>();
		GROUP_LIST.add(WITHIN_GROUP);
	}

	public static AbstractValidator<?> getValidator(final Parameters inParameters) {
		return VALIDATOR_MAP.get(inParameters);
	}

	public HashMapParameterHandler(Map<Parameters, AbstractValidator<?>> inValidatoryMap) {
		LOG.trace(getClass().getName());
		VALIDATOR_MAP = inValidatoryMap;
	}

	@Override
	public ParameterMap validateAndTransform(final Map<String, String[]> inRequestParameters, final Map<String, Object> postParms, final Object pathVariables) {
		Map<String, String[]> merged = mergeParameters(mergeParameters(inRequestParameters, postParms), pathVariables);
		Map<String, String[]> pruned = pruneParameters(merged);
		ParameterMap out = validateParameterNamesAndGroups(pruned.keySet());
		if (out.isValid()) {
			out.merge(validateAndTransformParameterValues(pruned));
		}
		return out;
	}

	protected Map<String, String[]> mergeParameters(final Map<String, String[]> original, Object additions) {
		Map<String, String[]> merged = new HashMap<>();
		if (null != original) {
			merged.putAll(original);
		}
		if (null != additions && additions instanceof Map) {
			Iterator<?> i = ((Map<?,?>) additions).entrySet().iterator();
			while (i.hasNext()) {
				Entry<?,?> entry = (Entry<?, ?>) i.next();
				Object key = ((Entry<?,?>) entry).getKey();
				List<String> mergedValues = convertPostValueToList(entry.getValue());
				if (merged.containsKey(key)) {
					mergedValues.addAll(Arrays.asList(merged.get(key)));
				}
				merged.put((String) key, mergedValues.toArray(new String[mergedValues.size()]));
			}
		}
		return merged;
	}

	@SuppressWarnings("unchecked")
	protected List<String> convertPostValueToList(final Object entryValue) {
		List<String> list = new ArrayList<>();
		if (entryValue instanceof String) {
			list.add((String) entryValue);
		} else if (entryValue instanceof Collection<?>){
			list.addAll((Collection<? extends String>) entryValue);
		}
		return list;
	};

	public Map<String, String[]> pruneParameters(final Map<String, String[]> inParameters) {
		Map<String, String[]> rtn = new HashMap<>();
		Iterator<?> entryIterator = inParameters.entrySet().iterator();
		while (entryIterator.hasNext()) {
			Object entry = entryIterator.next();
			if (entry instanceof Map.Entry<?,?>) {
				Object value = ((Map.Entry<?,?>)entry).getValue();
				Object key = ((Map.Entry<?,?>)entry).getKey();
				if (value instanceof String[] && key instanceof String && Parameters.isValid((String) key)) {
					String[] strings = (String[]) value;
					List<String> nonTrivialValues = new ArrayList<String>();
					for(String string : strings) {
						if (string != null && string.length() > 0) {
							nonTrivialValues.add(string);
						}
					}
					if (!nonTrivialValues.isEmpty()) {
						rtn.put((String) key, new String[]{StringUtils.join(nonTrivialValues, ';')});
					}
				}
			}
		}
		return rtn;
	}

	@Override
	public ParameterMap validateParameterNamesAndGroups(Set<String> inParameterNames) {
		ParameterMap rtn = new ParameterMap();
		Set<Parameters> userParameterSet = new HashSet<Parameters>();
		if (inParameterNames != null) {
			for (String pName : inParameterNames) {
				boolean isOk = true;
				String parameterName = pName;
				if (Parameters.isValid(parameterName)) {//TODO should the prune remove parameters we don't care about?
					Parameters parameter = Parameters.fromName(parameterName);
					if ( ! VALIDATOR_MAP.containsKey(parameter) ) {//TODO catch in configuration?
						isOk = false;
					} else {
						userParameterSet.add(parameter);
					}
				} else {
					isOk = false; //TODO possibly allow extra parameters!!!!
				}
				if ( ! isOk ) {
					ValidationResult<?> vr = new ValidationResult<Object>();
					vr.setValid(false);
					vr.getValidationMessages().add("Parameter Name '"+ parameterName +"' is not valid.  This service will NOT return ANY results until this parameter is removed from the query.");
					rtn.merge(parameterName, vr);
				}
			}
			rtn.merge(validateParameterGroups(userParameterSet));
		}
		return rtn;
	}

	public ParameterMap validateAndTransformParameterValues(Map<String, String[]> inMap) {
		ParameterMap rtnMap = new ParameterMap();
		for (Entry<String, String[]> entry : inMap.entrySet()) {
			String parameterName = entry.getKey();
			if (entry.getValue().length == 1) {
				AbstractValidator<?> validator = getValidator(Parameters.fromName(parameterName));
				if (validator != null) {
					rtnMap.merge(parameterName, validator.validate(entry.getValue()[0]));
				}
			} else {
				ValidationResult<?> vr = new ValidationResult<Object>();
				vr.setValid(false);
				vr.getValidationMessages().add("expected parameter value String[] of length == 1");
				rtnMap.merge(parameterName, vr);
			}
		}
		return rtnMap;
	}

	public ParameterMap validateParameterGroups(Set<Parameters> userParameterSet) {
		ParameterMap rtn = new ParameterMap();
		if (null != userParameterSet) {
			for (Set<Parameters> definedParameterGroup : GROUP_LIST) {
				SortedSet<String> containsSet = new TreeSet<String>();
				SortedSet<String> missingSet = new TreeSet<String>();
				for (Parameters groupParameter : definedParameterGroup) {
					missingSet.add(groupParameter.toString());
					//if the user is querying by on of the parameters in a group, add the group to be checked for completeness.
					if (userParameterSet.contains(groupParameter)) {
						containsSet.add(groupParameter.toString());
					}
				}
				//if the user queried by one, but not all of the set, throw an error.
				if (!containsSet.isEmpty() &&  !userParameterSet.containsAll(definedParameterGroup)) {
					missingSet.removeAll(containsSet);
					ValidationResult<?> vr = new ValidationResult<Object>();
					vr.setValid(false);
					vr.getValidationMessages().add("Parameter(s) " + formatGroupSet(containsSet) + " require(s) " + formatGroupSet(missingSet));
					rtn.merge("Group", vr);
				}
			}
		}
		return rtn;
	}

	private String formatGroupSet(SortedSet<String> containsSet) {
		StringBuilder builder = new StringBuilder();
		Iterator<String> iterator = containsSet.iterator();
		builder.append("'").append(iterator.next()).append("'");
		for (int i = containsSet.size() - 2; i > 0; --i) {
			builder.append(", '").append(iterator.next()).append("'");
		}
		if (iterator.hasNext()) {
			builder.append(" and '").append(iterator.next()).append("'");
		}
		return builder.toString();
	}
}