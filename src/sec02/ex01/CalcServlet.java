package sec02.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String command = req.getParameter("command");//수행할 요청을 받아 옵니다.
        String won = req.getParameter("won");
        String operator = req.getParameter("operator");

        if(command != null && command.equals("calculate")){
            String result = calculate(Float.parseFloat(won),operator);
            out.print("<html><font size=10>변환결과</font><br>");
            out.print("<html><font size =10>"+ result +"</font><br>");
            out.print("<a href = '/calc'>환율 계산기</a>");
            return;
        }

        out.print("<html><title>환율 계산기</title>");
        out.print("<font size=5>환율 계산기</font><br>");
        out.print("<form name='frmCalc method = 'get' action='/calc'/>");
        out.print("원화 : <input type='text' name='won' size=10/>");
        out.print("<select name='operator'>");
        out.print("<option value='dollar'>달러</option>");
        out.print("<option value='en'>엔화</option>");
        out.print("<option value='wian'>위안</option>");
        out.print("<option value='pound'>파운드</option>");
        out.print("<option value='euro'>유로</option>");
        out.print("</select>");
        out.print("<input type='hidden' name='command' value='calculate'/>");
        out.println("<input type='submit' value='변환'/>");
        out.println("</form>");
        out.print("</html>");
        out.close();
    }

    private static String calculate(float won,String operator){
        String result = null;
        if(operator.equals("dollar")){
            result = String.format("%.6f",won/1069.43);
        }else if(operator.equals("en")){
            result = String.format("%.6f",won/10.85);
        }else if(operator.equals("wian")){
            result = String.format("%.6f",won/169);
        }else if(operator.equals("pound")){
            result = String.format("%.6f",won/1471);
        }else if(operator.equals("euro")){
            result = String.format("%.6f",won/1320);
        }
        return result;
    }
}
