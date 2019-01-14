<%@page import="memberService.subaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>결과</title>
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
		String password = (String) session.getAttribute("password");

		if (id == null || id.equals(""))
			response.sendRedirect("start.do");
	%>
	<form>
		<fieldset style="width: 210; height: 150;">
			<h4>${id}님환영합니다.</h4>
			<h5>CM rental에서 즐거운시간 되세요.</h5>
			<input type="button" value="로그아웃" onclick="location.href='logout.do'">
			<input type="button" value="정보 수정"onclick="location.href='updateform.do'"> 
		</fieldset>
</body>
</html>