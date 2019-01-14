<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>정보수정 처리</title>
</head>
<body>
  <c:if test="${result>0}">
  	 <script>
  	 	alert("수정성공");
  	 	alert("다시 로그인하세요.")
  	 	location.href="start.do";
  	 </script>
  </c:if>
  <c:if test="${result==0}">
     <script>
     	alert("수정 실패!");
        history.back();
     </script>
  </c:if>
</body>
</html>