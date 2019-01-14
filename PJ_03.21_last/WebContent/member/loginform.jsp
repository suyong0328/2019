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
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <meta charset="utf-8">
    <title>로그인</title>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container" style="padding:80px;">
		<form action="loginresult.do" method="post" onsubmit="return chk()">
		<fieldset style="width:230px;">
			<legend>
				<h2>LOGIN</h2>
			</legend>
			<input type="text" class="form-control" name="id" id="id" size="15" placeholder="아이디">
			<p>
				<input type="password" class="form-control" name="password" id="password" size="15" placeholder="비밀번호"><br>
				<input type="submit" class="btn" value="LOGIN">
			<p>
				<input type="button" class="btn" value="회원가입" onclick="app()"> <input
					type="button" class="btn" value="아이디/비밀번호찾기" onclick="app2()">
		</fieldset>
	</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>