package input;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParamListServlet extends HttpServlet {


	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //request.setCharacterEncoding("Windows-31J");

        response.setContentType("text/plain; charset=Windows-31J");
        PrintWriter out = response.getWriter();

        @SuppressWarnings("unchecked")
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                out.println(name + "=" + values[i]);
            }
        }
    }

}
