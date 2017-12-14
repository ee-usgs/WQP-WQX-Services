package gov.usgs.cida.wqp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Component
public class CountDao extends BaseDao implements ICountDao {
	private static final Logger LOG = LoggerFactory.getLogger(CountDao.class);

	public static final String QUERY_SELECT_ID = ".count";

	@Autowired
	public CountDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	@Override
	public List<Map<String, Object>> getCounts(NameSpace nameSpace, FilterParameters filter) {
		LOG.trace("Getting counts for: {}", nameSpace);
		FilterParameters adjustedFilter = adjustMinCount(nameSpace, filter);
		return getSqlSession().selectList(nameSpace + QUERY_SELECT_ID, adjustedFilter);
	}

	protected FilterParameters adjustMinCount(NameSpace nameSpace, FilterParameters filter) {
		//We need to set a minimum record count so sites without activities/results are excluded from counts for those end points.
		if (null != nameSpace && null != nameSpace.getAdjustParameter()) {
			FilterParameters adjustedFilter = new FilterParameters();
			if (null != filter) {
				BeanUtils.copyProperties(filter, adjustedFilter);
			}
			switch (nameSpace.getAdjustParameter()) {
			case MIN_ACTIVITIES:
				if (StringUtils.isBlank(adjustedFilter.getMinactivities())) {
					adjustedFilter.setMinactivities("1");
				}
				break;
			case MIN_RESULTS:
				if (StringUtils.isBlank(adjustedFilter.getMinresults())) {
					adjustedFilter.setMinresults("1");
				}
				break;
			default:
				break;
			}
			return adjustedFilter;
		} else {
			return filter;
		}
	}

}
