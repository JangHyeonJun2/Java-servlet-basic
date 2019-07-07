package sec03.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");
        String address = req.getParameter("user_address"); // login2.html에서 hidden값으로 전송된 값을 받아 옵니다.
        System.out.println("id : "+ id);
        System.out.println("password : "+pw);

        String data = "<html>";
        data+="<body>";
        data+="id : " +id;
        data+="<br>";
        data+="password : "+pw;
        data+="<br>";
        data+="address : "+address;
        data+="</body>";
        data+="</html>";
        out.print(data);
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
