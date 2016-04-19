package dispatch;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RandomWalkServlet extends HttpServlet {

    final static String[] PATHS = new String[]{
            "/servlet/dispatch.ForwardServletB",
            "/dispatch/forward_ok.jsp",
    };
    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        
        int index = new Random().nextInt(PATHS.length);
        String path = PATHS[index];
        
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(path);

        dispatcher.forward(request, response);
    }
}
