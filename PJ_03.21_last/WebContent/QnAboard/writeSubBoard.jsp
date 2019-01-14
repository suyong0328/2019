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
	location.href = 'writeSubBoardPro.do?num=${num}&pageNum=${pageNum}&sub_writer=${sub_writer}&sub_content=${sub_content}&ref=${ref}&subPageNum=${subPageNum}';
</script>
</head>
<body>

</body>
</html>