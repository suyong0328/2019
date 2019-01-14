<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String) session.getAttribute("id");
	request.setAttribute("id", id);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="utf-8">
</head>
<body>
	<!-- 상단 메뉴바 -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span class="icon-bar"></span>
					<!-- 게시판 갯수 -->
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main.do"><img
					style="height: 40px;"
					src="${pageContext.request.contextPath}/module/logo.jpg" alt="로고"></a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="main.do">공지사항<span class="sr-only"></span></a></li>
					<li><a href="list.do">렌탈 게시판</a></li>
					<li><a href="listfree.do">자유 게시판</a></li>
					<!-- <li class="dropdown">
  						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
  							aria-haspopup="true" aria-expanded="false">게시판 목록<span class="caret"></span></a>
  						<ul class="dropdown-menu">
  							<li><a href="list.do">렌탈 등록 게시판</a></li>
  							<li><a href="list2.do">렌탈 신청 게시판</a></li>
  							<li><a href="#">공지사항</a></li>
  						</ul>
  					</li> -->
					<li><a href="listQna.do">Q&A 질문답변</a></li>
				</ul>
				<form class="navbar-form navbar-left" action="searchPro.do"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="search"
							placeholder="내용을 입력하세요.">
					</div>
					<input type="submit" class="btn" value="검색">
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><c:if test="${empty id}">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">로그인<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="start.do">로그인</a></li>
								<li><a href="idpass.do">아이디/비밀번호 찾기</a></li>
								<li><a href="sub.do">회원가입</a></li>
							</ul>
						</c:if> <c:if test="${!empty id}">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">회원정보<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">채팅하기</a>
								<li><a href="updateform.do?id=${id}">회원정보 보기</a></li>
								<li><a href="deleteMemberForm.do">회원 탈퇴</a></li>
								<li><a href="logout.do">로그아웃</a></li>
							</ul>
						</c:if></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">채팅창</h4>
				</div>
				<div class="modal-body">
					<form action="loginPro.do" method="post">
						<table border="1">
							<tr>
								<th>아이디</th>
								<td><input type="text" class="form-control" name="id"></td>
								<td rowspan="2"><input type="submit"
									class="btn btn-default" value="로그인"></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" class="form-control"
									name="password"></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- 	        로그인 Modal
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로그인</h4>
        </div>
        <div class="modal-body">
          <form action="loginPro.do" method="post">
          	<table border="1">
          		<tr>
          			<th>아이디</th>
          			<td><input type="text" class="form-control" name="id"></td>
          			<td rowspan="2"><input type="submit" class="btn btn-default" value="로그인"></td>
          		</tr>
          		<tr>
          			<th>비밀번호</th>
          			<td><input type="password" class="form-control" name="password"></td>
          		</tr>
          	</table>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div> -->
	<!-- 	        아이디 / 비밀번호 찾기 Modal
  <div class="modal fade" id="findModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">아이디 / 비밀번호 찾기</h4>
        </div>
        <div class="modal-body">
          <form action="loginPro.do" method="post">
          	<table border="1">
          		<tr>
          			<th>아이디</th>
          			<td><input type="text" class="form-control" name="id"></td>
          			<td rowspan="2"><input type="submit" class="btn btn-default" value="로그인"></td>
          		</tr>
          		<tr>
          			<th>비밀번호</th>
          			<td><input type="password" class="form-control" name="password"></td>
          		</tr>
          	</table>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div>
 회원가입 Modal
  <div class="modal fade" id="insertModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">회원가입</h4>
        </div>
        <div class="modal-body">
          <form action="loginPro.do" method="post">
          	<table border="1">
          		<tr>
          			<th>아이디</th>
          			<td><input type="text" class="form-control" name="id"></td>
          			<td rowspan="2"><input type="submit" class="btn btn-default" value="로그인"></td>
          		</tr>
          		<tr>
          			<th>비밀번호</th>
          			<td><input type="password" class="form-control" name="password"></td>
          		</tr>
          		<tr>
          			<th>이름</th>
          			<td><input type="text" class="form-control" name="name"></td>
          		</tr>
          		<tr>
          			<th>주민등록번호</th>
          			<td>
          				<input type="text" class="form-control, col-sm-4" name="rrnum1" maxlength="6">
          				<input type="password" class="form-control, col-sm-4" name="rrnum2" maxlength="7">
          			</td>
          		</tr>
          		<tr>
          			<th>Email</th>
          			<td><input type="text" class="form-control" name="password"></td>
          		</tr>
          		<tr>
          			<th>주소</th>
          			<td><input type="text" class="form-control" name="password"></td>
          		</tr>
          		
          	</table>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div> -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>