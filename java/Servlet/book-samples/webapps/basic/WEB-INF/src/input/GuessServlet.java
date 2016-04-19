package input;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuessServlet extends HttpServlet {
    
    /**
     * “–‚½‚è‚Ì”š
     */
    private int hit = new Random().nextInt(10);

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String numberStr = request.getParameter("number");
	    int number = Integer.parseInt(numberStr);
      
        response.setContentType("text/plain; charset=Windows-31J");        
        PrintWriter out = response.getWriter();  

        out.println("”šF" + number);
            
        if(hit == number){
            out.println("“–‚½‚èI");
        }else{
            out.println("‚Í‚¸‚ê");
        }        
    }
}
