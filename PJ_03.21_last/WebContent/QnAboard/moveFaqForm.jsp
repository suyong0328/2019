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
    <meta charset="utf-8">
    <title>FAQ로 이동하기</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/askDelete.js"></script>
<c:if test="${error != null}">
	<script type="text/javascript">
		alert('${error}');
		history.back();
	</script>
</c:if>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
		<h2>관리자 확인</h2>
		<form action="moveFaq.do" method="post" name="delForm">
			<input type="hidden" name="num" value="${num}"> <input
				type="hidden" name="pageNum" value="${pageNum}">
			<table class="table" style="width:500px;">
				<tr height="30">
					<th>관리자ID</th>
					<td><input type="text" class="form-control" name="id" value="${id}" readonly="readonly"></td>
				</tr>
				<tr height="30">
					<th>비밀번호</th>
					<td><input type="password" class="form-control" name="password"></td>
				</tr>
				<tr height="30">
					<td colspan=2 align="center"><input type="submit" class="btn"
						value="FAQ로 글 이동"> <input type="button" class="btn" value="글목록"
						onclick="location.href='listQna.do?pageNum=${pageNum}'">
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>