<%@page import="dto.Item"%>
<%@page import="dto.Board"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");
	request.setAttribute("id", id);
	// hot 글
	Board board = (Board)request.getAttribute("board");
	request.setAttribute("board", board);
	Item item = (Item)request.getAttribute("item");
	request.setAttribute("item", item);
	// 최신글
	Board nboard = (Board)request.getAttribute("nboard");
	request.setAttribute("nboard", nboard);
	Item nitem = (Item)request.getAttribute("nitem");
	request.setAttribute("nitem", nitem);
	
	
%>
<!doctype html>
<html lang="ko">
  <head>
  	<meta name="viewprot" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <meta charset="utf-8">
    <title>CM rental Home</title>
    <style type="text/css">
    .jumbotron{
    	background-image: url(module/hot.jpg);
    	background-size: cover;
    }
    .j2{
    	color: white;
    }
    </style>
  </head>
  <body>
	<jsp:include page="/module/top.jsp"/>
  	<div class="container">
		<div class="jumbotron">
			<h3 class="j2">가장 Hot한 글</h3>
			<c:if test="${board.num == 0}">
				<div>
					<h2 class="j2">게시판에 글이 존재하지 않습니다...</h2>
					<a href="writeForm.do?pageNum=1">글 작성하기</a>
				</div>
			</c:if>      
			<c:if test="${board.num != 0}">
			<div class="media-left">
        				<a href="view.do?num=${board.num}"><img alt="핫한 글 사진" src="imageFile/${item.item_image}" height="150px" width="150px"></a>
        			</div>
        		<div class="media-body">
        			<h4><a href="view.do?num=${board.num}">${board.subject}</a>&nbsp;<span class="badge">hot</span></h4>
        			<span class="j2">${board.content}</span>
        		</div>
        		</c:if>
		</div>
		<p></p>      
		<p></p>      
	</div>
    <div class="container">
      <div>
        <div class="panel panel-primary">
        	<div class="panel-heading">
        		<h3 class="panel-title"><span class="glyphicon glyphicon-pencil"></span>
        		&nbsp; &nbsp;최신 글 목록</h3>
        	</div>
        	<c:if test="${nboard.num == 0}">
				<div>
					<h2>게시판에 글이 존재하지 않습니다...</h2>
					<a href="writeForm.do?pageNum=1">글 작성하기</a>
				</div>
			</c:if> 
			<c:if test="${nboard.num != 0}">  
        	<div class="panel-body">
        		<div class="media">
        			<div class="media-left">
        				<a href="view.do?num=${nboard.num}"><img alt="새글 사진" src="imageFile/${nitem.item_image}" height="150px" width="150px"></a>
        			</div>
        		<div class="media-body">
        			<h4><a href="view.do?num=${nboard.num}">${nboard.subject}</a>&nbsp;<span class="badge">new</span></h4>
        			${nboard.content}
        		</div>
        	</div>
        </div>
        </c:if>
      </div>
	</div>
	</div> <!-- 메인 content 끝-->
	<jsp:include page="/module/bottom.jsp"/><!-- 푸터 -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>