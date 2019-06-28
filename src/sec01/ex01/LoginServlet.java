package sec01.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init 메서드 호출");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//전송된 데이터를 UTF-8로 인코딩 합니다.
        String user_id = req.getParameter("user_id");
        String user_pw = req.getParameter("user_pw");
        System.out.println("user_id:"+user_id);
        System.out.println("user_pw:"+user_pw);
    }

    @Override
    public void destroy() {
        System.out.println("destroy 메서드 호출");
    }

}
