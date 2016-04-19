package websample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.sun.rowset.CachedRowSetImpl;



public class RowSetSelectServlet extends HttpServlet{


    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection con = null;
        Statement smt = null;
        try{
                
			con = DBManager.getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT * FROM ACCOUNT");

			CachedRowSetImpl crs = new CachedRowSetImpl();
			crs.populate(rs);
			
			request.setAttribute("crs", crs);	

        }catch(SQLException e){
            throw new ServletException(e);
        }finally{
            if(smt != null){
                try{smt.close();}catch(SQLException ignore){}
            }
            if(con != null){
                try{con.close();}catch(SQLException ignore){}
            }
        }
        
        
        request.getRequestDispatcher("/websample/select_rs.jsp").forward(request, response);
    }
}
