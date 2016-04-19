package init;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ClasspathFileServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		URL url = ClasspathFileServlet.class.getResource("init.properties");			
		InputStream in = url.openStream();
		//ˆÈ‰º‚Å‚à“¯‚¶‚Å‚·
		//FileParamServlet.class.getResourceAsStream("init.properties");
		
		Properties prop = new Properties();
		prop.load(in);
		in.close();
		
		
		response.setContentType("text/plain; charset=Windows-31J");
		PrintWriter out = response.getWriter();
		out.println("url=" + url);
		prop.list(out);
	}

}
