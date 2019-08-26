package sec09.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/2sec09")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String address = (String)req.getAttribute("address");//전달되 request에서 getAttribute()를 이용해 address의 값을 가져온다.
                                                                //그리고 getAttribute()값은 반환값이 Object이므로 형변환 필수!!!!
        out.print("<html><body>");
        out.print("주소:"+address);
        out.print("<br>");
        out.print("redirect를 이용한 바인딩 실습입니다.");
        out.print("</body></html>");
    }
}
