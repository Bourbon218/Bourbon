package validate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EtoServlet2 extends HttpServlet {
    
    final static String[] ETOS = new String[]{
            "子(ね)",
            "丑(うし)",
            "寅(とら)",
            "卯(う)",
            "辰(たつ)",
            "巳(み)",
            "午(うま)",
            "未(ひつじ)",
            "申(さる)",
            "酉(とり)",
            "戌(いぬ)",
            "亥(い)",
    };

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String yearStr = request.getParameter("year");

        String error = null;
        String etoText = null;

        if(yearStr == null || yearStr.length() == 0){
            error = "何か入れてね";
        }
        
        if(error == null){
	        try{
	            //数値チェック＆数値に変換
	            int year = Integer.parseInt(yearStr);
	            //妥当性チェック
	            if(year < 0){
	                error = "紀元前の人はサポート外です";
	            }else{
	            	int index = (year + 8) % 12;   
	            	etoText = ETOS[index];
	            }
	            
	        }catch(NumberFormatException e) {
	            error = "年を入れてください";
	        }
        }
      
        response.setContentType("text/plain; charset=Windows-31J");        
        PrintWriter out = response.getWriter();  

        if(error != null){
            out.println(error);
        }else{
            out.println("あなたの干支：" + etoText);
        }
    }

}
