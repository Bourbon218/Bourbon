package filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DebugFilter implements Filter {

    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

    	@SuppressWarnings("unchecked")
        Enumeration<String> names = request.getParameterNames();        
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            String[] values = request.getParameterValues(key);
            for (int i = 0; i < values.length; i++) {
                System.out.println(key + "=" + values[i]);
            }
        }        
                
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
