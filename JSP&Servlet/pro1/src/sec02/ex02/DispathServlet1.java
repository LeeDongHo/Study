package sec02.ex02;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// dispath : browser 통하지 않고 서버단에서 redirect (브라우저는 redirect 모름)
@WebServlet(name = "DispathServlet1", value = "/dispath1")
public class DispathServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dispath2?name=doho");
        dispatcher.forward(request,response);
    }
}
