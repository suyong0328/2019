<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String id = (String)session.getAttribute("id");
	request.setAttribute("id", id);
%>
<!doctype html>
<html lang="ko">
  <head>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
  	<script type="text/javascript">
	function deleteSave(){
		if(delForm.password.value==""){
			alert("비밀번호를 입력하세요.");
			delForm.password.focus();
			return false;
		}
		return true;
	}
	</script>
    <meta charset="utf-8">
    <title>글 삭제</title>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
				<h2>글삭제</h2>
		<form action="deletePro.do" method="post" name="delForm" onsubmit="return deleteSave()">
		<table class="table" style="width:400px; padding:40px;">
			<tr height="30">
			<th>비밀번호</th><td><input type="password" name="password">
			<input type="hidden" name="num" value="${num}">
			<input type="hidden" name="pageNum" value="${pageNum}"></td>
			</tr>
			<tr height="30">
			    <td colspan=2 align="center">
			    <input type="submit" class="btn" value="글삭제">
			    <input type="reset" class="btn" value="글목록"
			      onclick ="location.href='list.do?pageNum=${pageNum}'">
			      <input type="button" class="btn" value="취소" onclick="history.back()">
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>