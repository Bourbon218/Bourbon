package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationCounterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext application = getServletContext();

        Integer count = (Integer) application.getAttribute("count");

        if (count == null) {
            count = new Integer(0);
        }
        count = new Integer(count.intValue() + 1);

        application.setAttribute("count", count);



        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.print("<p>count=" + count);
        String linkUrl = request.getRequestURI();
        out.println("<p><a href=\"" + linkUrl
                + "\">RELOAD</a>");
        out.println("</body></html>");
    }

}
