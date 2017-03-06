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
import bean.UserBean;
import form.LoginRegisterForm;
import sql.Query;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {

	private String threadId;
	private String title;
	public static final int COMMENTS_NB = 30;
	private int commentsPage;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserBean user = (UserBean) request.getAttribute("user");
		LoginRegisterForm registerForm = (LoginRegisterForm) request.getAttribute("registerForm");
		LoginRegisterForm loginForm = (LoginRegisterForm) request.getAttribute("loginForm");

		String nextComments = request.getParameter("next");
		String previousComments = request.getParameter("back");

		if(request.getParameter("threadId") != null){
			threadId = request.getParameter("threadId");
		}
		if(request.getParameter("title") != null){
			title = request.getParameter("title");
		}

		try {

			if(nextComments == null && previousComments == null){
				if(request.getParameter("page") != null){
					commentsPage = Integer.parseInt(request.getParameter("page"));
				}
				else {
					commentsPage = 1;
				}
				request.setAttribute("page", commentsPage);
			}
			else {
				commentsPage = Integer.parseInt(request.getParameter("page"));
			}

			if(user != null){
				request.setAttribute("user", user);
				if(registerForm != null){
					request.setAttribute("registerForm", registerForm);
				}
				else {
					request.setAttribute("loginForm", loginForm);
				}
			}

			List<CommentBean> comments = Query.getComments(threadId);

			request.setAttribute("comments", comments);
			request.setAttribute("title", title);
			request.setAttribute("threadId", threadId);
			request.setAttribute("servlet", "/comments");
			request.setAttribute("commentsNb", COMMENTS_NB);
			request.setAttribute("page", commentsPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/comment.jsp");
			dispatcher.forward(request, response);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("newComment") != null){

			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("userSession");
			if(user != null){

				try {

					int threadId = Integer.parseInt(request.getParameter("threadId"));
					String content = request.getParameter("content").replace("\n", "<br>");
					Query.insertComment(threadId, user.getName(), content);
					Query.updateThread(threadId, user.getName());

					request.setAttribute("title", title);
					request.setAttribute("threadId", threadId);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if(request.getParameter("updateComment") != null){

			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("userSession");
			if(user != null){

				try {
					int commentId = Integer.parseInt(request.getParameter("commentId"));
					int threadId = Integer.parseInt(request.getParameter("threadId"));
					String content = request.getParameter("content").replace("\n", "<br>");
					Query.updateComment(threadId, commentId, content);

					request.setAttribute("title", title);
					request.setAttribute("threadId", threadId);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if(request.getParameter("deleteComment") != null){

			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("userSession");
			if(user != null){

				try {
					int commentId = Integer.parseInt(request.getParameter("commentId"));
					String content;
					if(user.getIsAdmin()){
						content = "このコメントはアドミンに消された。";
					}
					else {
						content = "このコメントはユーザーに消された。";
					}
					Query.deleteComment(commentId, content);

					request.setAttribute("title", title);
					request.setAttribute("threadId", threadId);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		response.sendRedirect(getServletContext().getContextPath()+"/comments");
	}
}
