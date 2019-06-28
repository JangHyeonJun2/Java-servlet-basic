package sec01.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init method 호출");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String[] subject = req.getParameterValues("subject");
        String user_id = req.getParameter("user_id");
        String user_pw = req.getParameter("user_pw");

        System.out.println("id:"+user_id);
        System.out.println("pw:"+user_pw);

        for(String str : subject){
            System.out.println("선택한 과목: "+str);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy method 호출");
    }

}
