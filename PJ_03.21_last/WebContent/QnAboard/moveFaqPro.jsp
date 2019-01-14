<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
<c:if test="${error != null}">
	<script type="text/javascript">
		alert('${error}');
		history.back();
	</script>
</c:if>
<script type="text/javascript">
	location.href = 'listFaq.do';
</script>
</head>
<body>

</body>
</html>