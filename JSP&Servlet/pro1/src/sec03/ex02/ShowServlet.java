package sec03.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowServlet", value = "/show")
public class ShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doHandle(request,response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        String id = "", password = "";
        boolean isLogin = false;

        HttpSession session = request.getSession(false);
        if(session != null) {
            isLogin  = (boolean)session.getAttribute("isLogin");
            if(isLogin) {
                id = (String) session.getAttribute("login.id");
                password = (String) session.getAttribute("login.password");
                pw.print("아이디 + " + id + "<br>");
                pw.print("비밀번호 : " + password);
            } else {
                pw.print("<script type='text/javascript>alert('아이디/비밀번호를 확인해주세요.')</script>");
                response.sendRedirect("/login2.html");
            }
        } else {
            pw.print("<script type='text/javascript>alert('아이디/비밀번호를 확인해주세요.')</script>");
            response.sendRedirect("/login2.html");
        }
    }
}
