package sec01.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
/*
 * 어노테이션을 활용한 서블릿
 */
@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Enumeration enu = req.getParameterNames();//전송되어 온 name 속성들 만 Enumeration 타입으로 받아 옵니다.
        while(enu.hasMoreElements()){
            String name = (String)enu.nextElement();
            String[] values = req.getParameterValues(name);
            for(String value : values){
                System.out.println("name=" + name+", value="+value);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy method 호출");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init method 호출");
    }
}
