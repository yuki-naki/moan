package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.UserBean;

public class Query {

	public static boolean hasUser(Connection conn, String name) throws SQLException{

		boolean hasUser = false;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_user WHERE username = ?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			hasUser = true;
		}

		rs.close();
		return hasUser;
	}

	public static UserBean getUser(Connection conn, String name, String pass) throws SQLException{

		UserBean user = null;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_user WHERE username = ? AND password = ?");
		ps.setString(1, name);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			String userId = rs.getString(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			user = new UserBean(userId, username, password);
		}

		rs.close();
		return user;
	}

	public static void insertUser(Connection conn, UserBean user) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("INSERT INTO test_user VALUES (test_user_seq.nextVal, ?, ?)");

		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());

		ps.executeUpdate();
	}

	public static boolean isUserAdmin(Connection conn, String name) throws SQLException{

		boolean isAdmin = false;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_admin WHERE adminName = ?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			isAdmin = true;
		}

		rs.close();

		return isAdmin;
	}
}
