package sec01.ex04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        String id = request.getParameter("user_id");
        String password = request.getParameter("user_password");

        if(id != null && id.length()!=0) {
            pw.println("<html><body>");
            pw.println("<h1>" + id + "님이 로그인 하셨습니다.</h1>");
            pw.println("</html></body>");
        } else {
            pw.println("<html><body>");
            pw.println("<a href = /pro1_war_exploded/login2.html> 로그인창으로 이동 </a>");
            pw.println("</body></html>");
        }
    }
}
