package sec03.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SessionTest", value = "/sessionTest")
public class SessionTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        HttpSession session = request.getSession();
        pw.println("세션 아이디 : " + session.getId()+"<br>");
        pw.println("세션 최초 생성 시간 : " + new Date(session.getCreationTime())+"<br>");
        pw.println("세션 최초 접근 시간 : " + new Date(session.getLastAccessedTime())+"<br>");
        pw.println("세선 유효 시간 : " + session.getMaxInactiveInterval()+"<br>");

        if(session.isNew())
            pw.print("Create new session");
    }
}
