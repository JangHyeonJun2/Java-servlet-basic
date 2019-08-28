package sec10.ex01;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }
    private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        MemberDAO dao = new MemberDAO();

        List memberList = dao.listmembers();
        req.setAttribute("membersList",memberList);//조회된 회원 정보를 ArrayList 객체에 저장한 후 request에 바인딩합니다.
        /*
         * 바인딩한 request를 viewMembers 서블릿으로 포워딩합니다.
         */
        RequestDispatcher dispatch = req.getRequestDispatcher("viewMembers");
        dispatch.forward(req,resp);


    }
}
