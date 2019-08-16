package sec06.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

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
        resp.setContentType("text/html;charset=utf-8");
        MemberDAO dao = new MemberDAO();
        PrintWriter out = resp.getWriter();
        String command = req.getParameter("command");//memberForm.html에서 command을 받아온다.

        if(command != null && command.equals("addMember")){//회원 가입창에서 전송된 command가 addMember이면 전송된 값들을 받아온다.
            //아래는 회원 가입창에서 전송된 값들을 얻어 와 MemberVO객체에 저장한 후 SQL문을 이용해 전달합니다.
            String _id = req.getParameter("id");
            String _pwd = req.getParameter("pwd");
            String _name = req.getParameter("name");
            String _email = req.getParameter("email");
            MemberVO memberVO = new MemberVO();
            memberVO.setId(_id);
            memberVO.setPwd(_pwd);
            memberVO.setName(_name);
            memberVO.setEmail(_email);
            dao.addMember(memberVO);
        }else if(command != null && command.equals("delMember")){
            String id = req.getParameter("id");
            //dao.delMember(id);
        }

        List list = dao.listmembers();
        out.print("<html><body>");
        out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
        out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");

        for(int i = 0;i <list.size();i++){
            sec06.ex02.MemberVO memberVO= (MemberVO)list.get(i);
            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            Date joinDate = memberVO.getJoinDate();

            out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"+name+"</td><td>"+email+"</td><td>"+joinDate+"</td><td>"+
                    "<a href='/member3?command=delMember&id=" +id+"'>삭제 </a></td></tr>");
        }
        out.print("</table></body></html>");
        out.print("<a href='/memberForm.html'>새 회원 가입하기</a>");//클릭하면 다시 회원 가입창으로 이동합니다.
    }
}