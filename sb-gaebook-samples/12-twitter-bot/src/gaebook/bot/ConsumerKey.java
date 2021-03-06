package gaebook.bot;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class ConsumerKey {
    static Logger logger = Logger.getLogger(ConsumerKey.class.getName());
    
    static String key;
    static String secret;
    
    static void init(HttpServlet servlet){
        if (key != null) 
            return;
        ServletContext ctx = servlet.getServletConfig().getServletContext();
        key =    ctx.getInitParameter("consumerKey");
        secret = ctx.getInitParameter("consumerSecret");

        if (key == null || secret == null) { 
            logger.severe("failed to get consumer keys " +
            		"as init-param in the web.xml. " +
            		"Check the file again.");
        }
    }
}
