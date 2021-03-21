package sec02.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Process(request,response);
    }

    private void Process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        MemberDao dao = new MemberDao();
        String command = request.getParameter("command");

        if(command != null && command.equals("insertMember"))
        {
            MemberDto dto = new MemberDto();
            dto.setId(request.getParameter("id"));
            dto.setPassword(request.getParameter("password"));
            dto.setName(request.getParameter("name"));
            dto.setEmail(request.getParameter("email"));
            dao.insertMember(dto);
        } else if (command != null && command.equals("deleteMember")) {
            MemberDto dto = new MemberDto();
            dto.setId(request.getParameter("id"));
            dao.deleteMember(dto);
        }

        PrintWriter pw = response.getWriter();;
        List list = dao.listMembers();
        pw.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
        pw.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");

        for (int i=0; i<list.size();i++){
            MemberDto memberDto=(MemberDto) list.get(i);
            String id=memberDto.getId();
            String pwd = memberDto.getPassword();
            String name=memberDto.getName();
            String email=memberDto.getEmail();
            Date joinDate = memberDto.getJoinDate();
            pw.print("<tr><td>"+id+"</td><td>"+
                    pwd+"</td><td>"+
                    name+"</td><td>"+
                    email+"</td><td>"+
                    joinDate+"</td><td>"+
                    "<a href='/member?command=deleteMember&id="+id+
                    "'> X </a></td></tr>");
        }
        pw.print("</table>");
        pw.print("<a href='/createMemberForm.html'>회원 가입</a>");
    }
}
