package sec03.ex02;

import sec02.ex01.MemberDao;
import sec02.ex01.MemberDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlt", value = "/trueLogin")
public class LoginServlt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();;

        String userId = request.getParameter("user_id");
        String userPassword = request.getParameter("user_password");

        MemberDto dto = new MemberDto();
        dto.setId(userId);
        dto.setPassword(userPassword);

        MemberDao dao = new MemberDao();
        boolean isExisted = dao.isExisted(dto);
        if(isExisted) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogin", true);
            session.setAttribute("login.id", userId);
            session.setAttribute("login.password", userPassword);
            pw.print("<h1> Hello " + userId + "!!</h1>");
            response.addHeader("refresh","5;/show");
        } else {
            pw.print("<script type='text/javascript>alert('아이디/비밀번호를 확인해주세요.')</script>");
            response.sendRedirect("/login2.html");
        }
    }
}
