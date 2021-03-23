package sec02.ex03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ContextParamServlet", value = "/contextParam")
public class ContextParamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        ServletContext context = getServletContext();
        String menu_member = context.getInitParameter("menu_member");
        String menu_order = context.getInitParameter("menu_order");
        String menu_goods = context.getInitParameter("menu_goods");

        pw.print("<table boorder=1 cellspacing=0><tr>메뉴 이름</tr>");
        pw.print("<tr><td>"+ menu_member+"</td></tr>");
        pw.print("<tr><td>"+ menu_order+"</td></tr>");
        pw.print("<tr><td>"+ menu_goods+"</td></tr>");
        pw.print("</table>");
    }
}
