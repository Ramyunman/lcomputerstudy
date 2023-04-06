<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board 목록</title>
</head>
<style>
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:300px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	ul {
		width:600px;
		height:50px;
		margin:10px auto;	
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}
	form {
		text-align:center;
		margin:50px
	}

</style>
<body>
<h1> Board 목록 </h1>
	<table>
		<tr>
			<td colspan="3">전체 게시글 수: ${pagination.amount}</td>
		<tr>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>내용</th>
		</tr>
		<c:forEach items="${boardList}" var="board" varStatus="status">
			<tr>
				<td><a href="${pageContext.request.contextPath}/board-detail/${board.bIdx}">${(board.currentPage - 1) * board.pageSize + status.index + 1}</a></td>
				
				<c:choose>
					<c:when test="${board.bDepth > 0 }">
						<td style="text-align: left;">
							<c:forEach var="i" begin="1" end="${board.bDepth}" step="1">
							&nbsp;&nbsp;
							</c:forEach>
							ㄴ${board.bTitle}
						</td>
					</c:when>
					<c:when test = "${board.bDepth == 0 }">
						<td style="text-align: left; padding-left: 10px;">${board.bTitle}</td>
					</c:when>
				</c:choose>
				
				<td>${board.bContent}</td>
								
			</tr>
		</c:forEach>
	</table>
	&nbsp;
	<a href="${pageContext.request.contextPath}/"> 홈으로 </a>
	&nbsp;
	<a href="${pageContext.request.contextPath}/board-beforeSignUp"> 글등록하기 </a>
	
<!-- 아래부터 pagination -->
	<div>
		<ul>
			<c:choose>
				<c:when test="${ pagination.prevPage < 0 }">
					<li style="display:none;">					
						<span>◀</span>	
					</li>
				</c:when>
				<c:when test="${ pagination.prevPage <= pagination.endPage }">
					<li>					
						<a href="${pageContext.request.contextPath}/board-list?page=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
				
			</c:choose>
			<c:forEach var ="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
					<c:when test="${ pagination.page == i }">		<%-- 현재페이지가 i와 같다면 회색으로 나오게 한다. --%>
						<li style="background-color:#ededed;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${ pagination.page != i }">		<%-- 현재페이지가 i와 다르다면 링크를 걸게 한다. --%>
						<c:choose>
							<c:when test="${pagination.search.tcw == null }">
								<li>
									<a href="${pageContext.request.contextPath}/board-list?page=${i}">${i}</a>
								</li>
							</c:when>
							<c:when test="${pagination.search.tcw == 1 || pagination.search.tcw == 2 || pagination.search.tcw == 3 }">
								<li>
									<a href="${pageContext.request.contextPath}/board-list?tcw=${pagination.search.tcw}&searchbox=${pagination.search.searchbox}&page=${i}">${i}</a>
								</li>
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">	
						<a href="${pageContext.request.contextPath}/board-list?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose>
		</ul>
	</div>
	
	<form action="/board-list" name="search" method="get">
		<select name="tcw">
			<option value="0"> 전체 </option>
			<option value="1"> 제목 </option>
			<option value="2"> 제목+내용 </option>
			<option value="3"> 작성자 </option>
		</select>
		<input type="text" name="searchbox" placeholder="입력하세요!">
		<input type="submit" value="검색하기">
	</form>

</body>
</html>