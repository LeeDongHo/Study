package sec02.ex03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetServletContext", value = "/contextGet")
public class GetServletContext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        ServletContext context = getServletContext();
        List member = (List) context.getAttribute("member");
        String name = (String) member.get(0);
        int age = (int) member.get(1);
        pw.print("<h2>Name : " + name + " / Age : " + age + "</h2>");
    }
}
