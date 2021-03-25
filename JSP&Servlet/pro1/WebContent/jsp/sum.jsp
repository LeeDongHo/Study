<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오후 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="addException.jsp" %>
<%
    int num = Integer.valueOf(request.getParameter("sumNumber"));
    int sum = 0;
    for(int i=1;i<=num;i++)
        sum += i;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> 합 결과 </h2>
<h1> 1부터 <%=num%> 까지의 합은 <%=sum%> 입니다.</h1>
</body>
</html>
