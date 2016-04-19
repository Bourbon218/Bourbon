package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
        String keyParam = request.getParameter("key");
        String valueParam = request.getParameter("value");
        if(keyParam != null && keyParam.length() > 0){
        	
        	keyParam = URLEncoder.encode(keyParam, "Windows-31J");
        	valueParam = URLEncoder.encode(valueParam, "Windows-31J");        	
        	
        	Cookie cookie = new Cookie(keyParam, valueParam);
        	cookie.setMaxAge(60 * 60 * 24 * 5);
        	response.addCookie(cookie);
        }
        
        response.setContentType("text/html; charset=Windows-31J");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"\">");
        out.println("key <input type=\"text\" name=\"key\"><br>");
        out.println("value <input type=\"text\" name=\"value\"><br>");
        out.println("<input type=\"submit\" value=\"“o˜^\">");
        out.println("</form>");

    	
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String key = cookie.getName();
                String value = cookie.getValue();

                key = URLDecoder.decode(key, "Windows-31J");
                value = URLDecoder.decode(value, "Windows-31J");
                
                out.println(key + "=" + value + "<br>");
            }
        }
        
        out.println("</body></html>");
    }

}
