<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board 수정 완료</title>
</head>
<body>
	<h1> Board 정보 수정이 완료 되었습니다.</h1>
	<p>	수정된 게시물 번호 : ${afterBoard.bIdx }</p>
	<a href="${pageContext.request.contextPath}/board-detail/${board.bIdx}"> 돌아가기 </a>
<!--  	<a href="${pageContext.request.contextPath}/user-list}"> 돌아가기 </a>		-->
</body>
</html>