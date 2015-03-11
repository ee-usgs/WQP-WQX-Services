package gov.usgs.cida.wqp.validation;


import gov.usgs.cida.wqp.code.Code;
import gov.usgs.cida.wqp.code.CodeType;
import gov.usgs.cida.wqp.code.ICodeDao;
import gov.usgs.cida.wqp.parameter.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 *
 * @author drsteini
 */
public class LookupValidator extends AbstractValidator {
	private final Logger log = Logger.getLogger(getClass());

    private final CodeType codeType;
    
    private ICodeDao codeDao;

    public LookupValidator(Parameters inParameter)  {
        this(inParameter, DEFAULT_MIN_OCCURS, IN_CLAUSE_LIMIT, DEFAULT_DELIMITER);
    }
    
    public LookupValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter)  {
        super(inParameter, minOccurs, maxOccurs, delimiter);
        codeType = CodeType.valueOf(parameter.toString().toUpperCase());
    	log.trace(getClass());
    }

    public void setCodeDao(final ICodeDao inCodeDao) {
        codeDao = inCodeDao;
    }

    @Override
    public ValidationResult validate(final String value) {
        ValidationResult vr = new ValidationResult();
        String[] strings = split(value);
        if (strings.length < getMinOccurs() || strings.length > getMaxOccurs()) {
            vr.setValid(false);
            vr.getValidationMessages().add(getErrorMessage(value, IS_NOT_BETWEEN + getMinOccurs() + " and " + getMaxOccurs() + " occurences."));
        }
        
        Map<String, Code> valid = new HashMap<String, Code>();
        List<Code> codeList = codeDao.getCodes(codeType);
        if (codeList != null){
            for (Code c : codeList) {
                if (c != null){
                    valid.put(c.getValue(), c);
                }
            }
        }

        for (String s : strings) {
            if (!valid.containsKey(s)) {
                vr.setValid(false);
                vr.getValidationMessages().add(getErrorMessage(s, "is not in the list of enumerated values"));
            }
        }
        vr.setTransformedValue(strings);
        return vr;
    }

}
