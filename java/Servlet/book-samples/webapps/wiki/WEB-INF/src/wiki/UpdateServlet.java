package wiki;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String cmd = request.getParameter("cmd");
		
		try {
			WikiPage wikiPage = new WikiPage();
			wikiPage.setName(name);
			wikiPage.setContent(content);

			if(cmd.equals("update")){
				WikiPageDAO.getInstance().update(wikiPage);				
				RequestUtils.setMessage(request, name + "ÇçXêVÇµÇ‹ÇµÇΩ");
				request.getRequestDispatcher("/refer")
				.forward(request, response);

			}else if(cmd.equals("delete")){
				WikiPageDAO.getInstance().delete(wikiPage);	
				RequestUtils.setMessage(request, name + "ÇçÌèúÇµÇ‹ÇµÇΩ");
				request.getRequestDispatcher("/list")
				.forward(request, response);

			}else{
				request.getRequestDispatcher("/refer")
				.forward(request, response);				
			}
						

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
