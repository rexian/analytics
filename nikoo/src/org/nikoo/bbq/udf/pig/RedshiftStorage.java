package org.nikoo.bbq.udf.pig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.pig.StoreFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.DataByteArray;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.joda.time.DateTime;

/**
 * RedshiftStorage is a Pig UDF which stores a Pig relation in Redshift table, passed as a parameter while calling the UDF
 * @author Surajit Paul
 * @version 1.0
 * @since Sprint 12
 *
 */
public class RedshiftStorage extends StoreFunc {
	private final Log log = LogFactory.getLog(getClass());

	private PreparedStatement ps;
	private Connection con;  
	private String tableName;
	private int batchSize = 100;
	private int fieldSize = 0;
	private int count = 0;
	private String driver = "org.postgresql.Driver";
	private String jdbcURL = "jdbc:postgresql://HOST:5439/db?tcpKeepAlive=true";
	private String user = "db_user";
	private String pass = "db_pwd";	

	public RedshiftStorage(String tableName) {	 
		this.tableName = tableName;
		this.fieldSize = this.columnCount(tableName);
	}

	public int columnCount(String tableName){
		int columnCount = 0;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(jdbcURL, user, pass);
			ps = con.prepareStatement("select * from "+tableName);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnCount = rsmd.getColumnCount();				
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally{			
			try {
				if(ps != null)
					ps.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {					
				System.err.println(e.getMessage());
			}			
		}	  
		return columnCount;
	}


	/**
	 * Write the tuple to Database directly here.
	 */
	public void putNext(Tuple tuple) throws IOException {
		int sqlPos = 1;
		try {
			int size = tuple.size();
			for (int i = 0; i < size; i++) {
				try {
					Object field = tuple.get(i);
					if(field != null){
						if("\\N".equalsIgnoreCase(field.toString())){
							field = null;
						}	
					}
					

					switch (DataType.findType(field)) {
					case DataType.NULL:
						ps.setNull(sqlPos, java.sql.Types.VARCHAR);
						sqlPos++;
						break;

					case DataType.BOOLEAN:
						ps.setBoolean(sqlPos, (Boolean) field);
						sqlPos++;
						break;

					case DataType.INTEGER:
						ps.setInt(sqlPos, (Integer) field);
						sqlPos++;
						break;

					case DataType.LONG:
						ps.setLong(sqlPos, (Long) field);
						sqlPos++;
						break;

					case DataType.FLOAT:
						ps.setFloat(sqlPos, (Float) field);
						sqlPos++;
						break;

					case DataType.DOUBLE:
						ps.setDouble(sqlPos, (Double) field);
						sqlPos++;
						break;

					case DataType.DATETIME:
						ps.setTimestamp(sqlPos, new Timestamp(((DateTime) field).getMillis()));
						sqlPos++;
						break;

					case DataType.BYTEARRAY:
						byte[] b = ((DataByteArray) field).get();
						ps.setBytes(sqlPos, b);
						sqlPos++;
						break;
					case DataType.CHARARRAY:
						ps.setString(sqlPos, (String) field);
						sqlPos++;
						break;
					case DataType.BYTE:
						ps.setByte(sqlPos, (Byte) field);
						sqlPos++;
						break;

					case DataType.MAP:
					case DataType.TUPLE:
					case DataType.BAG:
						throw new RuntimeException("Cannot store a non-flat tuple "
								+ "using DbStorage");

					default:
						throw new RuntimeException("Unknown datatype "
								+ DataType.findType(field));

					}

				} catch (ExecException ee) {
					throw new RuntimeException(ee);
				}

			}
			ps.addBatch();
			count++;
			if (count > batchSize) {
				count = 0;
				ps.executeBatch();
				ps.clearBatch();
				ps.clearParameters();
			}
		} catch (SQLException e) {
			try {
				log
				.error("Unable to insert record:" + tuple.toDelimitedString("\t"),
						e);
			} catch (ExecException ee) {
				// do nothing
			}
			if (e.getErrorCode() == 1366) {
				// errors that come due to utf-8 character encoding
				// ignore these kind of errors TODO: Temporary fix - need to find a
				// better way of handling them in the argument statement itself
			} else {
				throw new RuntimeException("JDBC error", e);
			}
		}
	}

	class MyDBOutputFormat extends OutputFormat<NullWritable, NullWritable> {

		@Override
		public void checkOutputSpecs(JobContext context) throws IOException,
		InterruptedException {
			// IGNORE
		}

		@Override
		public OutputCommitter getOutputCommitter(TaskAttemptContext context)
				throws IOException, InterruptedException {
			return new OutputCommitter() {

				@Override
				public void abortTask(TaskAttemptContext context) throws IOException {
					try {
						if (ps != null) {
							ps.close();
						}
						if (con != null) {
							con.rollback();
							con.close();
						}
					} catch (SQLException sqe) {
						throw new IOException(sqe);
					}
				}

				@Override
				public void commitTask(TaskAttemptContext context) throws IOException {
					if (ps != null) {
						try {
							ps.executeBatch();
							con.commit();
							ps.close();
							con.close();
							ps = null;
							con = null;
						} catch (SQLException e) {
							log.error("ps.close", e);
							throw new IOException("JDBC Error", e);
						}
					}
				}

				@Override
				public boolean needsTaskCommit(TaskAttemptContext context)
						throws IOException {
					return true;
				}

				@Override
				public void cleanupJob(JobContext context) throws IOException {
					// IGNORE
				}

				@Override
				public void setupJob(JobContext context) throws IOException {
					// IGNORE
				}

				@Override
				public void setupTask(TaskAttemptContext context) throws IOException {
					// IGNORE
				}
			};
		}

		@Override
		public RecordWriter<NullWritable, NullWritable> getRecordWriter(
				TaskAttemptContext context) throws IOException, InterruptedException {
			// We don't use a record writer to write to database
			return new RecordWriter<NullWritable, NullWritable>() {
				@Override
				public void close(TaskAttemptContext context) {
					// Noop
				}
				@Override
				public void write(NullWritable k, NullWritable v) {
					// Noop
				}
			};
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputFormat getOutputFormat()
			throws IOException {
		return new MyDBOutputFormat();
	}

	/**
	 * Initialise the database connection and prepared statement here.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void prepareToWrite(RecordWriter writer)
			throws IOException {
		ps = null;
		con = null;
		String columns = "?";
		for(int fields = 1; fields < fieldSize; fields++){
			columns = columns + ",?";
		}
		String insertQuery = "insert into "+tableName+" values ("+columns+")";
		try {
			if (user == null || pass == null) {
				con = DriverManager.getConnection(jdbcURL);
			} else {
				con = DriverManager.getConnection(jdbcURL, user, pass);
			}
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertQuery);
		} catch (SQLException e) {
			log.error("Unable to connect to JDBC @" + jdbcURL);
			throw new IOException("JDBC Error", e);
		}
		count = 0;
	}

	@Override
	public void setStoreLocation(String location, Job job) throws IOException {}

	@Override
	public void cleanupOnFailure(String location, Job job)
			throws IOException {
		StoreFunc.cleanupOnFailureImpl(location, job);
	}

	@Override
	public void cleanupOnSuccess(String location, Job job)
			throws IOException {
		StoreFunc.cleanupOnFailureImpl(location, job);
	}
}

