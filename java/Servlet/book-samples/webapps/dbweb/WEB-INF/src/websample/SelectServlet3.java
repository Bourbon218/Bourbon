package websample;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectServlet3 extends HttpServlet{

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
                
        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.findAll();
        
		request.setAttribute("list", list);	
                
        request.getRequestDispatcher("/websample/select.jsp")
		    .forward(request, response);
    }
}
