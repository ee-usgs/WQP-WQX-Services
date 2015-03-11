package gov.usgs.cida.wqp.code;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

@XmlRootElement (name = "Code")
@XmlType(propOrder = {"value", "desc"})
public class Code implements Serializable {
	private final Logger log = Logger.getLogger(getClass());

    private static final long serialVersionUID = 7174466649683434137L;

    protected String value;
    protected String desc;

    public Code() {
        log.trace(getClass());
    }
    
    public Code(String inValue, String inDesc) {
        log.trace(getClass());
        
        value = inValue;
        desc = inDesc;
    }


    @XmlAttribute (name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(final String inValue) {
        value = inValue;
    }

    @XmlAttribute (name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(final String inDesc) {
        desc = inDesc;
    }

    @Override
    public String toString() {
        return "(value=" + getValue() + ", desc=" + getDesc() + ")";
    }

}
