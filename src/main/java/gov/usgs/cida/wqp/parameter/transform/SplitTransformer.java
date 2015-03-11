package gov.usgs.cida.wqp.parameter.transform;


import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 *
 * @author tkunicki
 */
public class SplitTransformer implements ITransformer {
	private final Logger log = Logger.getLogger(getClass());

    private final Pattern splitPattern;


    public SplitTransformer(String delimiter) {
        log.trace(getClass());
        
        if (null == delimiter || 0 == delimiter.length()) {
            throw new IllegalArgumentException("A Delimiter must be provided.");
        } else {
            splitPattern = Pattern.compile(delimiter);
        }
    }

    @Override
    public Object transform(String value) {
        return split(value);
    }

    protected String[] split(String value) {
        if (null == value || 0 == value.length()) {
            return new String[0];
        } else {
            return splitPattern.split(value, -1);
        }
    }

}
