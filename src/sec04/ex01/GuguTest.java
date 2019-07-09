package sec04.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guguTest")
public class GuguTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        int dan = Integer.parseInt(req.getParameter("dan")); //아마 자동 언박싱..? 될듯 싶다 확인하기 박싱,언박싱!
        out.print("<table border=1 width=800 align=center>");
        out.print("<tr align=center bgcolor='#FFFF66'>");
        out.print("<td colspan=2>"+dan+" 단 출력 </td>");
        out.print("</td>");
        for(int i=1;i<10;i++){
            out.print("<tr align=center>");
            out.print("<td width=400>");
            out.print(dan+ "* "+i);
            out.print("</td>");
            out.print("<td width=400>");
            out.print(i * dan);
            out.print("</td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy method");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init method");
    }
}
