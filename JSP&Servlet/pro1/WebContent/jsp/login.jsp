<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오후 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String userId = request.getParameter("user_id");
    String userPassword = request.getParameter("user_password");
%>
<html>
<head>
    <title>Login Result</title>
</head>
<body>
    <h1> 결과 출력 </h1>
<%
    if(userId == null || userId.length()==0) {
%>
    <h2>아이디를 입력하세요<br><a href="../login.html">로그인 창으로</a></h2>
<%
    } else {
        if(userId.equals("admin")) {
%>
    <h1>관리자 페이지</h1>
    <form>
        <input type="button" value="회원 정보 수정">
        <input type="button" value="회원 정보 삭제">
    </form>
    <%
        } else {
    %>
    <h1> 아이디 : <%=userId%></h1>
    <h1> 비밀번호 : <%=userPassword%></h1>
<%
    }
}
%>
</body>
</html>
