<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-29
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<html>
<head>
    <title>Article List</title>
    <style>
        .cls1 {
            text-decoration: none;
        }

        .cls2 {
            text-align: center;
            font-size: 30px;
        }
    </style>
</head>
<body>
    <table align="center" border="1" width="80%">
        <tr height="10" align="center" bgcolor="#add8e6">
            <td>글 번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>작성일</td>
        </tr>
        <c:choose>
            <c:when test="${articleList == null}">
                <tr height="10">
                    <td colspan="4">
                        <p align="center">
                            <b><span style="font-size: 9pt;">등록된 글이 없습니다.</span> </b>
                        </p>
                    </td>
                </tr>
            </c:when>
            <c:when test="${articleList != null}">
                <c:forEach var="article" items="${articleList}" varStatus="articleNum">
                    <tr align="center">
                        <td width="5%">${articleNum.count}</td>
                        <td align="left" width="35%">
                        <span style="padding-right: 30px"></span>
                        <c:choose>
                            <c:when test="${article.level > 1}">
                                <c:forEach begin="1" end="${article.level}" step="1">
                                    <span style="padding-left: 20px"></span>
                                </c:forEach>
                                <span style="font-size: 12px;">[답변]</span>
                                <a class="cls1" href="${contextPath}/board/viewArticle.do? articleId=${article.articleId}">${article.title}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="cls1" href="${contextPath}/board/viewAricle.do?articleId=${article.articleId}">${article.title}</a>
                            </c:otherwise>
                        </c:choose>
                        </td>
                        <td width="10%">${article.id}</td>
                        <td width="10%">
                            <fmt:formatDate value="${article.writeDate}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
    </table>
<a class="cls1" href="${contextPath}/board/articleForm.do">
    <p class="cls2">글쓰기</p>
</a>
</body>
</html>
