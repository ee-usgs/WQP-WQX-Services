package gov.usgs.cida.wqp.parameter.transform;

import gov.usgs.cida.wqp.parameter.Parameters;

import java.util.Map;

import org.apache.log4j.Logger;

public class ParameterTransformer {
	private final Logger log = Logger.getLogger(getClass());
    
    private static Map<Parameters, ITransformer> TRANSFORMER_MAP;

    private static ITransformer TRANSFORMER_DEFAULT;

    private ParameterTransformer() {
        log.trace(getClass());
    }

    public static void setTransformerMap(final Map<Parameters, ITransformer> inTransformerMap) {
        TRANSFORMER_MAP = inTransformerMap;
    }
    
    public static ITransformer getTransformer(final Parameters inParameters) {
        return TRANSFORMER_MAP.get(inParameters);
    }
    
    public static void setDefaultTransformer(final ITransformer inDefaultTransformer) {
        TRANSFORMER_DEFAULT = inDefaultTransformer;
    }
    
    public static ITransformer getDefaultTransformer() {
        return TRANSFORMER_DEFAULT;
    }

}
