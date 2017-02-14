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

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String uri =	request.getScheme() + "://" +   // "http" + "://
						request.getServerName() +       // "myhost"
						":" +                           // ":"
						request.getServerPort() +       // "8080"
						request.getContextPath();       // "/RJP"
						session.getAttribute("jsp");

		session.invalidate();
		response.sendRedirect(uri);
	}
}
