package sec07.ex1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first1")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        resp.sendRedirect("second1?name=lee"); // sendRedirect()메서드를 이용해 웹 브라우저에게 다른 서불릿인 second로 재요청합니다.또한
                                                    //GET방식을 이용해 이름/값 쌍으로 데이터를 다른 서블릿으로 전달합니다.
    }
}
