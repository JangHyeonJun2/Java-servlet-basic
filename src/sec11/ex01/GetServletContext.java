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

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext();//ServletContext객체를 가져온다.
        List member = (ArrayList)context.getAttribute("member");
        String name = (String)member.get(0);
        int age = (Integer) member.get(1); //아마 자동 언박싱 되서 age변수에 들어갈듯??

        out.print("<html><body>");
        out.print("name:"+name+", age:"+age);
        out.print("</body></html>");

    }
}
