<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%
	String id = (String) session.getAttribute("id");
	request.setAttribute("id", id);
%> --%>
<!doctype html>
<html lang="ko">
<head>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="utf-8">
<title>Q&A 글 작성</title>
<c:if test="${error != null}">
	<script type="text/javascript">
		alert('${error}');
		history.back();
	</script>
</c:if>
</head>
<body>
	<jsp:include page="/module/top.jsp" />
	<div class="container">
		<form action="writeQnaPro.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="num" value="${board.num}"> <input
				type="hidden" name="flag" value="${board.flag}"> <input
				type="hidden" name="ref" value="${board.ref}"> <input
				type="hidden" name="re_level" value="${board.re_level}"> <input
				type="hidden" name="re_step" value="${board.re_step}"> <input
				type="hidden" name="pageNum" value="${pageNum}">
			<table class="table" style="width:800px;">
				<caption>
					<b>Q&A 글 작성</b>
				</caption>
				<tr>
					<th>제목</th>
					<td><c:if test="${board.num == 0}">
							<input type="text" class="form-control" name="subject" required="required" maxlength="60">
						</c:if> <c:if test="${board.num != 0}">
							<input type="text" class="form-control" name="subject" required="required" maxlength="65" value="[답변]">
						</c:if></td>
				</tr>
				<tr>
					<th>작성자ID</th>
					<td><input type="text" class="form-control" name="writer" value="${id}"
						readonly="readonly"></td>
				</tr>
				<tr>
					<th>email</th>
					<td><input type="text" class="form-control" name="email" required value="${email}" maxlength="40"></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="file"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="form-control" rows="20" cols="80" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" class="btn" value="확인">
						<c:if test="${board.num != 0}">
							<input type="button" class="btn" value="취소"
								onclick="location.href='viewQna.do?num=${board.num}&pageNum=${pageNum}'">
						</c:if> <c:if test="${board.num == 0}">
							<input type="button" class="btn" value="취소"
								onclick="location.href='listQna.do?pageNum=${pageNum}'">
						</c:if></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp" />
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>