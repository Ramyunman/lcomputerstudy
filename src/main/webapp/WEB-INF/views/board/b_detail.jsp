<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Board 상세 정보 </title>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	}
</style>
<body>
	<h1>Board 상세 정보</h1>
	
	<table>
		<tr>
			<td>게시글 번호</td>
			<td>${board.bIdx }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${board.bTitle }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.bContent }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.bViews }</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>${board.user.username }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.user.uName }</td>
		</tr>
		<tr>
			<td>작성 날짜</td>
			<td>${board.bDateTime }</td>
		</tr>
		<tr style="height:50px;">
			<td style="border:none;">
				<a href="${pageContext.request.contextPath}/before-board-update/${board.bIdx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;">수정</a>
			</td>
			<td style="border:none;">
				<a href="${pageContext.request.contextPath}/board-delete/${board.bIdx}" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>
			</td>
		</tr>
		
	</table>
	<a href="${pageContext.request.contextPath}/board-list">목록으로</a>
	<a href="${pageContext.request.contextPath}/board-reply-insert/${board.bGroup}&${board.bOrder}&${board.bDepth}">답글 등록</a>
	
</body>
</html>