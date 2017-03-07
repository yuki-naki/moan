package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String servlet = request.getParameter("servlet");
		String page = request.getParameter("page");
		session.invalidate();
		//response.sendRedirect(request.getContextPath() + servlet);
		request.setAttribute("page", page);
		request.getRequestDispatcher(servlet).forward(request, response);
	}
}
