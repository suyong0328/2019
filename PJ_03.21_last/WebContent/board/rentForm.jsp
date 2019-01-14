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
    <title>렌트 하기</title>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
			<h2>렌트 내용 보기</h2>
	<form action="rentPro.do" method="post">
	<input type="hidden" name="num" value="${board.num }"><input
			type="hidden" name="pageNum" value="${pageNum }">
		<table class="table" style="width:700px;" align="center">
			<caption><h2>렌트 정보</h2></caption>
			<tr height="30">
				<th  width = "20%">작성자</th>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>${item.item_kind }</td>
			</tr>
			<tr>
				<th>거래방법</th>
				<td>${item.trans_type }</td>
			</tr>
			
			<tr>
				<th>물품명</th>
				<td >${item.item_name }</td>
			
			</tr>
			<tr>
				<th>가격</th>
				<td>${item.item_price }</td>
				</tr>
				<tr>
				<th>수량</th>
				<td>${item.item_count }</td>
			</tr>
			<tr>
				<th>기간</th>
				<td>${item.startday }~ ${item.endday }</td>
			</tr>
		</table>
		<hr>
		<table  class="table" style="width:700px;" align="center">
			<tr  height="30">
				<th width = "20%">분류</th>
				<td >${item.item_kind }</td>
			</tr>
			<tr>
				<th>수량</th>
				<td ><input type = "text" class="form-control" name = "rent_count" maxlength="8"></td>
			</tr>
			<tr>
				<th>구매자</th>
				<td><input type = "text" class="form-control" name = "rent_buyer" maxlength="20"></td>
				</tr>
				<tr>
				<th>수령자</th>
				<td><input type = "text" class="form-control" name = "deliveryname" maxlength="20"> </td>
			</tr>
				<tr>
				<th>전화번호</th>
				<td><input type = "text" class="form-control" name = "deliverytel" maxlength="20"> </td>
			</tr>
			<tr>
				<th>배송지</th>
				<td><input type = "text" class="form-control" name = "deliveryaddress" maxlength="50"></td>
			</tr>
			<tr>
			<td colspan="2" align = "center"><input type = "submit" class="btn" value = "확인"> <input type="button" class="btn" value="글목록"
					onclick="location.href='list.do?pageNum=${pageNum}'"></td>
			</tr>
		</table>
	</form>		
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>