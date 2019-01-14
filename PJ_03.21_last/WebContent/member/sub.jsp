<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String id = (String) session.getAttribute("id");
	request.setAttribute("id", id);
%>
<!doctype html>
<html lang="ko">
<head>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="utf-8">
<title>회원가입</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function chk3() {
		var id = document.getElementById('id').value;
		if (id.length == 0) {
			alert("id를 입력하세요");
			document.getElementById('id').focus();
			return;
		}
		window.open("idFalse2.do?id=" + id, "idchk",
				"height=100,width=450,resize=yes");
	}
</script>
</head>
<body>
	<jsp:include page="/module/top.jsp" />
	<div class="container">
		<form action="subpro.do" method="post" onsubmit="return chk2()">
			<fieldset style="width: 700px;">
				<legend>회원가입</legend>
				<table class="table">
					<tr>
						<td>ID</td>
						<td><input type="text" class="form-control" id="id" name="id"
							maxlength="20"> <input type="button" class="btn"
							name="id2" id="id2" value="중복확인" onclick="return chk3()"></td>
					</tr>
					<tr>
						<td>PW</td>
						<td><input type="password" class="form-control" id="password"
							name="password" maxlength="20"></td>
					</tr>
					<tr>
						<td>PW확인</td>
						<td><input type="password" class="form-control"
							id="password2" name="password2" maxlength="20"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" class="form-control" id="name"
							name="name" maxlength="10"></td>
					</tr>
					<tr>
						<td>주민번호</td>
						<td><input type="text" class="form-control" id="rrnum1"
							name="rrnum1" size="8" maxlength="6"
							placeholder="주민등록번호 앞 6자리 입력"> <input type="password"
							class="form-control" id="rrnum2" size="8" name="rrnum2"
							maxlength="7" placeholder="주민등록번호 뒤 7자리 입력"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" class="form-control" name="zipno"
							id="zipno" maxlength="7" size=7> <input type="button"
							class="btn" onclick="execDaumPostcode()" value="우편번호 찾기"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" class="form-control" name="address1"
							id="address1" maxlength=80 size="40"><input type="hidden"
							id="jibunAddress"><input type="hidden" id="guide"></td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td><input type="text" class="form-control" name="address2"
							id="address2" maxlength=80 size="40"></td>
					</tr>
					<tr>
						<td>TEL</td>
						<td><select name="tel1" id="tel1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="02">02</option>
								<option value="031">031</option>
								<option value="032">032</option>
						</select>- <input type="text" name="tel2" id="tel2" size="4" maxlength="4">-
							<input type="text" name="tel3" id="tel3" size="4" maxlength="4"></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" class="form-control" name="email"
							id="email" maxlength=80 size="40"></td>
					</tr>
					<tr>
						<td colspan=2 align="center"><input type="submit" class="btn"
							value="확인"> <input type="reset" class="btn" value="다시작성">
							<input type="button" class="btn" value="돌아가기"
							onclick="history.back()"></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<jsp:include page="/module/bottom.jsp" />
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>