package sec07.ex3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first3")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        /*
         *  밑에 코드는 자바스크립트 location의 href 속성에 서블릿second를 설정해 재요청합니다.
         */
        out.print("<script type = 'text/javascript'>");
        out.print("location.href='second3'");
        out.print("</script>");
    }
}
