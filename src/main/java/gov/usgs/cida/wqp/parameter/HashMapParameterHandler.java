package gov.usgs.cida.wqp.parameter;

import gov.usgs.cida.wqp.validation.AbstractValidator;
import gov.usgs.cida.wqp.validation.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;


/**
 * Was QWMapParameterValidator
 * @author drsteini
 *
 */
public class HashMapParameterHandler implements IParameterHandler {
	private final Logger log = Logger.getLogger(getClass());

    private static Map<Parameters, AbstractValidator<?>> VALIDATOR_MAP;

    private static final Set<Parameters> WITHIN_GROUP;
    private static final List<Set<Parameters>> GROUP_LIST;

    
    public static void setValidatorMap(final Map<Parameters, AbstractValidator<?>> inValidatoryMap) {
        VALIDATOR_MAP = inValidatoryMap;
    }

    public static AbstractValidator<?> getValidator(final Parameters inParameters) {
        return VALIDATOR_MAP.get(inParameters);
    }

    static {
        WITHIN_GROUP = new HashSet<Parameters>();
        WITHIN_GROUP.add(Parameters.LATITUDE);
        WITHIN_GROUP.add(Parameters.LONGITUDE);
        WITHIN_GROUP.add(Parameters.WITHIN);
    }
    
    static {
        GROUP_LIST = new ArrayList<Set<Parameters>>();
        GROUP_LIST.add(WITHIN_GROUP);
    }

    public HashMapParameterHandler() {
        log.trace(getClass());
	}
    
    @Override
    public ParameterMap validateAndTransform(final Object inObject) {
        ParameterMap out = new ParameterMap();
        if (inObject instanceof Map<?, ?>) {
            Map<?, ?> inParameters = (Map<?, ?>) inObject;

            Map<String, String[]> temp = pruneParameters(inParameters);

            out = validateParameterNamesAndGroups(temp.keySet());
            if (out.isValid()) {
                out.merge(validateAndTransformParameterValues(temp));
            }
        }

        return out;
    }

    public Map<String, String[]> pruneParameters(final Map<?, ?> inParameters) {
        Map<String, String[]> rtn = new HashMap<String, String[]>();

        Iterator<?> entryIterator = inParameters.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Object entry = entryIterator.next();
            if (entry instanceof Map.Entry<?,?>) {
                Object value = ((Map.Entry<?,?>)entry).getValue();
                Object key = ((Map.Entry<?,?>)entry).getKey();
                if (value instanceof String[] && key instanceof String) {
                    String[] strings = (String[]) value;
                    List<String> nonTrivialValues = new ArrayList<String>();
                    for(String string : strings) {
                        if (string != null && string.length() > 0) {
                            nonTrivialValues.add(string);
                        }
                    }
                    if (!nonTrivialValues.isEmpty()) {
                        rtn.put((String) key, nonTrivialValues.toArray(new String[0]));
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
                if (Parameters.isValid(parameterName)) {
                    Parameters parameter = Parameters.fromName(parameterName);
                    if (!VALIDATOR_MAP.containsKey(parameter) /*&& !Parameters.NON_OUTPUT.contains(parameterName)*/ ) {
                        isOk = false;
                    } else {
                        userParameterSet.add(parameter);
                    }
                } else {
                    isOk = false;
                }
                if (!isOk) {
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
            //untested since the only group defined has but three entries and will never get here.
            builder.append(", '").append(iterator.next()).append("'");
        }
        if (iterator.hasNext()) {
            builder.append(" and '").append(iterator.next()).append("'");
        }
        return builder.toString();
    }

}
