package sec12.ex02;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * load-on-startup 관한 내용 문서화 시켰으니 참고!
 */
@WebServlet(name = "loadConfig", urlPatterns = {"/loadConfig"},loadOnStartup = 1)//loadOnStartup 속성을 추가하고 우선수위를 1로 설정한다.
public class loadConfig extends HttpServlet {
    private ServletContext context;//멤버변수로 선언한다.

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("LoadAppConfig");
        context = config.getServletContext();//init() 메서드에서 ServletContext 객체를 얻는다.
        String menu_member = context.getInitParameter("menu_member");
        String menu_order = context.getInitParameter("menu_order");
        String menu_goods = context.getInitParameter("menu_goods");

        context.setAttribute("menu_member",menu_member);
        context.setAttribute("menu_order",menu_order);
        context.setAttribute("menu_goods",menu_goods);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String menu_member = (String)context.getAttribute("menu_member");
        String menu_order = (String)context.getAttribute("menu_order");
        String menu_goods = (String)context.getAttribute("menu_goods");

        out.print("<html><body>");
        out.print("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
        out.print("<tr><td>"+menu_member+"</td></tr>");
        out.print("<tr><td>"+menu_order+"</td></tr>");
        out.print("<tr><td>"+menu_goods+"</td></tr>");
        out.print("</table></body></html>");

    }
}
