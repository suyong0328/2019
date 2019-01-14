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
    <title>답글 작성</title>
    <style>
    th{
    	width: 130px;
    }
    </style>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
				<form action="refwritefreePro.do" method="post">
<input type="hidden" name="num" value='${board.num}'>
<input type="hidden" name="pageNum" value='${pageNum}'>
<input type="hidden" name="ref" value='${board.ref}'>
<input type="hidden" name="re_level" value='${board.re_level}'>
<input type="hidden" name="re_step" value='${board.re_step}'>
<table class="table">
<caption><b>답글 작성</b></caption>
<tr><th>제목</th><td>
   <c:if test="${board.num==0}">
   <input type="text" class="form-control" name="subject" required>
   </c:if>
   <c:if test="${board.num!=0}">
   <input type="text" class="form-control" name="subject" required value="[답변]">
   </c:if>
 </td>
  </tr>
  <tr>
    <th><b>작성자</b></th><td><input type="text" class="form-control" name="writer"></td> 
  </tr>
  <tr>
    <th><b>email</b></th><td><input type="text" class="form-control" name="email"></td> 
  </tr>
  <tr>
    <th><b>비밀번호</b></th><td><input type="password" class="form-control" name="password"></td> 
  </tr>
  <tr>
    <th><b>비밀번호확인</b></th><td><input type="password" class="form-control" name="password2"></td> 
  </tr>
  <tr>
    <th><b>내용</b></th><td><textarea class="form-control" rows="20" cols="110" name="content"></textarea> </td> 
  </tr>
<tr>
	<td colspan="3" align="center">
		<input type="submit" class="btn" value="확인">
		<input type="button" class="btn" value="취소" onclick="history.back()">
	</td>
</tr>   
   
</table>
</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>