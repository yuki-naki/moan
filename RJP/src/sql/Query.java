package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bean.AdminBean;
import bean.ClientBean;
import bean.CommentBean;
import bean.ThreadBean;

public class Query {

	public static Connection conn = ConnectionDB.getConnection();

	public static boolean hasClient(String name) throws SQLException{

		boolean hasClient = false;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_user WHERE username = ?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			hasClient = true;
		}

		rs.close();
		return hasClient;
	}

	public static ClientBean getClient(String name, String pass) throws SQLException{

		ClientBean client = null;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_user WHERE username = ? AND password = ?");
		ps.setString(1, name);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			String userId = rs.getString(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			client = new ClientBean(userId, username, password);
		}

		rs.close();
		return client;
	}

	public static void insertClient(ClientBean user) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("INSERT INTO test_user VALUES (test_user_seq.nextVal, ?, ?)");

		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());

		ps.executeUpdate();
	}

	public static boolean isAdmin(String name) throws SQLException{

		boolean isAdmin = false;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_admin WHERE admin_name = ?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			isAdmin = true;
		}

		rs.close();

		return isAdmin;
	}

	public static AdminBean getAdmin(String name, String pass) throws SQLException{

		AdminBean admin = null;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM test_admin WHERE admin_name = ? AND admin_pass = ?");
		ps.setString(1, name);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			String adminName = rs.getString(1);
			String adminPass = rs.getString(2);
			admin = new AdminBean(adminName, adminPass);
		}

		rs.close();
		return admin;
	}

	public static List<ThreadBean> getThreads(String searchTxt) throws SQLException{

		List<ThreadBean> listThread = new LinkedList<>();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT thread_id, title, creator, TO_CHAR(created_date,'yyyy-mm-dd HH24:MI'),"
				+ " TO_CHAR(last_update,'yyyy-mm-dd HH24:MI'), last_user, reply_nb FROM test_thread "
				+ "WHERE title LIKE '%"+searchTxt+"%' ORDER BY last_update DESC");

		while(rs.next()){
			String threadId = rs.getString(1);
			String title = rs.getString(2);
			String creator = rs.getString(3);
			String created_date = rs.getString(4);
			String last_update = rs.getString(5);
			String last_user = rs.getString(6);
			int reply_nb = rs.getInt(7);

			ThreadBean thread = new ThreadBean(threadId, title, creator, created_date, last_update, last_user, reply_nb);
			listThread.add(thread);
		}

		rs.close();
		stmt.close();
		return listThread;
	}

	public static List<ThreadBean> getThreads() throws SQLException{

		List<ThreadBean> listThread = new LinkedList<>();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT thread_id, title, creator, TO_CHAR(created_date,'yyyy-mm-dd HH24:MI'),"
				+ " TO_CHAR(last_update,'yyyy-mm-dd HH24:MI'), last_user, reply_nb FROM test_thread ORDER BY last_update DESC");

		while(rs.next()){
			String threadId = rs.getString(1);
			String title = rs.getString(2);
			String creator = rs.getString(3);
			String created_date = rs.getString(4);
			String last_update = rs.getString(5);
			String last_user = rs.getString(6);
			int reply_nb = rs.getInt(7);

			ThreadBean thread = new ThreadBean(threadId, title, creator, created_date, last_update, last_user, reply_nb);
			listThread.add(thread);
		}

		rs.close();
		stmt.close();
		return listThread;
	}

	public static List<CommentBean> getComments(String threadId) throws SQLException{

		List<CommentBean> listComment = new LinkedList<>();
		boolean isDeletedBoolean;

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT comment_id, commenter,"
				+ " content, TO_CHAR(created_date,'yyyy-mm-dd HH24:MI'),"
				+ " isDeleted FROM test_comment WHERE thread_id = "+threadId+" ORDER BY created_date");

		while(rs.next()){
			String commentId = rs.getString(1);
			String commenter = rs.getString(2);
			String content = rs.getString(3);
			String created_date = rs.getString(4);
			int isDeletedInt = rs.getInt(5);


			if(isDeletedInt == 1){
				isDeletedBoolean = true;
			}
			else {
				isDeletedBoolean = false;
			}

			CommentBean comment = new CommentBean(commentId, threadId, commenter, content, created_date, isDeletedBoolean);
			listComment.add(comment);
		}

		rs.close();
		stmt.close();
		return listComment;
	}

	public static int insertThread(String username, String title) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("INSERT INTO test_thread VALUES (test_thread_seq.nextVal, ?, ?, SYSDATE, SYSDATE, ?, ?)");

		ps.setString(1, title);
		ps.setString(2, username);
		ps.setString(3, username);
		ps.setInt(4, 0);

		ps.executeUpdate();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT test_thread_seq.currVal FROM dual");

		rs.next();

		int threadId = rs.getInt(1);

		rs.close();
		stmt.close();
		return threadId;
	}

	public static void deleteThreads(String[] checkboxes) throws SQLException{

		String threadIds = "";

		for(int i = 0; i < checkboxes.length; i++){
			threadIds += checkboxes[i];
			if(i < checkboxes.length - 1){
				threadIds += ",";
			}
		}

		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DELETE FROM test_thread WHERE thread_id IN ("+threadIds+") ");
		stmt.close();
	}

	public static void updateThread(int threadId, String username) throws SQLException{

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT reply_nb FROM test_thread WHERE thread_id = "+ threadId);

		rs.next();

		int replyNb = rs.getInt(1);

		rs.close();
		stmt.close();

		PreparedStatement ps = conn.prepareStatement("UPDATE test_thread SET last_update = SYSDATE, last_user='"+username+"', "
				+ "reply_nb = "+(replyNb + 1)+" WHERE thread_id= ?");

		ps.setInt(1, threadId);

		ps.executeUpdate();
	}

	public static void insertComment(int threadId, String username, String content) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM test_comment WHERE thread_id = ?");
		ps.setInt(1, threadId);
		ResultSet rs = ps.executeQuery();
		int commentNb = 0;

		if(rs.next()){
			commentNb = rs.getInt(1);
		}

		rs.close();

		ps = conn.prepareStatement
		("INSERT INTO test_comment VALUES (?, ?, ?, ?, SYSDATE, 0)");

		ps.setInt(1,(commentNb+1) );
		ps.setInt(2, threadId);
		ps.setString(3, username);
		ps.setString(4, content);

		ps.executeUpdate();
	}

	public static void updateComment(int threadId, int commentId, String content) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("UPDATE test_comment SET content='"+content+"', created_date=SYSDATE WHERE comment_id= ?");

		ps.setInt(1, commentId);

		ps.executeUpdate();

		ps = conn.prepareStatement("UPDATE test_thread SET last_update = SYSDATE WHERE thread_id= ?");

		ps.setInt(1, threadId);

		ps.executeUpdate();
	}

	public static void deleteComment(int commentId, String content) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("UPDATE test_comment SET content='"+content+"' WHERE comment_id= ?");

		ps.setInt(1, commentId);

		ps.executeUpdate();

		ps = conn.prepareStatement("UPDATE test_comment SET isDeleted = 1 WHERE comment_id= ?");

		ps.setInt(1, commentId);

		ps.executeUpdate();
	}
}
