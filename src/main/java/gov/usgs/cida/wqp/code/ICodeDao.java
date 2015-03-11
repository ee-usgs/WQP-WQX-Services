package gov.usgs.cida.wqp.code;

import java.util.List;
import java.util.Map;

public interface ICodeDao {

    List<Code> getCodes(CodeType codeType);
    
    List<Code> getCodes(CodeType codeType, Map<String, Object> parameterMap);
}
