package sec04.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * 실습예제1: 서블릿에 로그인 요청 시 유효성 검사하기
 */
@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");

        System.out.println("id : "+ id);
        System.out.println("password : "+pw);

        if(id!=null && (id.length()!=0)){
            out.print("<html>");
            out.print("<body>");
            out.print(id+"님이 로그인 하셨습니다.");
            out.print("</body>");
            out.print("</html>");
        }else{
            out.print("<html>");
            out.print("<body>");
            out.print("아이디를 입력하세요!!!");
            out.print("<br>");
            out.print("<a href='http://localhost:8090/test01/login.html'>로그인 창으로 이동</a>");
            out.print("</body>");
            out.print("</html>");
        }
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
