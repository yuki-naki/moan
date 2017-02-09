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

import bean.ThreadBean;
import sql.ConnectionDB;
import sql.Query;

@WebServlet("/ThreadServlet")
public class ThreadServlet extends HttpServlet {

    public ThreadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ThreadBean> threads;

		if(ConnectionDB.openConnection()){

			try {
				threads = Query.getThreads(ConnectionDB.getConnection());

				request.setAttribute("threads", threads);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/thread.jsp");

				dispatcher.forward(request, response);
			}
			catch (SQLException e) {
				e.printStackTrace();
				ConnectionDB.closeConnection();
			}
		}
	}
}
