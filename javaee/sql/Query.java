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
import servlet.ThreadServlet;

public class Query {

	public static boolean hasClient(Connection conn, String name) throws SQLException{

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

	public static ClientBean getClient(Connection conn, String name, String pass) throws SQLException{

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

	public static void insertClient(Connection conn, ClientBean user) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("INSERT INTO test_user VALUES (test_user_seq.nextVal, ?, ?)");

		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());

		ps.executeUpdate();
	}

	public static boolean isAdmin(Connection conn, String name) throws SQLException{

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

	public static AdminBean getAdmin(Connection conn, String name, String pass) throws SQLException{

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

	public static List<ThreadBean> getThreads(Connection conn, int page) throws SQLException{

		List<ThreadBean> listThread = new LinkedList<>();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM test_thread WHERE thread_id BETWEEN "+((page - 1) * ThreadServlet.THREADS_NB + 1)
				+" AND "+(page * ThreadServlet.THREADS_NB));

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

	public static List<CommentBean> getComments(Connection conn, String threadId) throws SQLException{

		List<CommentBean> listComment = new LinkedList<>();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM test_comment WHERE thread_id = "+threadId+" ORDER BY created_date");

		while(rs.next()){
			String commentId = rs.getString(1);
			String commenter = rs.getString(3);
			String content = rs.getString(4);
			String created_date = rs.getString(5);

			CommentBean comment = new CommentBean(commentId, threadId, commenter, content, created_date);
			listComment.add(comment);
		}

		rs.close();
		stmt.close();
		return listComment;
	}

	public static int insertThread(Connection conn, String username, String title) throws SQLException{

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

	public static void deleteThread(Connection conn, String id) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		("DELETE FROM test_thread WHERE thread_id= ?");

		ps.setString(1,id);

		ps.executeUpdate();
	}

	public static void insertComment(Connection conn,int threadId, String username, String content) throws SQLException{

		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM test_comment WHERE thread_id = ?");
		ps.setInt(1, threadId);
		ResultSet rs = ps.executeQuery();
		int commentNb = 0;

		if(rs.next()){
			commentNb = rs.getInt(1);
		}

		rs.close();

		ps = conn.prepareStatement
		("INSERT INTO test_comment VALUES (?, ?, ?, ?, SYSDATE)");

		ps.setInt(1,(commentNb+1) );
		ps.setInt(2, threadId);
		ps.setString(3, username);
		ps.setString(4, content);

		ps.executeUpdate();
	}

	//deleteチェック
	public static void deleteComment(Connection conn, String id、boolean admin) throws SQLException{

		PreparedStatement ps = conn.prepareStatement
		//("DELETE FROM test_comment WHERE comment_id= ?");
		if(admin){
			("UPDATE test_comment SET content='このコメントは管理者権限により削除されました。'WHERE comment_id= ?");
		}else{
			("UPDATE test_comment SET content='このコメントは削除されました。'WHERE comment_id= ?");
		}

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
