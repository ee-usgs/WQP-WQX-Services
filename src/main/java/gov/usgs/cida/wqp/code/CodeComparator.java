package gov.usgs.cida.wqp.code;


import java.io.Serializable;
import java.util.Comparator;

import org.apache.log4j.Logger;

public class CodeComparator implements Serializable, Comparator<Code> {
	private final Logger log = Logger.getLogger(getClass());

    private static final long serialVersionUID = -1818803002136763012L;

    public CodeComparator() {
        log.trace(getClass());
	}
    
    @Override
    public int compare(Code code1, Code code2) {
        if (code1 == null) {
            return (code2 == null)? 0: 1;
        }
        if (code2 == null) {
            return -1;
        }
        String desc1 = (code1.getDesc() != null && !code1.getDesc().isEmpty())? code1.getDesc(): (code1.getValue() != null)? code1.getValue(): "";
        String desc2 = (code2.getDesc() != null && !code2.getDesc().isEmpty())? code2.getDesc(): (code2.getValue() != null)? code2.getValue(): "";
        int result = desc1.compareTo(desc2);
        if (result != 0) {
            return result;
        }
        String value1 = (code1.getValue() != null)? code1.getValue(): "";
        String value2 = (code2.getValue() != null)? code2.getValue(): "";
        return value1.compareTo(value2);
    }

}
