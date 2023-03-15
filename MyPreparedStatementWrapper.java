package hbv;
import java.util.Calendar;
import java.net.URL;
import java.sql.*;
import java.io.*;
import java.math.*;


public class MyPreparedStatementWrapper extends MyStatementWrapper
	implements PreparedStatement {
	private PreparedStatement orgps;
	private String sql;
	public MyPreparedStatementWrapper(String sql,PreparedStatement org){
		super(org);
		this.sql=sql;
		this.orgps = org;
	}

	public void addBatch() throws SQLException {
		orgps.addBatch();
	}
	public void clearParameters() throws SQLException {
		orgps.clearParameters();
	}
	public boolean execute() throws SQLException {
		return orgps.execute();
	}
	public ResultSet executeQuery() throws SQLException {
		//MyLogger.info("executeQuery:"+this.sql);
		return orgps.executeQuery();
	}
	public int executeUpdate() throws SQLException {
		return orgps.executeUpdate();
	}
	public ResultSetMetaData getMetaData() throws SQLException {
		return orgps.getMetaData();
	}

	public ParameterMetaData getParameterMetaData() throws SQLException {
		return orgps.getParameterMetaData();
	}
	public void setArray(int parameterIndex, Array x) throws SQLException {
		orgps.setArray(parameterIndex,x);
	}
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		orgps.setAsciiStream(parameterIndex,x);
	}
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		orgps.setAsciiStream(parameterIndex,x,length);
	}
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		orgps.setAsciiStream(parameterIndex,x,length);
	}
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		orgps.setBigDecimal(parameterIndex,x);
	}
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		orgps.setBinaryStream(parameterIndex,x);
	}
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		orgps.setBinaryStream(parameterIndex,x,length);
	}
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		orgps.setBinaryStream(parameterIndex,x,length);
	}
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		orgps.setBlob(parameterIndex,inputStream);
	}
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		orgps.setBlob(parameterIndex, inputStream, length);
	}
	public void setBlob(int parameterIndex, Blob x)  throws SQLException {
		orgps.setBlob(parameterIndex,x);
	}
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		orgps.setBoolean(parameterIndex,x);
	}
	public void setByte(int parameterIndex, byte x) throws SQLException {
		orgps.setByte(parameterIndex,x);
	}
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		orgps.setBytes(parameterIndex,x);
	}
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		orgps.setCharacterStream(parameterIndex,reader);
	}
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		orgps.setCharacterStream(parameterIndex,reader,length);
	}
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		orgps.setCharacterStream(parameterIndex,reader,length);
	}
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		orgps.setClob(parameterIndex,reader);
	}
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		orgps.setClob(parameterIndex,reader,length);
	}
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		orgps.setClob(parameterIndex,x);
	}
	public void setDate(int parameterIndex, Date x) throws SQLException {
		orgps.setDate(parameterIndex,x);
	}
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		orgps.setDate(parameterIndex,x,cal);
	}
	public void setDouble(int parameterIndex, double x) throws SQLException {
		orgps.setDouble(parameterIndex,x);
	}
	public void setFloat(int parameterIndex, float x) throws SQLException {
		orgps.setFloat(parameterIndex,x);
	}
	public void setInt(int parameterIndex, int x) throws SQLException {
		orgps.setInt(parameterIndex,x);
	}
	public void setLong(int parameterIndex, long x) throws SQLException {
		orgps.setLong(parameterIndex,x);
	}
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		orgps.setNCharacterStream(parameterIndex,value);
	}
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		orgps.setNCharacterStream(parameterIndex,value,length);
	}
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		orgps.setNClob(parameterIndex,reader);
	}
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		orgps.setNClob(parameterIndex,reader,length);
	}
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		orgps.setNClob(parameterIndex,value);
	}
	public void setNString(int parameterIndex, String value) throws SQLException {
		orgps.setNString(parameterIndex,value);
	}
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		orgps.setNull(parameterIndex,sqlType);
	}
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		orgps.setNull(parameterIndex,sqlType,typeName);
	}
	public void setObject(int parameterIndex, Object x) throws SQLException {
		orgps.setObject(parameterIndex,x);
	}
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		orgps.setObject(parameterIndex,x,targetSqlType);
	}
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		orgps.setObject(parameterIndex,x,targetSqlType,scaleOrLength);
	}
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		orgps.setRef(parameterIndex,x);
	}
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		orgps.setRowId(parameterIndex,x);
	}
	public void setShort(int parameterIndex, short x) throws SQLException {
		orgps.setShort(parameterIndex,x);
	}
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		orgps.setSQLXML(parameterIndex,xmlObject);
	}
	public void setString(int parameterIndex, String x) throws SQLException {
		orgps.setString(parameterIndex,x);
	}
	public void setTime(int parameterIndex, Time x) throws SQLException {
		orgps.setTime(parameterIndex,x);
	}
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		orgps.setTime(parameterIndex,x,cal);
	}
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		orgps.setTimestamp(parameterIndex,x);
	}
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		orgps.setTimestamp(parameterIndex,x,cal);
	}
	@SuppressWarnings("deprecation")
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		orgps.setUnicodeStream(parameterIndex,x,length);
	}
	public void setURL(int parameterIndex, URL x) throws SQLException {
		orgps.setURL(parameterIndex,x);
	}
}
