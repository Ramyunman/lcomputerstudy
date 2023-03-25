<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board 정보 수정</title>
</head>
<body>
	<h1>Board 정보 수정</h1>
	<form action="/user-update" method="post">
	<!-- csrf -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	   		<input type="hidden" name="bIdx" value="${board.bIdx}">    
			<p> 제목 : <input type="text" name="bTitle" value = "${board.bTitle }"></p>
			<p> 내용 : <textarea name="bContent" rows="10" cols="50">${board.bContent}</textarea></p>
						
			<p> <input type="submit" value="수정하기"></p>
			<a href="${pageContext.request.contextPath}/board-list"> board 목록 </a>
	</form>

</body>
</html>