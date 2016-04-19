import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloHtmlServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, 
		HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html; charset=Windows-31J");
		//response.setContentType("text/html");
	    //response.setCharacterEncoding("Windows-31J");

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>HTMLサーブレット</TITLE>");
        out.println("</HEAD>");
		out.println("<BODY>");
		out.println("こんにちわ");
		out.println("</BODY>");
		out.println("</HTML>");
	}
}
