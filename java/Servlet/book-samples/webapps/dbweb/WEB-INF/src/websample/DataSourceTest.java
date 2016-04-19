package websample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



public class DataSourceTest extends HttpServlet{


    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain; charset=Windows-31J");
        PrintWriter out = response.getWriter();        
                
        try{
        	InitialContext ctx = new InitialContext();
        	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myds");
        	Connection con = ds.getConnection();
        	out.println("con=" + con);

        }catch(Exception e){
        	throw new ServletException(e);
        }

    }
}
