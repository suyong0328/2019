<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>게시글 입력</title>
</head>
<body>
	<c:if test="${result>0}">
		<script>
			location.href = "viewQna.do?num=${num}&pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result<0}">
		<script>
			alert('${error}');
			history.back();
		</script>
	</c:if>

</body>
</html>