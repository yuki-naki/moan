package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ThreadBean;
import bean.UserBean;
import form.LoginRegisterForm;
import sql.Query;

@WebServlet("/threads")
public class ThreadServlet extends HttpServlet {

	public static final int THREADS_NB = 20;
	private int threadsPage;
	private boolean titleAscSort = true;
	private boolean creatorAscSort = true;
	private boolean replyAscSort = true;
	private boolean createdDateAscSort = true;
	private boolean lastUserAscSort = true;
	private boolean lastUpdateAscSort = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserBean user = (UserBean) request.getAttribute("user");
		LoginRegisterForm registerForm = (LoginRegisterForm) request.getAttribute("registerForm");
		LoginRegisterForm loginForm = (LoginRegisterForm) request.getAttribute("loginForm");

		String searchTxt = request.getParameter("searchInput");

		String titleSort = request.getParameter("titleSort");
		String creatorSort = request.getParameter("creatorSort");
		String replySort = request.getParameter("replySort");
		String createdDateSort = request.getParameter("createdDateSort");
		String lastUserSort = request.getParameter("lastUserSort");
		String lastUpdateSort = request.getParameter("lastUpdateSort");

		String titleSortSearch = request.getParameter("titleSortSearch");
		String creatorSortSearch = request.getParameter("creatorSortSearch");
		String replySortSearch = request.getParameter("replySortSearch");
		String createdDateSortSearch = request.getParameter("createdDateSortSearch");
		String lastUserSortSearch = request.getParameter("lastUserSortSearch");
		String lastUpdateSortSearch = request.getParameter("lastUpdateSortSearch");

		String nextThreads = request.getParameter("next");
		String previousThreads = request.getParameter("back");

		String[] checkboxes = request.getParameterValues("deleteThreadCheckBox");


		List<ThreadBean> threads;

		try {

			if(nextThreads == null && previousThreads == null){
				if(request.getParameter("page") != null){
					threadsPage = Integer.parseInt(request.getParameter("page"));
				}
				else {
					threadsPage = 1;
				}
				request.setAttribute("page", threadsPage);
			}
			else {
				threadsPage = Integer.parseInt(request.getParameter("page"));
			}

			if(checkboxes != null){
				Query.deleteThreads(checkboxes);
			}

			if(searchTxt != null && ! searchTxt.trim().equals("")){
				threads = Query.getThreads(searchTxt);
				request.setAttribute("searchTxt", searchTxt);
			}
			else {
				threads = Query.getThreads();
			}

			if(titleSort != null || titleSortSearch != null){
				if(nextThreads == null && previousThreads == null && titleSortSearch == null){
					titleAscSort = ! titleAscSort;
				}
				creatorAscSort = true;
				replyAscSort = true;
				createdDateAscSort = true;
				lastUserAscSort = true;
				lastUpdateAscSort = true;
				if(titleAscSort){
					Collections.sort(threads, ThreadBean.titleComparator);
				}
				else {
					Collections.sort(threads, Collections.reverseOrder(ThreadBean.titleComparator));
				}
				request.setAttribute("selectedSort", "title");
			}
			else if(creatorSort != null || creatorSortSearch != null){
				if(nextThreads == null && previousThreads == null && creatorSortSearch == null){
					creatorAscSort = ! creatorAscSort;
				}
				titleAscSort = true;
				replyAscSort = true;
				createdDateAscSort = true;
				lastUserAscSort = true;
				lastUpdateAscSort = true;
				if(creatorAscSort){
					Collections.sort(threads, ThreadBean.creatorComparator);
				}
				else {
					Collections.sort(threads, Collections.reverseOrder(ThreadBean.creatorComparator));
				}
				request.setAttribute("selectedSort", "creator");
			}
			else if(replySort != null  || replySortSearch != null){
				if(nextThreads == null && previousThreads == null && replySortSearch == null){
					replyAscSort = ! replyAscSort;
				}
				titleAscSort = true;
				creatorAscSort = true;
				createdDateAscSort = true;
				lastUserAscSort = true;
				lastUpdateAscSort = true;

				if(replyAscSort){
					Collections.sort(threads, ThreadBean.replyComparator);
				}
				else {
					Collections.sort(threads, Collections.reverseOrder(ThreadBean.replyComparator));
				}
				request.setAttribute("selectedSort", "reply");
			}
			else if(createdDateSort != null || createdDateSortSearch != null){
				if(nextThreads == null && previousThreads == null && createdDateSortSearch == null){
					createdDateAscSort = ! createdDateAscSort;
				}
				titleAscSort = true;
				creatorAscSort = true;
				replyAscSort = true;
				lastUserAscSort = true;
				lastUpdateAscSort = true;
				if(createdDateAscSort){
					Collections.sort(threads, ThreadBean.createdDateComparator);
				}
				else {
					Collections.sort(threads, Collections.reverseOrder(ThreadBean.createdDateComparator));
				}
				request.setAttribute("selectedSort", "createdDate");
			}
			else if(lastUserSort != null || lastUserSortSearch != null){
				if(nextThreads == null && previousThreads == null && lastUserSortSearch == null){
					lastUserAscSort = ! lastUserAscSort;
				}
				titleAscSort = true;
				creatorAscSort = true;
				replyAscSort = true;
				createdDateAscSort = true;
				lastUpdateAscSort = true;
				if(lastUserAscSort){
					Collections.sort(threads, ThreadBean.lastUserComparator);
				}
				else {
					Collections.sort(threads, Collections.reverseOrder(ThreadBean.lastUserComparator));
				}
				request.setAttribute("selectedSort", "lastUser");
			}
			else if(lastUpdateSort != null || lastUpdateSortSearch != null){
				if(nextThreads == null && previousThreads == null && lastUpdateSortSearch == null){
					lastUpdateAscSort = ! lastUpdateAscSort;
				}
				titleAscSort = true;
				creatorAscSort = true;
				replyAscSort = true;
				createdDateAscSort = true;
				lastUserAscSort = true;
				if(lastUpdateAscSort){
					Collections.sort(threads, ThreadBean.lastUpdateComparator);
				}
				else {
					Collections.sort(threads, Collections.reverseOrder(ThreadBean.lastUpdateComparator));
				}
				request.setAttribute("selectedSort", "lastUpdate");
			}

			request.setAttribute("titleAscSort", titleAscSort);
			request.setAttribute("creatorAscSort", creatorAscSort);
			request.setAttribute("replyAscSort", replyAscSort);
			request.setAttribute("createdDateAscSort", createdDateAscSort);
			request.setAttribute("lastUserAscSort", lastUserAscSort);
			request.setAttribute("lastUpdateAscSort", lastUpdateAscSort);

			request.setAttribute("threads", threads);
			request.setAttribute("threadsNb", THREADS_NB);
			request.setAttribute("page", threadsPage);
			request.setAttribute("servlet", "/threads");

			if(user != null){
				request.setAttribute("user", user);
				if(registerForm != null){
					request.setAttribute("registerForm", registerForm);
				}
				else {
					request.setAttribute("loginForm", loginForm);
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");

			dispatcher.forward(request, response);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("newThread") != null){
			HttpSession session = request.getSession();
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			UserBean user = (UserBean) session.getAttribute("userSession");

			try {

				int threadId = Query.insertThread(user.getName(), title);
				Query.insertComment(threadId, user.getName(), content);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect(getServletContext().getContextPath()+"/threads");
	}
}
