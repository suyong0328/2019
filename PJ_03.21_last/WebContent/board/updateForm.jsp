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
  	<script src="${pageContext.request.contextPath}/js/script.js"></script>
    <meta charset="utf-8">
    <title>글 수정</title>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
		<form action="updatePro.do" onsubmit="return writeSave()" method = "post">
			<table class="table" align="center">
			<caption>
			<h2>글 수정</h2>
			</caption>
			<tr><td>이름</td>
			<td><input type = "text" class="form-control" maxlength="10" name = "writer" value = "${board.writer }" readonly="readonly">
			<input type = "hidden" name = "num" value = "${board.num }"></td>
			</tr>
			<tr><td>제목</td>
			<td><input type = "text" class="form-control" maxlength="50" name = "subject" value = "${board.subject }">
			</td>
			</tr>
			<tr><td>email</td>
			<td><input type = "text" class="form-control" maxlength="40" name = "email" value = "${board.email }">
			</td>
			</tr>
			<tr><td>내용</td>
			<td><textarea rows="10" cols="140" name = "content">${board.content }</textarea>
			</td>
			</tr>
			<tr><td>패스워드</td>
			<td><input type = "password" class="form-control" maxlength="10" name = "password" >
			</td>
			</tr>
			<tr><td colspan="2" >
			<input type ="submit" value = "글수정" class="btn"> 
			<input type = "reset" class="btn" value = "다시작성">
			<input type = "button" class="btn" value = "목록보기" onclick="location.href='list.do?pageNum=${pageNum}'"> 
		</table>
			
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>