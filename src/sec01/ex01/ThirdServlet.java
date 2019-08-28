package sec01.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
 * 어노테이션을 활용한 서블릿
 */
//@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ThirdServlet doGet method");
    }

    @Override
    public void destroy() {
        System.out.println("ThirdServlet destroy method");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("ThirdServlet init method ");
    }
}
