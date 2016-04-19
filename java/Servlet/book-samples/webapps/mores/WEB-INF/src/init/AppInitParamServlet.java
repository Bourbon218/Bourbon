package init;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppInitParamServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain; charset=Windows-31J");
        PrintWriter out = response.getWriter();

        out.println("アプリケーション初期化パラメータ");
        ServletContext context = getServletContext();
        @SuppressWarnings("unchecked")
        Enumeration<String> names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = context.getInitParameter(name);
            out.println(name + "=" + value);
        }
    }

}
