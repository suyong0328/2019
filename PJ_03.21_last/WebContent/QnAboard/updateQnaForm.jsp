<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ko">
<head>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="utf-8">
<title>Q&A 글 수정</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
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
		<div align="center">
			<h2>Q&A 글 수정</h2>
			<form action="updateQnaPro.do" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="num" value="${num}"> <input
					type="hidden" name="pageNum" value="${pageNum}">

				<table width="400" class="table">
					<tr>
						<th>제목</th>
						<td><input type="text" size="40" class="form-control" maxlength="50"
							name="subject" value="${board.subject}" required="required"></td>
					</tr>
					<tr>
						<th>작성자ID</th>
						<td class="left"><input type="text" class="form-control" size="10" maxlength="10"
							name="writer" value="${board.writer}" readonly="readonly"
							required="required"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" size="40" class="form-control" maxlength="30" name="email"
							value="${board.email}" required="required"></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="file"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="20" cols="70" name="content"
								required="required">
					${board.content}</textarea></td>
					</tr>
					<!-- <tr>
						<th>비밀번호</th>
						<td class="left"><input type="password" size="10"
							maxlength="10" name="password"></td>
					</tr> -->
					<tr>
						<td><input type="button" class="btn" value="목록보기"
							onclick="location.href='listQna.do?pageNum=${pageNum}'"></td>
						<td align="center"><input type="submit" class="btn" value="글 수정">
							<input type="button" class="btn" value="취소"
							onclick="location.href='viewQna.do?num=${num}&pageNum=${pageNum}'">
							<input type="reset" class="btn" value="다시작성"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="/module/bottom.jsp" />
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>