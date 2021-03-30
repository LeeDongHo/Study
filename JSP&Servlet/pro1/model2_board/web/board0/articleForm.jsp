<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-29
  Time: 오후 4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>글 쓰기</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
        function readURL(input) {
            if(input.files && input.files[0]) {
                let reader = new FileReader();
                reader.onload = function (e) {
                    $('#preview').attr('src',e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        function backToList(obj) {
            obj.action = "${contextPath}/board/articleList.do";
            obj.submit();
        }
    </script>
</head>
<body>
<h1 style="text-align: center">새글 쓰기</h1>
<form name = "articleForm" method="post" action="${contextPath}/board/save.do" enctype="multipart/form-data">
    <table border="0" align="center">
        <tr>
            <td align="right">글 제목 : </td>
            <td colspan="2"><input type="text"  size="67" maxlength="500" name="title"/> </td>
        </tr>
        <tr>
            <td align="right" valign="top"><br>글 내용 : </td>
            <td colspan="2"><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
        </tr>
        <tr>
            <td align="right">이미지 첨부 : </td>
            <td><input type="file" name="imageFileName" onchange="readURL(this);"/> </td>
            <td><img id="preview" scr="#" width=200 height 200 /></td>
        </tr>
        <tr>
            <td align="right"></td>
            <td colspan="2">
                <input type="submit" value="글쓰기"/>
                <input type="button" value="목록 보기" onclick="backToList(this.form)"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
