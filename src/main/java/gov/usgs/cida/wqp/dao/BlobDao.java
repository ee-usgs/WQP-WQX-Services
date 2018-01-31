package gov.usgs.cida.wqp.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
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

	public static final String PROJECT_OBJECT_NAMESPACE = "projectObject";
	private static final int DEFAULT_BUFFER_SIZE = 1024;
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

	public void getProjectFiles(ZipOutputStream target, String organization, String projectIdentifier) {
		LOG.trace("Getting file for: organization/{}/project/{}", organization, projectIdentifier);
		jdbcTemplate.query("select object_name, object_content from project_object where organization = ? and project_identifier = ? order by object_id",
				new Object[] {organization, projectIdentifier}, (resultSet) -> {
			streamFile(resultSet, target);
		});
	}

	public void getMonitoringLocationFiles(ZipOutputStream target, String organization, String monitoringLocation) {
		LOG.trace("Getting file for: organization/{}/monitoringLocation/{}", organization, monitoringLocation);
		jdbcTemplate.query("select object_name, object_content from station_object where organization = ? and site_id = ? order by object_id",
				new Object[] {organization, monitoringLocation}, (resultSet) -> {
			streamFile(resultSet, target);
		});
	}

	public void getResultFiles(ZipOutputStream target, String organization, String activity, ResultIdentifier resultIdentifier) {
		LOG.trace("Getting file for: organization/{}/activity/{}/result/{}", organization, activity, resultIdentifier.getSingle());
		jdbcTemplate.query("select object_name, object_content from result_object where organization = ? and activity = ? and data_source = ? and result_id = ? order by object_id",
				new Object[] {organization, activity, resultIdentifier.getDataSource(), resultIdentifier.getResultId()}, (resultSet) -> {
			streamFile(resultSet, target);
		});
	}

	private void streamFile(ResultSet resultSet, ZipOutputStream target) {
		try {
			target.putNextEntry(new ZipEntry(resultSet.getString("OBJECT_NAME")));
			InputStream content = resultSet.getBinaryStream("OBJECT_CONTENT");
			byte[] chunk = new byte[DEFAULT_BUFFER_SIZE];
			int count;
			while ((count = content.read(chunk)) >= 0) {
				target.write(chunk, 0, count);
			}
			target.closeEntry();
		} catch (IOException | SQLException e) {
			LOG.error("Error writing file to zip output stream", e);
		}
	}

}
