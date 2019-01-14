<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String id = (String)session.getAttribute("id");
	request.setAttribute("id", id);
%>
<!doctype html>
<html lang="ko">
  <head>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <meta charset="utf-8">
    <title>Q&A 댓글 삭제</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/askDelete.js"></script>
	<c:if test="${result < 0}">
	<script type="text/javascript">
		alert('글 쓴이만 삭제할 수 있습니다.');
		history.back();
	</script>
</c:if>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
		<h2>댓글삭제</h2>
		<form action="deleteSubBoardPro.do" method="post" name="delForm"
			onsubmit="return ask()">
			<table class="table" style="width:500px;">
				<caption>삭제하시려면 비밀번호를 한번 더 입력해 주세요.</caption>
				<tr height="30">
					<th>비밀번호</th>
					<td><input type="password" class="form-control" name="sub_password"> <input
						type="hidden" name="num" value="${num}"> <input
						type="hidden" name="pageNum" value="${pageNum}"> <input
						type="hidden" name="sub_num" value="${sub_num}"> <input
						type="hidden" name="subPageNum" value="${subPageNum}"></td>
				</tr>
				<tr height="30">
					<td align="center"><input type="button" class="btn" value="글목록"
						onclick="location.href='listQna.do?pageNum=${pageNum}'"></td>
					<td align="center"><input type="submit" class="btn" value="글삭제"> <input
						type="button" class="btn" value="취소"
						onclick="location.href='viewQna.do?num=${num}&pageNum=${pageNum}'">
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>