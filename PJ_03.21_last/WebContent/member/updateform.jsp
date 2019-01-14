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
  	<script type="text/javascript" src="/Project/js/script.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <meta charset="utf-8">
    <title>회원정보</title>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
	<div class="container">
		<form action="updatepro.do" method="post" onsubmit="return chk2()">
		<fieldset style="width:600px;">
			<h3>회원정보</h3>
			<table class="table">
				<tr>
					<td>ID</td>
					<td><input type="text" class="form-control" id="id" name="id" value="${id}" readonly="readonly"> 
						</td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="text" class="form-control" id="password" name="password"></td>
				</tr>
				<tr>
					<td>PW확인</td>
					<td><input type="text" class="form-control" id="password2" name="password2">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" class="form-control" id="name" name="name" value="${member.name}"></td>
				</tr>
				<tr>
					<td>주민번호</td>
					<td><input type="text" class="form-control" id="rrnum1" name="rrnum1" size="8" value=${member.rrnum1}>
					<input type="password" class="form-control" id="rrnum2" size="8" name="rrnum2" value="${member.rrnum2}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" class="form-control" name="zipno" id="zipno" maxlength="7"
						size=7 value="${member.zipno}"> <input type="button" class="btn"
						onclick="execDaumPostcode()" value="우편번호 찾기"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" class="form-control" name="address1" id="address1"
						maxlength=80 size="40" value="${member.address1}"></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" class="form-control" name="address2" id="address2"
						maxlength=80 size="40" value="${member.address2}"></td>
				</tr>
				<tr>
					<td>TEL</td>
					<td><select name="tel1" id="tel1" value="${member.tel1}">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="02">02</option>
							<option value="031">031</option>
							<option value="032">032</option>
					</select>- <input type="text" name="tel2" id="tel2" size="4" maxlength="4" value="${member.tel2}">-
						<input type="text" name="tel3" id="tel3" size="4" maxlength="4"value=${member.tel3}></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" class="form-control" name="email" id="email" maxlength=80
						size="40" value="${member.email }"></td>
				</tr>
				<tr>
		<td colspan=2 align="center"><input type="submit" class="btn" value="수정">
		                             <input type="reset" class="btn" value="다시작성">
		                             <input type="button" class="btn" value="돌아가기" onclick="history.back()">
		 </td>
	</tr>
			</table>
		</fieldset>
	</form>
	</div>
	<jsp:include page="/module/bottom.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>