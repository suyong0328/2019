<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String id = (String) session.getAttribute("id");
	request.setAttribute("id", id);
%>
<!doctype html>
<html lang="ko">
<head>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="utf-8">
<title>아이디 / 비밀번호 찾기</title>
</head>
<body>
	<jsp:include page="/module/top.jsp" />
	<div class="container">
		<h3>ID 찾기</h3>
		<form action="searchId.do" method="post">
			<table class="table" style="width: 500px;">
				<caption>아이디를 찾기 위해 이름과 주민등록번호를 입력해주세요.</caption>
				<tr height="30">
					<th>이름</th>
					<td><input type="text" class="form-control" name="name"
						placeholder="이름"></td>
				</tr>
				<tr height="30">
					<th>주민등록번호</th>
					<td><input type="text" class="form-control" name="rrnum1"
						placeholder="주민등록번호 앞 6 자리" maxlength="6"> - <input
						type="password" class="form-control" name="rrnum2"
						placeholder="주민등록번호 뒤 7 자리" maxlength="7"></td>
				</tr>
				<tr height="30">
					<td colspan=2 align="center"><input type="submit" class="btn"
						value="아이디 찾기"> <input type="button" class="btn"
						value="취소" onclick="location.href='redirect:history.back();'">
				</tr>
			</table>
			<!-- <fieldset style="width: 330px;">
				<legend>
					<h3>ID 찾기</h3>
				</legend>
				<input type="text" class="form-control" name="name" id="name"
					size="15" placeholder="이름"><br>
				<p>
					<input type="text" class="form-control" name="password"
						id="password" size="7" placeholder="주민번호 앞 6자리" maxlength="6">-
					<input type="password" class="form-control" name="password"
						id="password" size="7" maxlength="7" placeholder="주민번호 뒤 7자리">
				<p>
					<input type="submit" class="btn" value="찾기"> <input
						type="button" class="btn" value="돌아가기" onclick="history.back()">
				<p>
			</fieldset> -->
		</form>
		<br> <br>
		<h3>비밀번호 찾기</h3>
		<form action="searchPass.do" method="post">
			<table class="table" style="width: 500px;">
				<caption>비밀번호를 찾기 위해 이름과 주민등록번호, 아이디를 입력해주세요.</caption>
				<tr height="30">
					<th>이름</th>
					<td><input type="text" class="form-control" name="name"
						placeholder="이름"></td>
				</tr>
				<tr height="30">
					<th>주민등록번호</th>
					<td><input type="text" class="form-control" name="rrnum1"
						placeholder="주민등록번호 앞 6 자리" maxlength="6"> - <input
						type="password" class="form-control" name="rrnum2"
						placeholder="주민등록번호 뒤 7 자리" maxlength="7"></td>
				</tr>
				<tr height="30">
					<th>ID</th>
					<td><input type="text" class="form-control" name="id"
						placeholder="아이디"></td>
				</tr>
				<tr height="30">
					<td colspan=2 align="center"><input type="submit" class="btn"
						value="비밀번호 찾기"> <input type="button" class="btn"
						value="취소" onclick="location.href='redirect:history.back();'">
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