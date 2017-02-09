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
	
	
	//Threadチェック
	//Insertチェック
	public static void insertThread(Connection conn, ThreadBean thread) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("INSERT INTO test_thread VALUES (test_thread_seq.nextVal, ?, ?, SYSDATE, SYSDATE, ?, ?)");

		ps.setString(1, thread.getTitle());
		ps.setString(2, thread.getCreator());
		ps.setString(3, thread.getLastUser());
		ps.setInt(4, thread.getReplyNb());

		ps.executeUpdate();
	}
	//deleteチェック
	public static void deleteThread(Connection conn, String id) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("DELETE FROM test_thread WHERE thread_id= ?");

		ps.setString(1,id);

		ps.executeUpdate();
	}
	//updateチェック
	public static void updateThread(Connection conn, String id) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("UPDATE test_thread SET last_user='Oikawa' WHERE thread_id= ?");
		
		ps.setString(1,id);

		ps.executeUpdate();
	}
	
	//CommentCheck
	//insertチェック
	public static void insertComment(Connection conn, CommentBean comment) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM test_comment WHERE thread_id = ?");
		ps.setString(1, comment.getThreadId());
		ResultSet rs = ps.executeQuery();
		int commentNb = 0;

		if(rs.next()){
			commentNb = rs.getInt(1);
		}

		rs.close();

		ps = conn.prepareStatement
		("INSERT INTO test_comment VALUES (?, ?, ?, ?, SYSDATE)");
		
		ps.setInt(1,(commentNb+1) );
		ps.setString(2, comment.getThreadId());
		ps.setString(3, comment.getCommenter());
		ps.setString(4, comment.getContent());

		ps.executeUpdate();
	}
	
	//deleteチェック
	public static void deleteComment(Connection conn, String id) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("DELETE FROM test_comment WHERE comment_id= ?");

		ps.setString(1,id);

		ps.executeUpdate();
	}
	
	//updateチェック
	public static void updateComment(Connection conn, String id) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("UPDATE test_comment SET content='aiaiaa' WHERE comment_id= ?");
		
		ps.setString(1,id);

		ps.executeUpdate();
	}
}
