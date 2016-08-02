package gov.usgs.cida.wqp.springinit;

import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import gov.usgs.cida.wqp.dao.WqpArrayTypeHandler;
import gov.usgs.cida.wqp.util.MybatisConstants;

public class MybatisConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setCallSettersOnNulls(true);
		config.setCacheEnabled(false);
		config.setLazyLoadingEnabled(false);
		config.setAggressiveLazyLoading(false);
		config.setMapUnderscoreToCamelCase(true);

		registerAliases(config.getTypeAliasRegistry());

		return config;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setConfiguration(mybatisConfig());
		sqlSessionFactory.setDataSource(dataSource);
		Resource[] mappers = new PathMatchingResourcePatternResolver().getResources(MybatisConstants.MYBATIS_MAPPERS);
		sqlSessionFactory.setMapperLocations(mappers);
		return sqlSessionFactory;
	}

	private void registerAliases(TypeAliasRegistry registry) {
		registry.registerAlias("LinkedHashMap", LinkedHashMap.class);
		registry.registerAlias("WqpArrayTypeHandler", WqpArrayTypeHandler.class);
	}

}
