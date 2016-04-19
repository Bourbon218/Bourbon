package reqattr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletA extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("message", "ñ◊Ç©Ç¡ÇƒÇ‹Ç¡Ç©ÅH");
        
        
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/servlet/reqattr.ServletB");

        dispatcher.forward(request, response);
    }
}
