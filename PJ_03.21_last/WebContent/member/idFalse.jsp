<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>