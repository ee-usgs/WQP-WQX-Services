package gov.usgs.cida.wqp.station;

import java.util.LinkedHashMap;


/**
 * Generic extension to LinkedHashMap so we can intercept them in Mybatis and guarantee the map has the 
 * correct number of entries in the correct positions for the exports to work correctly.  (Mybatis currently
 * drops entries if the value is null.) 
 * @author drsteini
 *
 */
public class ExportMap extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = -4627386265399891290L;
}
