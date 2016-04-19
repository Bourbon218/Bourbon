package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieCounterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        Integer count = null;
        
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("count")) {
                    String value = cookie.getValue();
                    count = Integer.valueOf(value);
                    break;
                }
            }
        }

        if (count == null) {
            count = new Integer(0);
        }
        count = new Integer(count.intValue() + 1);

        Cookie cookie = new Cookie("count", count
                .toString());
        cookie.setMaxAge(60 * 60 * 24 * 5);
        //cookie.setMaxAge(0);
        response.addCookie(cookie);
        
        response.setContentType("text/html; charset=Windows-31J");
        PrintWriter out = response.getWriter();        
        out.println("<html><body>");
        out.print("<p>" + count + " 回目のアクセスありがとう");
        String linkUrl = request.getRequestURI();
        //linkUrl = response.encodeURL(linkUrl);//url rewriting
        out.println("<p><a href=\"" + linkUrl
                + "\">RELOAD</a>");
        out.println("</body></html>");
    }

}
