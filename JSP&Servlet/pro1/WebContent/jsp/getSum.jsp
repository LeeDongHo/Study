<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오전 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    String name = "선언문";
    public String getName() {return name;}
%>
<% String age=request.getParameter("age");%>

<%--주석쓰--%>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<h1> hello <%=getName()%>>의 나이는 <%=age%> 입니다. </h1>
</body>
</html>
