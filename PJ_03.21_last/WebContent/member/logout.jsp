<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>로그아웃</title>
</head>
<body>
<%
		out.print("<script>");
		out.print("alert('로그아웃 되었습니다.');");
		out.print("location.href='main.do';");
		out.print("</script>");
%>
</body>
</html>