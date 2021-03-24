package sec03.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet("/setCookie")
public class SetCookie extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        Date d = new Date();
        Cookie c = new Cookie("cookieTest", URLEncoder.encode("Cookie Test","utf-8"));
        c.setMaxAge(24*60*60);  // 유효기간 : 음수/x -> Session cookie
        response.addCookie(c);
        pw.print("현재시간 : " + d);
        pw.print(" 을 Cookie로 저장쓰~");
        pw.close();
        response.addHeader("Refresh","5;url=/getCookie");
    }
}
