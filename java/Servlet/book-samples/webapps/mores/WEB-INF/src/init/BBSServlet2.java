package init;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BBSServlet2 extends HttpServlet {

    private List<BBSMessage> chatList; 
    
    final static String FILE_PATH = "WEB-INF/bbs.dat";
    
    
    @SuppressWarnings("unchecked")
	public void init() throws ServletException {

        chatList = new Vector<BBSMessage>();
        
        File file = getDataFile();
        
        if(file.exists()){
            try {
                InputStream in = new FileInputStream(file);
                XMLDecoder decoder = new XMLDecoder(in);                
                chatList = (List<BBSMessage>)decoder.readObject();
                decoder.close();

            } catch (FileNotFoundException e) {
                throw new ServletException(e);
            }
        }
    }

    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=Windows-31J");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String message = request.getParameter("message");
        
        if(message != null){
            BBSMessage newBM = new BBSMessage();
            newBM.setUser(user);
            newBM.setMessage(message);            
        	chatList.add(newBM);
        }
        
        out.println("<html><body>");
                
        String uri = request.getRequestURI();
        out.println("<a href=\"" +uri+ "\">リロード</a>");
        out.println("<form action=\"" +uri+ "\">");
        out.println("名前<input type=\"text\" name=\"user\"><br>");
        out.println("メッセージ<input type=\"text\" name=\"message\" size=\"60\">");
        out.println("<input type=\"submit\" value=\"発言\"");
        out.println("</form>");
        
        out.println("<table>");
        for(int i=chatList.size()-1; i>=0; i--){
            BBSMessage cm = chatList.get(i);
            out.println("<tr>");
            out.println("<td>" + cm.getUser());
            out.println("<td>:" + cm.getMessage());
        }        
        out.println("</table>");
        
        out.println("</body></html>");        
    }

        
    public void destroy() {
        File file = getDataFile();
        try {
            OutputStream out = new FileOutputStream(file);
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(chatList);
            encoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private File getDataFile() {
        String path = getServletContext().getRealPath(FILE_PATH);
        return new File(path);        
    }

}
