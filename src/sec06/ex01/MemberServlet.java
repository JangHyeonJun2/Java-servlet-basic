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
/*
 * command 값을 먼저 받아 와 addMember이면 같이 전송된 회원 정보를 받아 옵니다. 회원 정보를 MemberVO객체에 설정한 후 MemberDAO의 메서드를 전달해 SQL문을 이용하여 테이블에 추가 합니다.
 */

@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }
    private void doHandle(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;cahrset=utf-8");
        MemberDAO dao = new MemberDAO();
        PrintWriter out = resp.getWriter();
        String command = req.getParameter("command");

        if(command != null && command.equals("addMember")){
            String _id = req.getParameter("id");
            String _pwd = req.getParameter("pwd");
            String _name = req.getParameter("name");
            String _email = req.getParameter("email");
            MemberVO memberVO = new MemberVO();
            memberVO.setId(_id);
            memberVO.setId(_pwd);
            memberVO.setId(_name);
            memberVO.setId(_email);
            //dao.addMember(memberVO);
        }else if(command != null && command.equals("delMember")){
            String id = req.getParameter("id");
            //dao.delMember(id);
        }

        List list = dao.listmembers();
        out.print("<html><body>");
        out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
        out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");

        for(int i = 0;i <list.size();i++){
            sec06.ex02.MemberVO memberVO= (sec06.ex02.MemberVO)list.get(i);
            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            Date joinDate = memberVO.getJoinDate();

            out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"+name+"</td><td>"+email+"</td><td>"+joinDate+"</td><td>"+
                    "<a href='/member3?command=delMember&id=" +id+"'>삭제 </a></td></tr>");
        }
        out.print("</table></body></html>");
        out.print("<a href='/memberForm.html'>새 회원 가입하기</a>");
    }
}
