package session;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GuessServlet extends HttpServlet {

    //private int hit = new Random().nextInt(10);    
    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String numberStr = request.getParameter("number");

        String error = null;
        int number = -1;

        if(numberStr == null || numberStr.length() == 0){
            error = "何か入れてね";
        }
        
        if(error == null){
	        try{
	            //数値チェック＆数値に変換
	            number = Integer.parseInt(numberStr);
	            //妥当性チェック
	            if( number < 0 || 9 < number ){
	                error = "0から9までの数字を入れてください";
	            }
	            
	        }catch(NumberFormatException e) {
	            error = "数字を入れてください";
	        }
        }
      

        String path;
        
        if(error != null){
            request.setAttribute("message", error);
            path = "/session/guess.jsp";
        }else{

            //セッションから当たり数字を取得
            //なければ作る
            HttpSession session = request.getSession();
            Integer hit = (Integer)session.getAttribute("hit");
            if(hit == null){
                hit = new Integer(new Random().nextInt(10));
                session.setAttribute("hit", hit);
            }
            
            if(hit.intValue() == number){
                request.setAttribute("message", "当たり。数字=" + number);
                path = "/session/guess_hit.jsp";

                //当ったらあたり数字をクリア
                session.removeAttribute("hit");
            }else{
                request.setAttribute("message", "はずれ。数字=" + number);
                path = "/session/guess.jsp";
            }

        }


		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);

    }
}
