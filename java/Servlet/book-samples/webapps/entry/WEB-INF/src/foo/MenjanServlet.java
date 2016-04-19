package foo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MenjanServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=Windows-31J");
        PrintWriter out = response.getWriter();

        String s = "<HTML>"
        		+ "<HEAD>"
                + "<TITLE>AI面雀</TITLE>" 
                + "</HEAD>" 
                + "<BODY>" 
                //+ "<H2>シャー専用　おはよう</H2>"
                + "<H2>" + new Menjan().getWord()+ "</H2>" 
                + "</BODY>" 
                + "</HTML>"
                ;

        out.println(s);
    }
}
