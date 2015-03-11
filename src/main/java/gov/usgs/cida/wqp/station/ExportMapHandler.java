package gov.usgs.cida.wqp.station;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;


/**
 * @author drsteini
 *
 */
public class ExportMapHandler implements TypeHandler<ExportMap> {

    /** 
     * {@inheritDoc}
     * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, ExportMap parameter, JdbcType jdbcType) throws SQLException {
        // Not necessary for WQP so not implemented.
    }

    /** 
     * {@inheritDoc}
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
     */
    @Override
    public ExportMap getResult(ResultSet rs, int columnIndex) throws SQLException {
        ExportMap export = new ExportMap();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            export.put(rs.getMetaData().getColumnName(i), nullCheckValue(rs.getObject(i)));
        }

        return export;
    }

    /** 
     * {@inheritDoc}
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
     */
    @Override
    public ExportMap getResult(CallableStatement cs, int columnIndex) throws SQLException {
        //  Not necessary for WQP so not implemented.
        return null;
    }

    /** 
     * {@inheritDoc}
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public ExportMap getResult(ResultSet rs, String columnName) throws SQLException {
        ExportMap export = new ExportMap();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            export.put(rs.getMetaData().getColumnName(i), nullCheckValue(rs.getObject(i)));
        }

        return export;
    }

    private Object nullCheckValue(Object value) {
        return null == value ? "" : value;
    }

}
