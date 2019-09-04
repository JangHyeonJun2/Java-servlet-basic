package sec12.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InitParamServlet",
            urlPatterns = {"/sInit","/sInit2"},//urlPatterns를 이용해 매핑 이름을 여러 개 설정할 수 있습니다.

             /* @WebinitParam을 이용해 여러 개의 매개변수를 설정할 수 있다.*/
            initParams = {@WebInitParam(name = "email",value = "admin@naver.com"),
                          @WebInitParam(name = "tel", value = "010-0000-1111")})
public class InitParamServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String email = getInitParameter("email");//설정한 매개변수의 name으로 값을 가져온다.
        String tel = getInitParameter("tel");

        out.print("<html><body>");
        out.print("<table><tr>");
        out.print("<td>email:</td><td>"+email+"</td></tr>");
        out.print("<tr><td>tel:</td><td>"+tel+"</td>");
        out.print("</tr></table></body></html>");
    }
}
