package sec11.ex01;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/*
 * cset.java부분
 */
@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext(); // ServletContext 객체를 가져온다.
        List member = new ArrayList();
        member.add("이순신");
        member.add(30);//String으로 안 넣게 조심하기!
        context.setAttribute("member",member);//ServletContext 객체에 데이터를 바인딩 한다.
        out.print("<html><body>");
        out.print("이순신과 30 설정");
        out.print("</body></html>");


    }
}
