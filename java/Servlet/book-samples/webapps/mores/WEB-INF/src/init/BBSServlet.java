package init;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BBSServlet extends HttpServlet {

    private List<BBSMessage> chatList; 
    
    
    public void init() throws ServletException {

        chatList = new Vector<BBSMessage>();
    }

    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=Windows-31J");
        PrintWriter out = response.getWriter();

        //入力パラメータの取得
        String user = request.getParameter("user");
        String message = request.getParameter("message");
        
        //入力があったときは、chatListに入力の発言を追加
        if(message != null){
            BBSMessage newBM = new BBSMessage();
            newBM.setUser(user);
            newBM.setMessage(message);            
        	chatList.add(newBM);
        }
        
        //以下は出力
        out.println("<html><body>");
                
        String uri = request.getRequestURI();
        out.println("<a href=\"" +uri+ "\">リロード</a>");
        out.println("<form action=\"" +uri+ "\">");
        out.println("名前<input type=\"text\" name=\"user\"><br>");
        out.println("メッセージ<input type=\"text\" name=\"message\" size=\"60\">");
        out.println("<input type=\"submit\" value=\"発言\"");
        out.println("</form>");
        
        //chatListの一覧をテーブル出力
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
    }

}
