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
<c:set var="articleList" value="${articleMap.articleList}"/>
<c:set var="totalArticles" value="${articleMap.totalArticles}"/>
<c:set var="section" value="${articleMap.section}"/>
<c:set var="pageNum" value="${articleMap.pageNum}"/>
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

        .no-uline { text-decoration: none;}
        .sel-page {text-decoration: none;color: #ff0000;}
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
                        <td width="5%">${article.articleId}</td>
                        <td align="left" width="35%">
                        <span style="padding-right: 30px"></span>
                        <c:choose>
                            <c:when test="${article.level > 1}">
                                <c:forEach begin="1" end="${article.level}" step="1">
                                    <span style="padding-left: 20px"></span>
                                </c:forEach>
                                <span style="font-size: 12px;">[답변]</span>
                                <a class="cls1" href="${contextPath}/board/articleView.do? articleId=${article.articleId}">${article.title}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="cls1" href="${contextPath}/board/articleView.do?articleId=${article.articleId}">${article.title}</a>
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


    <div class="cls2">
        <c:if test="${totArticles != null }" >
            <c:choose>
                <c:when test="${totArticles >100 }">  <!-- 글 개수가 100 초과인경우 -->
                    <c:forEach   var="page" begin="1" end="10" step="1" >
                        <c:if test="${section >1 && page==1 }">
                            <a class="no-uline" href="${contextPath }/board/articleList.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp; pre </a>
                        </c:if>
                        <a class="no-uline" href="${contextPath }/board/articleList.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
                        <c:if test="${page ==10 }">
                            <a class="no-uline" href="${contextPath }/board/articleList.do?section=${section+1}&pageNum=${section*10+1}">&nbsp; next</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${totArticles ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
                    <c:forEach   var="page" begin="1" end="10" step="1" >
                        <a class="no-uline"  href="#">${page } </a>
                    </c:forEach>
                </c:when>

                <c:when test="${totArticles< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
                    <c:forEach   var="page" begin="1" end="${totArticles/10 +1}" step="1" >
                        <c:choose>
                            <c:when test="${page==pageNum }">
                                <a class="sel-page"  href="${contextPath }/board/articleList.do?section=${section}&pageNum=${page}">${page } </a>
                            </c:when>
                            <c:otherwise>
                                <a class="no-uline"  href="${contextPath }/board/articleList.do?section=${section}&pageNum=${page}">${page } </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:if>
    </div>
    <br><br>
    <a  class="cls1"  href="${contextPath}/board/articleForm.do"><p class="cls2">글쓰기</p></a>
</body>
</html>
