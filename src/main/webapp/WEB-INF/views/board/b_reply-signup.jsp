<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 등록하기</title>
</head>
<body>
	<h1>답글 등록</h1>
		<form action="/board-replySignup" method="post">
		<!-- csrf -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="uIdx" value="${sessionScope.user.uIdx }">
			<input type="hidden" name="bIdx" value="${board.bIdx}">			
			<p> 제목 : <input type = "text" name="bTitle" placeholder="제목 입력"> </p>
			<p> 내용 : </p>
			<textarea name="bContent" rows="10" cols="50" placeholder="내용 입력"></textarea>  
		  	<input type="hidden" name="bGroup" value="${board.bGroup}">
			<input type="hidden" name="bOrder" value="${board.bOrder}">
			<input type="hidden" name="bDepth" value="${board.bDepth}">
			
			<p> <button type="submit">등록하기</button> </p>
		</form>
</body>
</html>