<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오후 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    int dan = Integer.valueOf(request.getParameter("dan"));
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="800">
    <tr align="center" bgcolor="#faebd7">
        <td colspan="2"><%=dan%>단</td>
    </tr>
    <%
        for(int i=1;i<10;i++) {
    %>
    <tr align="center">
        <td width="400">
            <%=dan%> * <%=i%>
        </td>
        <td width="400">
            <%=dan*i%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
