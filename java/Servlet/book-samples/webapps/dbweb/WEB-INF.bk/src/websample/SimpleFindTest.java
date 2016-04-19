package websample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleFindTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Account> list = DBManager3.simpleFind("SELECT * FROM ACCOUNT",
					new AccountMapping());
			request.setAttribute("list", list);
			request.getRequestDispatcher("/websample/select.jsp")
				.forward(request, response);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
