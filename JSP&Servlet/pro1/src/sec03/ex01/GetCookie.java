package sec03.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet("/getCookie")
public class GetCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        Cookie[] c = request.getCookies();

        for(int i=0;i<c.length;i++) {
            if(c[i].getName().equals("cookieTest"))
                pw.println("<h2>Cookie data is "+ URLDecoder.decode(c[i].getValue(),"utf-8") +"</h2>");
        }
        pw.close();
    }
}
