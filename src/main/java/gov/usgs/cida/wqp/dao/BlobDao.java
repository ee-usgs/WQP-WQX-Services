package gov.usgs.cida.wqp.dao;

import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlobDao {
	private static final Logger LOG = LoggerFactory.getLogger(BlobDao.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BlobDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getProjectFiles(ZipOutputStream target, String provider, String organization, String projectIdentifier) {
		LOG.trace("Getting file for: providers/{}/organizations/{}/projects/{}", provider, organization, projectIdentifier);
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from project_object where data_source = ? and organization = ? and project_identifier = ? order by object_id",
				new Object[] {provider, organization, projectIdentifier},
				handler);
		return handler.getRowCount();
	}

	public int getMonitoringLocationFiles(ZipOutputStream target, String provider, String organization, String monitoringLocation) {
		LOG.trace("Getting file for: providers/{}/organizations/{}/monitoringLocation/{}", provider, organization, monitoringLocation);
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from station_object where data_source = ? and organization = ? and site_id = ? order by object_id",
				new Object[] {provider, organization, monitoringLocation},
				handler);
		return handler.getRowCount();
	}

	public int getResultFiles(ZipOutputStream target, String provider, String organization, String activity, String resultId) {
		LOG.trace("Getting file for: providers/{}/organizations/{}/activities/{}/results/{}", provider, organization, activity, resultId);
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from result_object where data_source = ? and organization = ? and activity = ? and result_id = ?::int order by object_id",
			new Object[] {provider, organization, activity, resultId},
			handler);
		return handler.getRowCount();
	}

	public int getActivityFiles(ZipOutputStream target, String provider, String organization, String activity) {
		LOG.trace("Getting file for: providers/{}/organizations/{}/activities/{}", provider, organization, activity);
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from activity_object where data_source = ? and  organization = ? and activity = ? order by object_id",
				new Object[] {provider, organization, activity},
				handler);
		return handler.getRowCount();
	}

}
