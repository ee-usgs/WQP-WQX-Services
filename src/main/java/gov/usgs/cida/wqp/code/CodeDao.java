package gov.usgs.cida.wqp.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CodeDao extends SqlSessionDaoSupport implements ICodeDao {
	private final Logger log = Logger.getLogger(getClass());

    public CodeDao() {
        log.trace(getClass());
	}
    
    @Override
    public List<Code> getCodes(final CodeType codeType, final Map<String, Object> parameterMap) {
        return getSqlSession().selectList(codeType.getListMapperSelectID(), parameterMap);
    }

    @Override
    public List<Code> getCodes(final CodeType codeType) {
        return getCodes(codeType, new HashMap<String, Object>());
    }

}
