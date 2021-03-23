package sec02.ex03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.StringTokenizer;

@WebServlet(name = "ContextFileServlet", value = "/contextFile")
public class ContextFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        ServletContext context = getServletContext();
        InputStream stream = context.getResourceAsStream("/WEB-INF/bin/init.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        String menu = null;
        String menuMember = null;
        String menuOrder = null;
        String menuGoods = null;

        while((menu=bufferedReader.readLine()) != null) {
            StringTokenizer tokens = new StringTokenizer(menu, ",");
            menuMember=tokens.nextToken();
            menuOrder=tokens.nextToken();
            menuGoods=tokens.nextToken();
        }

        pw.print("<table boorder=1 cellspacing=0><tr>메뉴 이름</tr>");
        pw.print("<tr><td>"+ menuMember+"</td></tr>");
        pw.print("<tr><td>"+ menuOrder+"</td></tr>");
        pw.print("<tr><td>"+ menuGoods+"</td></tr>");
        pw.print("</table>");
        pw.close();
    }
}
