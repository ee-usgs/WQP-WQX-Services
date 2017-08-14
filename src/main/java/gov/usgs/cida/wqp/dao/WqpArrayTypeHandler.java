package gov.usgs.cida.wqp.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.ArrayTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class WqpArrayTypeHandler extends ArrayTypeHandler {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null || !(parameter instanceof String[])) {
			throw new IllegalArgumentException("parameter is invalid, must be instance of String[] with length > 0");
		}

		Connection c = ps.getConnection();
		if (c instanceof DelegatingConnection) {
			Connection pooledConnection = c;
			c = ((DelegatingConnection<?>)c).getInnermostDelegate();
			if (c == null) {
				throw new RuntimeException("Database resource pool configuration error: " + pooledConnection.getClass().getName());
			}
		}

		//drsteini - added to support junit testing
		if (c.isWrapperFor(oracle.jdbc.OracleConnection.class)) {
			c = c.unwrap(oracle.jdbc.OracleConnection.class);
		}

		ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("TYP_VCTBL", c);
		Array array = new ARRAY(descriptor, c, parameter);
		ps.setArray(i, array);
	}

}
