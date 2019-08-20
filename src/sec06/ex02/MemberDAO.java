package sec06.ex02;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 * DataSource를 이용한 DB접속
 * JPA로 바꿔보기
 */

public class MemberDAO {
    private DataSource dataFactory;
    public MemberDAO(){
        Context ctx;

        {
            try {
                ctx = new InitialContext();
                Context envContext = (Context)ctx.lookup("java:/comp/env"); //이거 뭔지 다시 확인하기
                dataFactory = (DataSource)envContext.lookup("jdbc/mysql");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    public List listmembers(){
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from t_member";

        try{
            conn = dataFactory.getConnection(); //데이터베이스 연결 메서드
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("joinDate");

                MemberVO vo = new MemberVO();
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                vo.setEmail(email);
                vo.setJoinDate(joinDate);
                list.add(vo);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public void addMember(MemberVO memberVO){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataFactory.getConnection();//DataSource를 이용해 데이터베이스와 연결한다.

            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();

            String query = "insert into t_member";//insert문을 문자열로 맘듭니다.
            query+= "(id,pwd,name,email)";
            query+= "values(?,?,?,?)";
            System.out.println("prepareStatement: " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);
            pstmt.setString(3,name);
            pstmt.setString(4,email);
            pstmt.executeUpdate();//회원 정보를 테이블에 추가합니다.

            pstmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delMember(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = dataFactory.getConnection();
            //Statement stmt = conn.createStatement();

            String query = "delete from t_member"+" where id=?";//delete 쿼리문을 문자열로 만든다.단,where앞에 공백 필수!!
            System.out.println("prepareStatement: "+query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    private Connection connDB(){
        Connection conn = null;

        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "study_db1"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "0653"; // MySQL 서버 비밀번호
        //1.드라이버 로
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("mysql 드라이버 로딩 성공");
        }catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        //연결
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false&amp;autoReconnection=true", user_name, password);
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
    */

}
