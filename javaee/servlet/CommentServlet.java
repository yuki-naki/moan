package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CommentBean;
import sql.ConnectionDB;
import sql.Query;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {

	private String threadId;
	private String title;

	public CommentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("threadId") != null){
			threadId = request.getParameter("threadId");
		}
		if(request.getParameter("title") != null){
			title = request.getParameter("title");
		}

		if(ConnectionDB.openConnection()){

			try {

				List<CommentBean> comments = Query.getComments(ConnectionDB.getConnection(), threadId);
				ConnectionDB.closeConnection();

				request.setAttribute("comments", comments);
				request.setAttribute("title", title);
				request.setAttribute("threadId", threadId);
				request.setAttribute("servlet", "/comments");

				//RequestDispatcher dispatcher = request.getRequestDispatcher("/"+id+".jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/comment.jsp");
				dispatcher.forward(request, response);
			}
			catch (SQLException e) {
				e.printStackTrace();
				ConnectionDB.closeConnection();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("newComment") != null){

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("userSession");
			if(username != null){

				if(ConnectionDB.openConnection()){

					try {

						int threadId = Integer.parseInt(request.getParameter("threadId"));
						String content = request.getParameter("content");
						Query.insertComment(ConnectionDB.getConnection(), threadId, username, content);
						Query.updateThread(ConnectionDB.getConnection(), threadId, username);
						ConnectionDB.closeConnection();

						request.setAttribute("title", title);
						request.setAttribute("threadId", threadId);

						doGet(request, response);
					}
					catch (SQLException e) {
						e.printStackTrace();
						ConnectionDB.closeConnection();
					}
				}
			}
			else {
				doGet(request, response);
			}
		}
	}
}
