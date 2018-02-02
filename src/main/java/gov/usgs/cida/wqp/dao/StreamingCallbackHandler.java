package gov.usgs.cida.wqp.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowCountCallbackHandler;

public class StreamingCallbackHandler extends RowCountCallbackHandler {
	private static final Logger LOG = LoggerFactory.getLogger(StreamingCallbackHandler.class);

	private static final int DEFAULT_BUFFER_SIZE = 1024;
	private ZipOutputStream target;

	public StreamingCallbackHandler(ZipOutputStream target) {
		this.target = target;
	}

	@Override
	public void processRow(ResultSet resultSet, int rowNum) {
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
