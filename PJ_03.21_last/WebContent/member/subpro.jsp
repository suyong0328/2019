<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원가입 처리</title>
</head>
<body>
  <c:if test="${result>0}">
  	 <script>
  	 	alert("입력성공");
  	 	location.href="start.do";
  	 </script>
  </c:if>
  <c:if test="${result==0}">
     <script>
     	alert("입력 실패!");
        history.back();
     </script>
  </c:if>
</body>
</html>