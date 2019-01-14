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
    <title>댓글 삭제</title>
    <script type="text/javascript">
	function deleteSave(){
		if(delForm.password.value==""){
			alert("비밀번호를 입력하세요.");
			delForm.password.focus();
			return false;
		}
		return true;
	}
</script>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
		<form action="subdeletePro.do" method="post" name="delForm" onsubmit="return deleteSave()">
<table class="table" style="width:500px;" >
	<caption><h2>댓글 삭제 확인</h2></caption>
	<tr height="30">
	<th>비밀번호</th><td><input type="password" class="form-control" name="password">
	<input type="hidden" name="subnum" value="${subnum}">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}"></td>
	</tr>
	<tr height="30">
	    <td colspan=2 align="center">
	    <input type="submit" class="btn" value="댓글삭제">
	    <input type="reset" class="btn" value="글목록"
	      onclick ="location.href='list.do?pageNum=${pageNum}'"> 
	      <input type="button" class="btn" value="취소" onclick="history.back()">
	</tr>
</table>
</form>		
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>