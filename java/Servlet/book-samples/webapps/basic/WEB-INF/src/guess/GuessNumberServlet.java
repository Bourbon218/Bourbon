package guess;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GuessNumberServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        
        String inputText = request.getParameter("number");
        
        GuessNumber guess = GuessNumber.getInstanceFromSession(request);

        guess.setInput(inputText);
        String fowardPath = process(guess);        
        RequestDispatcher dispatcher = request.getRequestDispatcher(fowardPath);
        dispatcher.forward(request, response);
    }

    
    private String process(GuessNumber guess){
        if(guess.isInputError()){
           return "/guess/entry.jsp";
        }
        
        guess.challenge();
        
        if(guess.isAllHit()){
            return "/guess/atari.jsp";
        }else{
            return "/guess/entry.jsp";
        }            
    }
    
    
}
