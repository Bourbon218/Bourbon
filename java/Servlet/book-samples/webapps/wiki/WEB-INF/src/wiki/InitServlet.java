package wiki;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fw.DBManager;

public class InitServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
						
		try {
            String path = getServletContext().getRealPath("/WEB-INF/db/create.sql");
            StringBuffer sb = new StringBuffer();
            InputStream in = new FileInputStream(path);
            byte[] b = new byte[4096];
            int len;
            while( (len = in.read(b)) != -1 ){
                sb.append(new String(b, 0, len));
            }
            
            String sql = sb.toString();
            System.out.println(sql);
            
            DBManager.simpleUpdate(sql);

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
