package sec11.ex03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.StringTokenizer;

@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext();
        InputStream is =context.getResourceAsStream("/WEB-INF/bin/init.txt");//해당 위치의 파일을 읽어 들인다.
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));//위에 is에 init.txt에 문자열을 읽어들인다.

        String menu = null;
        String menu_order = null;
        String menu_member = null;
        String menu_goods = null;

        while ((menu = buffer.readLine()) !=null){
            StringTokenizer tokens = new StringTokenizer(menu,",");//콤마로 구분해서 문자열을 구분한다.출력 기능
            menu_member = tokens.nextToken();
            menu_order = tokens.nextToken();
            menu_goods = tokens.nextToken();
        }

        out.print("<html><body>");
        out.print(menu_member+"<br>");
        out.print(menu_order+"<br>");
        out.print(menu_goods+"<br>");
        out.print("</body></html>");
        out.close();

    }
}
