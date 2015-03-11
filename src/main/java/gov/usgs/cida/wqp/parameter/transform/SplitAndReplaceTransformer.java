package gov.usgs.cida.wqp.parameter.transform;

import org.apache.log4j.Logger;

/**
 *
 * @author tkunicki
 */
public class SplitAndReplaceTransformer extends SplitTransformer {
	private final Logger log = Logger.getLogger(getClass());

    private final String regex;
    private final String replace;

    public SplitAndReplaceTransformer(String delimiter, String regex, String replace) {
        super(delimiter);
    	log.trace(getClass());
        this.regex = regex;
        this.replace = replace;
    }

    @Override
    public Object transform(String value) {
        String[] strings = split(value);
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = strings[i].replaceAll(regex, replace);
        }
        return strings;
    }

}
