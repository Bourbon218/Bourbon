package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoughCounterServlet extends HttpServlet {

	private Integer counter = new Integer(0);
	
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int count = counter.intValue();
    	count = count + 1;
    	System.out.println(count);
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException ignore) {}
		counter = new Integer(count);
    	
    	response.setContentType("text/plain; charset=Windows-31J");    	
    	PrintWriter out = response.getWriter();
    	out.println("count=" + count);
    	out.println("thread=" + Thread.currentThread());
    	out.println("instance=" + this);
    }

}
