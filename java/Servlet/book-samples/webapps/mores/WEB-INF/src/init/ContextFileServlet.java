package init;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextFileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext application = getServletContext();
        String path = application.getRealPath("/WEB-INF/init.properties");
        
        InputStream in = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(in);
        in.close();

        response.setContentType("text/plain; charset=Windows-31J");
        PrintWriter out = response.getWriter();
        
        out.println("path=" + path);

        prop.list(out);
    }

}
