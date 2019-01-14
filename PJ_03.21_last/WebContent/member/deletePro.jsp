<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
<c:if test="${result == 1}">
	<script type="text/javascript">
		alert('성공적으로 탈퇴처리 되었습니다.');
		location.href = 'main.do';
	</script>
</c:if>
<c:if test="${result != 1}">
	<script type="text/javascript">
		alert('회원 탈퇴에 실패하였습니다.');
		history.back();
	</script>
</c:if>
</head>
<body>

</body>
</html>