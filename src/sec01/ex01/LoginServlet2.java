package sec01.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * doGet()메소드에 Html문서 작업하기
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init 메서드 호출");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //웹 브라우저에서 전송된 데이터의 인코딩을 설정합니다.
        resp.setContentType("text/html;charset=utf-8"); //setContentType()을 이용해 응답할 데이터 종류가 HTML임을 설정한다.
        PrintWriter out = resp.getWriter(); //HttpServletResponse 객체의 getWriter()를 이용해 출력 스트림 PrintWriter 객체를 받아 옵니다.
        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");

        String data = "<html>";
        data += "<body>";
        data += "아이디 : " + id;
        data += "<br>";
        data += "패스워드 :"+ pw;
        data += "</body>";
        data += "</html>";
        out.print(data);
    }

    @Override
    public void destroy() {
        System.out.println("destroy method");
    }

}
