<%--
  Created by IntelliJ IDEA.
  User: edh10
  Date: 2021-03-30
  Time: 오후 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>글 보기</title>
    <script type="text/javascript">
        function backToList(obj) {
            obj.action = "${contextPath}/board/articleList.do";
            obj.submit();
        }

        function fn_enable(obj){
            document.getElementById("i_title").disabled=false;
            document.getElementById("i_content").disabled=false;
            document.getElementById("i_imageFileName").disabled=false;
            document.getElementById("tr_btn_modify").style.display="block";
            document.getElementById("tr_btn").style.display="none";
        }

        function fn_modify_article(obj){
            obj.action="${contextPath}/board/articleModify.do";
            obj.submit();
        }

        function fn_remove_article(url,articleId){
            let form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", url);
            let articleIdInput = document.createElement("input");
            articleIdInput.setAttribute("type","hidden");
            articleIdInput.setAttribute("name","articleId");
            articleIdInput.setAttribute("value", articleId);

            form.appendChild(articleIdInput);
            document.body.appendChild(form);
            form.submit();
        }

        function fn_reply_form(url, parentId) {
            let form = docuemt.createElement("form");
            form.setAttribute("method","post");
            form.setAttribute("action",url);
            let parentIdInput = docuemt.createElement("input");
            parentIdInput.setAttribute("type","hidden");
            parentIdInput.setAttribute("name","parentId");
            parentIdInput.setAttribute("value",parentId);
            form.appendChild(parentIdInput);
            docuemt.body.appendChild(form);
            form.submit();
        }

        function readURL(input) {
            if (input.files && input.files[0]) {
                let reader = new FileReader();
                reader.onload = function (e) {
                    $('#preview').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</head>
<body>
<form name="articleForm" method="post"   enctype="multipart/form-data">
    <table  border="0" align="center" >
        <tr>
            <td width="150" align="center" bgcolor="#FF9933">
                글번호
            </td>
            <td >
                <input type="text"  value="${article.articleId }"  disabled />
                <input type="hidden" name="articleId" value="${article.articleId}"  />
            </td>
        </tr>
        <tr>
            <td width="150" align="center" bgcolor="#FF9933">
                작성자 아이디
            </td>
            <td >
                <input type="text" value="${article.id }" name="id"  disabled />
            </td>
        </tr>
        <tr>
            <td width="150" align="center" bgcolor="#FF9933">
                제목
            </td>
            <td>
                <input type="text" value="${article.title }"  name="title"  id="i_title" disabled />
            </td>
        </tr>
        <tr>
            <td width="150" align="center" bgcolor="#FF9933">
                내용
            </td>
            <td>
                <textarea rows="20" cols="60"  name="content"  id="i_content"  disabled /> ${article.content} </textarea>
            </td>
        </tr>

        <c:if test="${not empty article.imageFileName && article.imageFileName!='null' }">
            <tr>
                <td width="20%" align="center" bgcolor="#FF9933"  rowspan="2">
                    이미지
                </td>
                <td>
                    <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
                    <img src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleId=${article.articleId }"   id="preview"  /><br>

                </td>
            </tr>
            <tr>
                <td>
                    <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
                </td>
            </tr>
        </c:if>
        <tr>
            <td width="20%" align="center" bgcolor="#FF9933">
                등록일자
            </td>
            <td>
                <input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
            </td>
        </tr>
        <tr   id="tr_btn_modify"  >
            <td colspan="2"   align="center" >
                <input type=button value="수정 반영 하기"   onClick="fn_modify_article(articleForm)"  >
                <input type=button value="취소"  onClick="backToList(articleForm)">
            </td>
        </tr>

        <tr  id="tr_btn"    >
            <td colspan=2 align="center">
                <%-- <c:if test="${member.id == article.id }">
                   <input type=button value="수정하기" onClick="fn_enable(this.form)">
                   <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
                 </c:if> --%>
                <input type=button value="수정하기" onClick="fn_enable(this.form)">
                <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/articleRemove.do', ${article.articleId})">
                <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
                <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleId})">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
