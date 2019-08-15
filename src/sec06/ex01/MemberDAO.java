package sec06.ex01;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 * DataSource를 이용한 DB접속
 * 주석2
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
