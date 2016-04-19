package init;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InitParamServlet extends HttpServlet {
		
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain; charset=Windows-31J");
        PrintWriter out = response.getWriter();

        out.println("サーブレット初期化パラメータ");
        ServletConfig config = getServletConfig();
        @SuppressWarnings("unchecked")
        Enumeration<String> names = config.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = config.getInitParameter(name);
            out.println(name + "=" + value);
        }

    }

}
