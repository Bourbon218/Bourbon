package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThreadThinkServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	response.setContentType("text/plain; charset=Windows-31J");
    	response.setIntHeader("Refresh", 5);
    	response.setHeader("Connection", "close");
    	
    	PrintWriter out = response.getWriter();
    	out.println("thread=" + Thread.currentThread());
    	out.println("instance=" + this);
    }

}
