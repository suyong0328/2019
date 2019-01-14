<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <c:if test="${result==2}">
  	 <script>
  	 	alert("사용 가능한 id 입니다.");
  	 	window.self.close();
  	 </script>
  </c:if>
  <c:if test="${result==1}">
     <script>
     	alert("이미 사용중인 id입니다.");
     	window.self.close();
     </script>
  </c:if>
</body>
</html>
