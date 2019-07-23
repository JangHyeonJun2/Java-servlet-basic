package sec06.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet("/member2")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        MemberDAO dao = new MemberDAO(); //SQL문으로 조회할 MemberDAO객체를 생성합니다.
        List list = dao.listmembers(); //listMembers() 메서드로 회원 정보를 조회합니다.

        out.print("<html><body>");
        out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
        out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일자</td>");

        for(int i=0;i<list.size();i++){
            MemberVO membervo = (MemberVO) list.get(i);
            String id = membervo.getId();
            String pwd = membervo.getPwd();
            String name = membervo.getName();
            String email = membervo.getEmail();
            Date joinDate = membervo.getJoinDate();
            out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"+name+"</td><td>"+email+"</td><td>"+joinDate+"</td><td>");
        }

        out.print("</table></body></html>");
    }
}
