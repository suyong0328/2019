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
<title>Q&A 글 읽기</title>
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
			<h2>Q&A글 내용 보기</h2>
			<form action="writeSubBoard.do">
				<input type="hidden" value="${pageNum}" name="pageNum">
				<table class="table" width="500">
					<tr>
						<th>글번호</th>
						<td><input type="hidden" value="${board.num}" name="num">${board.num}</td>
						<th>조회수</th>
						<td>${board.readcount}</td>
						<th align="center">첨부파일</th>
					</tr>

					<tr>
						<th>작성자ID</th>
						<td>${board.writer}</td>
						<th>작성일</th>
						<td>${board.reg_date}</td>
						<td rowspan="3" align="center"><img
							src="refFiles/${board.filename}" width="300" height="300"
							alt="첨부파일이 없습니다."></td>
					</tr>

					<tr>
						<th>글제목</th>
						<td colspan="3">${board.subject}</td>
					</tr>
					<tr>
						<th>글내용</th>
						<td colspan="3"><pre>
   ${board.content}
   </pre></td>
					</tr>
					<c:if test="${total == 0}">
						<tr>
							<td colspan="5" align="center">댓글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${total != 0}">
						<tr>
							<th>댓글목록</th>

							<td colspan="4"><table style="width: 600px">
								<tr>
										<th>내용</th>
										<th>아이디</th>
										<th>등록일</th>
									</tr>
									<c:forEach var="s" items="${subList}" varStatus="n">
										<tr>
											<td height="1">${s.sub_content}</td>
											<td height="1">${s.sub_writer}</td>
											<td height="1">${s.reg_date}</td>
											<td><input type="button" value="삭제" class="btn"
												onclick="location.href='deleteSubBoardForm.do?num=${board.num}&sub_num=${s.sub_num}&pageNum=${pageNum}&subPageNum=${subPageNum}'"></td>
										</tr>
									</c:forEach>

									<tr>
										<td colspan="4" align="center"><c:if
												test="${pb.startPage > pb.BLOCKSIZE}">
												<a
													href="viewQna.do?num=${board.num}&pageNum=${pageNum}&subPageNum=${pb.startPage - pb.BLOCKSIZE}">[이전]</a>
											</c:if> <c:forEach var="i" begin="${pb.startPage}"
												end="${pb.endPage}">
												<a
													href="viewQna.do?num=${board.num}&pageNum=${pageNum}&subPageNum=${i}">${i}</a>
											</c:forEach> <c:if test="${pb.endPage < pb.pageCount}">
												<a
													href="viewQna.do?num=${board.num}&pageNum=${pageNum}&subPageNum=${pb.startPage + pb.BLOCKSIZE}">[다음]</a>
											</c:if></td>
									</tr>
								</table></td>
						</tr>
					</c:if>
					<tr>
						<th>댓글 등록</th>
						<td colspan="4" ><input type="text" class="form-control" name="sub_content"
							size="70"> <!-- <td><input type="text" name="sub_writer"><br> <input
							type="password" name="sub_password"></td> --> <input
							type="submit" class="btn" value="댓글달기"></td>
					</tr>
					<tr>
						<c:if test="${faqChk!='Y'}">
							<td align="center"><input type="button" class="btn" value="글목록"
								onclick="location.href='listQna.do?pageNum=${pageNum}'"></td>
						</c:if>
						<c:if test="${faqChk=='Y'}">
							<td align="center"><input type="button" class="btn" value="글목록"
								onclick="location.href='listFaq.do?pageNum=${pageNum}'"></td>
						</c:if>
						<td colspan="4" align="center"><input type="button" class="btn"
							value="글수정"
							onclick="location.href='updateQnaForm.do?num=${board.num}&pageNum=${pageNum}'">
							<input type="button" class="btn" value="글삭제"
							onclick="location.href='deleteQnaForm.do?num=${board.num}&pageNum=${pageNum}'">
							<input type="button" class="btn" value="답글쓰기"
							onclick="location.href='writeQnaForm.do?pageNum=${pageNum}&num=${board.num}&flag=${board.flag}&ref=${board.ref}&re_step=${board.re_step}&re_level=${board.re_level}&chk=Y'">
						</td>
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