package wiki;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String content = request.getParameter("content");
				
		try {
			WikiPage wikiPage = new WikiPage();
			wikiPage.setName(name);
			wikiPage.setContent(content);

			WikiPageDAO.getInstance().insert(wikiPage);
			RequestUtils.setMessage(request,  name + "ÇçÏê¨ÇµÇ‹ÇµÇΩ");
			
			request.getRequestDispatcher("/refer")
				.forward(request, response);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
