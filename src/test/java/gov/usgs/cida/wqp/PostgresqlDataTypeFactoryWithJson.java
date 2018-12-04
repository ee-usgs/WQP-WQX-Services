package gov.usgs.cida.wqp;

import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.postgresql.CitextType;
import org.dbunit.ext.postgresql.GenericEnumType;
import org.dbunit.ext.postgresql.GeometryType;
import org.dbunit.ext.postgresql.InetType;
import org.dbunit.ext.postgresql.IntervalType;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.ext.postgresql.UuidType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresqlDataTypeFactoryWithJson extends PostgresqlDataTypeFactory {
	private static final Logger LOG = LoggerFactory.getLogger(PostgresqlDataTypeFactoryWithJson.class);

	@Override
    public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException {
		LOG.debug("createDataType(sqlType={}, sqlTypeName={})",
                String.valueOf(sqlType), sqlTypeName);

        if (sqlType == Types.OTHER)

            if ("json".equals(sqlTypeName)) {
                return new JsonType(); // support PostgreSQL json
            } else if ("uuid".equals(sqlTypeName))
                return new UuidType();
        	// Intervals are custom types
            else if ("interval".equals(sqlTypeName))
            	return new IntervalType();
            else if ("inet".equals(sqlTypeName))
                return new InetType();
            else if("geometry".equals(sqlTypeName))
               return new GeometryType();
            else if("citext".equals(sqlTypeName))
                return new CitextType();
            else
            {
                // Finally check whether the user defined a custom datatype
                if(isEnumType(sqlTypeName))
                {
                    if(LOG.isDebugEnabled())
                    	LOG.debug("Custom enum type used for sqlTypeName {} (sqlType '{}')", 
                                new Object[] {sqlTypeName, new Integer(sqlType)} );
                    return new GenericEnumType(sqlTypeName);
                }
            }

        return super.createDataType(sqlType, sqlTypeName);
    }
}
