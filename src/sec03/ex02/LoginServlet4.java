package sec03.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method");
        doHandle(req,resp);//Get 방식으로 요청시 다시 doHandle()을 호출합니다.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method");
        doHandle(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("destroy method");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init method");
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{//모든 호출 방식에 대해 처리할 수 있습니다.
        req.setCharacterEncoding("utf-8");
        String user_id = req.getParameter("user_id");
        System.out.println("doHandle 메서드 호출");
        String user_pw = req.getParameter("user_pw");
        System.out.println("아이디 : "+user_id);
        System.out.println("비밀번호 : "+user_pw);
    }
}
