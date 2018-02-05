package gov.usgs.cida.wqp.dao;

import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import gov.usgs.cida.wqp.parameter.ResultIdentifier;

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

	public int getProjectFiles(ZipOutputStream target, String organization, String projectIdentifier) {
		LOG.trace("Getting file for: organization/{}/project/{}", organization, projectIdentifier);
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from project_object where organization = ? and project_identifier = ? order by object_id",
				new Object[] {organization, projectIdentifier},
				handler);
		return handler.getRowCount();
	}

	public int getMonitoringLocationFiles(ZipOutputStream target, String organization, String monitoringLocation) {
		LOG.trace("Getting file for: organization/{}/monitoringLocation/{}", organization, monitoringLocation);
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from station_object where organization = ? and site_id = ? order by object_id",
				new Object[] {organization, monitoringLocation},
				handler);
		return handler.getRowCount();
	}

	public int getResultFiles(ZipOutputStream target, String organization, String activity, ResultIdentifier resultIdentifier) {
		LOG.trace("Getting file for: organization/{}/activity/{}/result/{}", organization, activity, resultIdentifier.getSingle());
		StreamingCallbackHandler handler = new StreamingCallbackHandler(target);
		jdbcTemplate.query("select object_name, object_content from result_object where organization = ? and activity = ? and data_source = ? and result_id = ? order by object_id",
				new Object[] {organization, activity, resultIdentifier.getDataSource(), resultIdentifier.getResultId()},
				handler);
		return handler.getRowCount();
	}

}
