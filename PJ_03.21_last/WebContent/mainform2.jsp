<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");
	request.setAttribute("id", id);
%>
<!doctype html>
<html lang="ko">
  <head>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="css/bootstrap.css">
    <meta charset="utf-8">
    <title>CM rental Home</title>
    <style type="text/css">
    
    </style>
  </head>
  <body>
	<jsp:include page="module/top.jsp"/>
	<div class="container">
		
	</div>
	<jsp:include page="module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
  </body>
</html>