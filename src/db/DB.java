package db;

import java.sql.*;

public class DB {
	private static final String url = "jdbc:mysql://localhost:3306/coursejdbc";
	private static final String user = "developer";
	private static final String password = "8922";

	private static Connection conn = null;

	public static Connection getConn(){
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return conn;
	}

	public static void closeConn(){
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void closeStatement (Statement st) {
		if (st != null) {
			try{
				st.close();
			}
			catch (SQLException e){
				throw new RuntimeException(e);
			}

		}
	}

	public static void closeResultSet (ResultSet rs) {
		if (rs != null) {
			try{
				rs.close();
			}
			catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
