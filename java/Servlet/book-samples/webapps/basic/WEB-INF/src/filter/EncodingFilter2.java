package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter2 implements Filter {

    private String encode;
    
    public void init(FilterConfig filterConfig)
            throws ServletException {

        encode = filterConfig.getInitParameter("encode");
        
        System.out.println("encode=" + encode);
    }

    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding(encode);
        chain.doFilter(request, response);
    }

    public void destroy() {
        //‰½‚à‚µ‚È‚¢
    }

}
