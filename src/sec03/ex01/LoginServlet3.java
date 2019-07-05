package sec03.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login3")
public class LoginServlet3 extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init method");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String user_id = req.getParameter("user_id");
        String user_pw = req.getParameter("user_pw");
        System.out.println("아이디:"+user_id);
        System.out.println("비밀번호"+user_pw);
    }

    @Override
    public void destroy() {
        System.out.println("destroy method");
    }
}
