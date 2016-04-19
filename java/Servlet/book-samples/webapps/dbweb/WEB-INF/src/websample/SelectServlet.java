package websample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SelectServlet extends HttpServlet{


    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain; charset=Windows-31J");
        PrintWriter out = response.getWriter();        
        
        String sql = "SELECT * FROM ACCOUNT";
                
        Connection con = null;
        Statement smt = null;
        try{
                
			con = DBManager.getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
	
			while(rs.next()){
				out.println(
					"ID=" + rs.getInt("ID")
					+ ",TITLE=" + rs.getString("NAME")
					+ ",PRICE=" + rs.getInt("MONEY")
				);
			}
				
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
    }
}
