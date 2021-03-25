<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오후 5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h5>toString() : </h5> <%=exception.toString()%><br>
<h5>getMessage() : </h5> <%=exception.getMessage()%><br>
<h5>printStackTrace() : </h5> <%=exception.printStackTrace()%><br>
</body>
</html>
