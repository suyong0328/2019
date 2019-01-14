<%@page import="dao.BoardDao"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ko">
  <head>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <meta charset="utf-8">
    <title>보기</title>
	<script type="text/javascript">
	function chk() {
		if (frm.sub_writer.value == "") {
			alert("작성자 입력하세요");
			frm.sub_writer.focus();
			return false;
		}
		if (frm.sub_content.value == "") {
			alert("내용을 입력하세요");
			frm.sub_writer.focus();
			return false;
		}
		if (frm.sub_password.value == "") {
			alert("비밀번호 입력하세요");
			frm.sub_writer.focus();
			return false;
		}
		return true;
	}
</script>
    <style type="text/css">
    td:nth-child(2){
    	width: 385.5px;
    }
    /* table:not(#sub_b) td{ // 댓글또한 테두리 선이 생김 ....
    	border: 1px solid rgb(219, 217, 217);
    } */
    </style>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
			<h2>글내용 보기</h2>
	<form action="sub_writefreePro.do" method="post" onsubmit="return chk()"
		name="frm" id="frm">
		<table class="table" style="border:1px solid rgb(219, 217, 217)" align="center">
			<tr height="30">
				<th>글번호</th>
				<td>${rn}</td>
				<th>조회수</th>
				<td>${board.readcount }</td>
			</tr>
			<tr height="30">
				<th>작성자</th>
				<td>${board.writer}</td>
				<th>작성일</th>
				<td>${board.reg_date }</td>
			</tr>
			<tr height="30">
				<th>글제목</th>
				<td colspan="3">${board.subject }</td>
			</tr>
			<c:if test="${!empty item.item_kind }">


				<%-- <tr>
					<th>분류</th>
					<td>${item.item_kind }</td>
					<th>거래방법</th>
					<td>${item.trans_type }</td>
				</tr>
				<tr>
					<th>물품명</th>
					<td colspan="3">${item.item_name }</td>
				</tr>
				<tr>
					<th>가격</th>
					<td>${item.item_price }</td>
					<th>수량</th>
					<td>${item.item_count }</td>
				</tr>
				<tr>
					<th>기간</th>
					<td colspan="3">${item.startday }~${item.endday }</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td colspan="3"><img src="imageFile/${item.item_image }"
						width="300" height="200"></td>
				</tr> --%>
			</c:if>
			<tr>
				<th>글내용</th>
				<td colspan="3" class="left"><textarea class="form-control" rows="20" cols="100" disabled="disabled">${board.content }</textarea></td>
			</tr>
			<tr>
				<th>댓글</th>
				<td colspan="3">
					<table>
						<c:set var="rowNum" value="${pb.rowNum }" />
						<c:set var = "num" value = "${num }"/>
						<c:if test="${total==0 }">데이터가 없습니다.</c:if>
						<c:if test="${total>0 }">
						<tr>
						<th>순번</th><th>작성자</th><th style="width:1400px;">내용</th><th style="width:200px;">작성일</th></tr>
							<c:forEach var="sub_board" items="${list }">
									<td>${rowNum}</td>
									<td>${sub_board.sub_writer }</td>
									<td>${sub_board.sub_content}</td>
									<td>${sub_board.reg_date}</td>
									<td><input type="button" class="btn" value="삭제"
										onclick="location.href='subdeletefreeForm.do?num=${board.num }&subnum=${sub_board.sub_num}&pageNum=${pageNum}'">
									</td>

								<tr>
									<c:set var="rowNum" value="${rowNum-1}" />
							</c:forEach>
							<tr>
								<td colspan="4" align="center"><c:if
										test="${pb.startPage>pb.BLOCKSIZE }">
										<a href="viewfree.do?num=${num }&pageNum=${pb.currentPage }">이전</a>
									</c:if> <c:forEach var="i" begin="${pb.startPage }"
										end="${pb.endPage }">
										<a href="viewfree.do?num=${num }&pageNum=${i }">${i }</a>
									</c:forEach> <c:if test="${pb.endPage<pb.pageCount }">
										<a href="viewfre.do?num=${num }&pageNum=${pb.startPage+pb.BLOCKSIZE }">다음</a>
									</c:if></td>
							</tr>
							<tr>
								<td>내용</td>
							</tr>
						</c:if>
						
						<tr>
								<td width="500"><input type="text" class="form-control" name="sub_content"></td> 
								<td><input type="hidden" name="num" value='${board.num}'>
									<input type="hidden" name="pageNum" value='${pageNum}'> 
									<input type="submit" class="btn" value="등록"></td>
							</tr>
					</table>





				</td>
			</tr>
			<tr height="30">
				<td colspan="4" align="center"><input type="button" class="btn" value="글수정"
					onclick="location.href='updatefreeForm.do?num=${num}&pageNum=${pageNum }'">
					<input type="button" class="btn" value="글삭제"
					onclick="location.href='deletefreeForm.do?num=${board.num}&pageNum=${pageNum}'">
					<input type="button" class="btn" value="답글쓰기"
					onclick="location.href='refwritefreeForm.do?num=${board.num }&ref=${board.ref }&re_step=${board.re_step }&re_level=${board.re_level }&pageNum=${pageNum}'">
					<input type="button" class="btn" value="글목록"
					onclick="location.href='listfree.do?pageNum=${pageNum}'"></td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>