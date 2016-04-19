package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("init");
    }
    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        PrintWriter out = response.getWriter();
        out.println("OK");
    }


    public void destroy() {
        System.out.println("destroy");
    }

}
