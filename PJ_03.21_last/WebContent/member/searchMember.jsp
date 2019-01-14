<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
<c:if test="${error != null}">
	<script type="text/javascript">
		alert('${error}');
		location.href = 'idpass.do';
	</script>
</c:if>
<c:if test="${error == null}">
	<c:if test="${password == null}">
		<script type="text/javascript">
			alert('회원님의 아이디는 ${id}입니다.');
			location.href = 'start.do';
		</script>
	</c:if>
	<c:if test="${password != null}">
		<script type="text/javascript">
			alert('회원님의 비밀번호는 ${password}입니다.');
			location.href = 'start.do';
		</script>
	</c:if>
</c:if>
</head>
<body>

</body>
</html>