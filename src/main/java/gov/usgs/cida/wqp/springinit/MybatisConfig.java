package gov.usgs.cida.wqp.springinit;

import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import gov.usgs.cida.wqp.dao.WildCardTypeHandler;

@Configuration
public class MybatisConfig {

	@Autowired
	DataSource dataSource;
	public static final String MYBATIS_MAPPERS = "mybatis/*.xml";

	@Bean
	public org.apache.ibatis.session.Configuration mybatisConfiguration() {
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
		sqlSessionFactory.setConfiguration(mybatisConfiguration());
		sqlSessionFactory.setDataSource(dataSource);
		Resource[] mappers = new PathMatchingResourcePatternResolver().getResources(MybatisConfig.MYBATIS_MAPPERS);
		sqlSessionFactory.setMapperLocations(mappers);
		return sqlSessionFactory;
	}

	private void registerAliases(TypeAliasRegistry registry) {
		registry.registerAlias("LinkedHashMap", LinkedHashMap.class);
//TODO		registry.registerAlias("WqpArrayTypeHandler", WqpArrayTypeHandler.class);
		registry.registerAlias("WildCardTypeHandler", WildCardTypeHandler.class);
	}

}
