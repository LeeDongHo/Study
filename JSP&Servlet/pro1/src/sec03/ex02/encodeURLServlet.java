package sec03.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "encodeURLServlet", value = "/encodeURL")
public class encodeURLServlet extends HttpServlet {
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
        HttpSession session = request.getSession();

        String userId = request.getParameter("user_id");
        String userPassword = request.getParameter("user_password");
        if(session.isNew()) {
            if(userId != null) {
                session.setAttribute("userId",userId);
                response.sendRedirect(response.encodeRedirectURL("encodeURL"));
            } else {
                pw.print("<script type='text/javascript'> alert('please check your id/password'); </script>");
                session.invalidate();
            }
        } else {
            userId = (String) session.getAttribute("userId");
            pw.print("<h1> Login success : </h1><h2>" + userId +"</h2>");
        }
    }
}
