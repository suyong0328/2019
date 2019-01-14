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
    <title>CM rental Home</title>
	<script type="text/javascript">
		function viewselect(selectvalue) {
			document.location = selectvalue.value;
		}
	</script>
    <style type="text/css">
    
    </style>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
	
  	  	<h2>자유 게시판</h2>
	<table class="table" align="center">
		<c:set var="rowNum" value="${pb.rowNum }" />
		<c:if test="${total==0 }">데이터가 없습니다.</c:if>
		<c:if test="${total>0 }">

		

			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>이메일</th>
				<th>조회수</th>
				<th>IP</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="list" items="${list }">
					<tr>
						<td>${rowNum}</td>
						<td><c:if test="${list.re_level >0}">
								<img src='images/level.gif' width='${list.re_level*10}'
									height='5'>
								<img src='images/re.gif'>
							</c:if> <a href='viewfree.do?num=${list.num}&pageNum=${pb.currentPage}&rn=${rowNum}'>
								${list.subject} </a> <c:if test="${list.readcount > 20 }">
								<img src="images/hot.gif">
							</c:if></td>
						<td><a href="mailto:${list.email}">${list.writer}</a></td>
						<td><a href="mailto:${list.email}">${list.email}</a></td>
						<td>${list.readcount}</td>
						<td>${list.ip }</td>
						<td>${list.reg_date }</td>
					</tr>
				<c:set var="rowNum" value="${rowNum-1}" />
			</c:forEach>
			</c:if>
		<tr align="right">
			<td colspan="7"><a
				href="writefreeForm.do?pageNum=${pb.currentPage }"><b>글쓰기</b></a><br>
			</td>
		</tr>
	</table>
	<p>
	<div align="center">
		<c:if test="${pb.startPage>pb.BLOCKSIZE }">
			<a href="listfree.do?pageNum=${pb.currentPage }">이전</a>
		</c:if>
		<c:forEach var="i" begin="${pb.startPage }" end="${pb.endPage }">
			<a href="listfree.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pb.endPage<pb.pageCount }">
			<a href="listfree.do?pageNum=${pb.startPage+pb.BLOCKSIZE }">다음</a>
		</c:if>
		<br>
		<form action="searchfreePro.do" method="post">
			<input type="text" name="search" id="search"> <input
				type="submit" class="btn" value="검색">
		</form>
	</div>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>