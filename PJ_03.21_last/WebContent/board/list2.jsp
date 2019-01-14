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
    <title>앨범 게시판</title>
    <style type="text/css">
    /* 	div.container2 {
			width: 400px;
			border: 1px solid black;
		}
		div.box {
			box-sizing: border-box;
			width: 200px;
			padding: 0px;
			margin: 0px; 
			border: 1px solid black;
			float: left;
		} */
    </style>
    <script type="text/javascript">
	    function viewselect(selectvalue) {
			document.location = selectvalue.value;
		}
    </script>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
	<ul class="nav nav-pills navbar-right" role="tablist">
			<li class="dropdown">
	    		<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="border:0px;">
	      			뷰 선택 <span class="caret"></span></a>
	      		<ul class="dropdown-menu" role="menu">
		        	<li><a href="list.do">글</a></li>
		        	<li><a href="list2.do">앨범</a></li>
	      		</ul>
	   	 	</li>
  	  	</ul>
		<h2>앨범 게시판</h2>
	<table class="table" align="center">
		<c:set var="rowNum" value="${boardpb.rowNum }" />
		<c:if test="${itemtotal==0 }">데이터가 없습니다.</c:if>
		<c:if test="${itemtotal>0 }">

			<tr>
			<td>
			<div class="container2">

				<c:forEach var="item" items="${item }">
					<div class="box" align = 'center'>
						<a
							href='view.do?num=${item.item_id}&pageNum=${itempb.currentPage}&rn=${rowNum}'><img
							src="imageFile/${item.item_image}" width="200" height="100"></a>
					</div>
				</c:forEach>
				<div style="clear: both;"></div>
				<c:set var="rowNum" value="${rowNum-1}" />
			</div>
			</td>
			</tr>

		</c:if>
		<tr align="right">
			<td colspan="7"><a
				href="writeForm.do?pageNum=${boardpb.currentPage }"><b>글쓰기</b></a><br>
			</td>
		</tr>
	</table>
	<p>
	<div align="center">
		<c:if test="${itempb.startPage>pb.BLOCKSIZE }">
			<a href="list2.do?pageNum=${itempb.currentPage }">이전</a>
		</c:if>
		<c:forEach var="i" begin="${itempb.startPage }"
			end="${itempb.endPage }">
			<a href="list2.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${itempb.endPage<itempb.pageCount }">
			<a href="list2.do?pageNum=${itempb.startPage+itempb.BLOCKSIZE }">다음</a>
		</c:if>
		<br>
		<form action="searchPro.do" method="post">
			<input type="text" name="search" id="search"> <input
				type="submit" value="검색">
		</form>
	</div>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>