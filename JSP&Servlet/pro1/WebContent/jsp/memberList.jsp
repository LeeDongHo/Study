<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*" %>
<%@ page import="sec02.ex01.*" %>
<html>
<head>
    <title>회원 조회 결과</title>
    <style>
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>회원 정보 출력</h1>
<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    MemberDto dto = new MemberDto();
    dto.setName(name);
    MemberDao dao = new MemberDao();
    List memberList = dao.listMembers(dto);
%>

<table border="1" width="800" align="center">
    <tr align="center" bgcolor="#faebd7">
        <td>아이디</td>
        <td>비밀번호</td>
        <td>이름</td>
        <td>이메일</td>
        <td>가입일자</td>
    </tr>

    <%
        for(int i=0;i<memberList.size();i++) {
            MemberDto dto1= (MemberDto) memberList.get(i);
            String id = dto1.getId();
            String password = dto1.getPassword();
            String nickname = dto1.getName();
            String email = dto1.getEmail();
            Date joinDate = dto1.getJoinDate();
    %>

    <tr align="center">
        <td><%= id %></td>
        <td><%= password %></td>
        <td><%= nickname %></td>
        <td><%= email %></td>
        <td><%= joinDate %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
