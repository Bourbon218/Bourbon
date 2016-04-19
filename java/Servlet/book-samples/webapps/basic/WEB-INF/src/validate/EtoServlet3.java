package validate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EtoServlet3 extends HttpServlet {
    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String yearStr = request.getParameter("year");

        Eto eto = new Eto();
        eto.setYearStr(yearStr);
        eto.validate();

        response.setContentType("text/plain; charset=Windows-31J");        
        PrintWriter out = response.getWriter();  

        if(eto.getError() != null){
            out.println(eto.getError());
        }else{
            out.println("Ç†Ç»ÇΩÇÃä±éxÅF" + eto.getEtoText());
        }

    }

}
