import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OracleServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, 
                       HttpServletResponse response ) 
        throws IOException, ServletException {

		Oracle ora = new Oracle();
		String result = ora.getResult();

        PrintWriter out = response.getWriter();
        out.println("your fortune is " + result);
    }
}

