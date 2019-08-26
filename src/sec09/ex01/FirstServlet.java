package sec09.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sec09")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.setAttribute("address","서울시 성북구");//웹 브라우저에서 요청한 request객체에 address의 값으로 '서울시 성북구'를 바인딩한다.
        resp.sendRedirect("2sec09");//두 번쨰 서블릿으로 전달하기 위한 작업
    }
}
