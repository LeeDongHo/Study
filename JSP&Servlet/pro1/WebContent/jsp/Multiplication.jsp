<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-25
  Time: 오후 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="dan" value="${param.dan }" />
<table border="1" width="800" align="center">
    <tr align="center" bgcolor="lightgreen">
        <td colspan="2"><c:out value="${dan}" />단 출력  </td>
    </tr>

    <c:forEach var="i"  begin="1" end="9" step="1"  >
        <tr align="center">
            <td width="400">
                <c:out value="${dan}" /> * <c:out value="${i}" />
            </td>
            <td width="400">
                <c:out value="${i*dan }" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
