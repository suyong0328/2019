<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ko">
<head>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="utf-8">
<title>회원 탈퇴</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/askDelete.js"></script>
</head>
<body>
	<jsp:include page="/module/top.jsp" />
	<div class="container">
		<h2>회원 탈퇴</h2>
		<form action="deleteMemberPro.do" method="post" name="delForm">
			<table class="table" style="width: 500px;">
				<caption>회원 탈퇴를 원하시면 비밀번호를 입력해주세요.</caption>
				<tr height="30">
					<th>회원ID</th>
					<td><input type="text" class="form-control" name="id"
						value="${id}" readonly="readonly"></td>
				</tr>
				<tr height="30">
					<th>비밀번호</th>
					<td><input type="password" class="form-control"
						name="password"></td>
				</tr>
				<tr height="30">
					<td colspan=2 align="center"><input type="submit" class="btn"
						value="회원 탈퇴"> <input type="button" class="btn" value="취소"
						onclick="location.href='redirect:history.back();'">
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp" />
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>