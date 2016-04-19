package validate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuessServlet extends HttpServlet {
    
    private int hit = new Random().nextInt(10);


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
      
        response.setContentType("text/plain; charset=Windows-31J");        
        PrintWriter out = response.getWriter();  

        if(error != null){
            out.println(error);
        }else{

            out.println("数字：" + number);
            
            if(hit == number){
                out.println("当たり！");
            }else{
                out.println("はずれ");
            }            
        }
    }
}
