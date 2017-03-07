package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import form.LoginRegisterForm;

@WebServlet("/LoginRegisterServlet")
public class LoginRegisterServlet extends HttpServlet {

	public static final String USER = "user";
	public static final String REGISTER_FORM = "registerForm";
    public static final String LOGIN_FORM = "loginForm";
	public static final String LOGIN_REGISTER_SERVLET = "loginRegisterServlet";
	public static final String USER_SESSION = "userSession";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String register = request.getParameter("register");
		String servlet = request.getParameter("servlet");
		String page = request.getParameter("page");

		LoginRegisterForm form = new LoginRegisterForm();
		UserBean user;

		if(register != null){
			user = form.clientRegister(request);
			request.setAttribute(REGISTER_FORM, form);
		}
		else {
			user = form.userLogin(request);
			request.setAttribute(LOGIN_FORM, form);
		}

		if(form.getErrors().isEmpty()) {
            session.setAttribute(USER_SESSION, user);
        }
		else {
            session.setAttribute(USER_SESSION, null);
        }

		request.setAttribute("page", page);
		request.setAttribute(USER, user);

		//response.sendRedirect(getServletContext().getContextPath()+servlet);
		request.getRequestDispatcher(servlet).forward(request, response);
	}
}