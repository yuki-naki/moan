package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	// JDBC driver name, database URL, username and password
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "info";
	private static final String PASS = "pro";

	private static Connection conn = null;

	public static boolean openConnection(){

		boolean result = true;

		try{
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

		}
		// Register JDBC driver error
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
			result = false;
		}
		// Open connection error
		catch(SQLException ex){
			ex.printStackTrace();
			result = false;
		}

		return result;
	}

	public static boolean closeConnection(){

		boolean result = true;

		try {
			conn.close();
		}
		catch (SQLException ex){
			result = false;
			ex.printStackTrace();
		}

		return result;
	}

	public static Connection getConnection(){
		return conn;
	}
}
