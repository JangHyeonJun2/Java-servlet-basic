package sec11.ex02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext(); // ServletContext객체를 가져온다

        /*
         * web.xml의 <param-name>태그의 이름으로 <param-value> 태그의 값인 메뉴 이름들을 받아온다.
         */
        String menu_member = context.getInitParameter("menu_member");
        String menu_order = context.getInitParameter("menu_order");
        String menu_goods = context.getInitParameter("menu_goods");

        out.print("<html><body>");
        out.print("<table border=1 cellspacing=0><tr>메뉴이름</tr>");
        out.print("<tr><td>"+menu_member+"</td></tr>");
        out.print("<tr><td>"+menu_order+"</td></tr>");
        out.print("<tr><td>"+menu_goods+"</td></tr>");
        out.print("</table></body></html>");

    }
}
