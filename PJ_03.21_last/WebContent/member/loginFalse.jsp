<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String num = request.getParameter("num");
	if(num.equals("2")){
		out.print("<script>");
		out.print("alert('아이디가 존재하지 않습니다.');");
		out.print("history.back();");
		out.print("</script>");
	} else{
		out.print("<script>");
		out.print("alert('비밀번호가 틀렸습니다.');");
		out.print("history.back();");
		out.print("</script>");
	}
%>
<html>
<head>
</head>
<body>
</body>
</html>