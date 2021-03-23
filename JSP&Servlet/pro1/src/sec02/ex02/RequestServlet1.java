package sec02.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    HttpServletRequest, HttpSession, ServletContext 객체에서 사용되며, 저장된 데이터는 프로그램 실행시 서블릿이나 JSP에 공유해서 사용
    Model2, Spring, Apache Struts에선 바인딩을 이용해 데이터 전달/공유
*/
@WebServlet(name = "RequestServlet1", value = "/request1")
public class RequestServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("address","인천광역시 남동구");
        response.sendRedirect("request2");
    }
}
