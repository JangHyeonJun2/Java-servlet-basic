package sec01.ex01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method >>>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy method >>>");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init method >>>");
    }
}
