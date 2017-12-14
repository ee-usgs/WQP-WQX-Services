package gov.usgs.cida.wqp.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class WqpArrayTypeHandler extends BaseTypeHandler<List<String>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
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
		Array array = new ARRAY(descriptor, c, parameter.toArray());
		ps.setArray(i, array);
	}

	@Override
	public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		//Not used for WQP
		return null;
	}

	@Override
	public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		//Not used for WQP
		return null;
	}

	@Override
	public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		//Not used for WQP
		return null;
	}

}
