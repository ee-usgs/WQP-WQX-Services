package gov.usgs.wma.wqp;

import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresqlDataTypeFactoryWithJson extends PostgresqlDataTypeFactory {
	private static final Logger LOG = LoggerFactory.getLogger(PostgresqlDataTypeFactoryWithJson.class);

	@Override
	public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException {
		LOG.debug("createDataType(sqlType={}, sqlTypeName={})", String.valueOf(sqlType), sqlTypeName);

		if (sqlType == Types.OTHER && ("json".equals(sqlTypeName) || "jsonb".equals(sqlTypeName))) {
			return new JsonType(); // support PostgreSQL json
		} else {
			return super.createDataType(sqlType, sqlTypeName);
		}
	}
}
