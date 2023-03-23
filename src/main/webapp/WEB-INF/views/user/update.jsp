<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User 정보 수정</title>
</head>
<body>
	<h1>User 정보 수정</h1>
	<form action="/user-update" method="post">
	<!-- csrf -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	   		<input type="hidden" name="uIdx" value="${user.uIdx}">    
			<p> ID : <input type="text" name="username" value = "${user.username }"></p>
			<p> 비밀번호 : <input type="text" name="password" value = ${user.password }></p>
			<p> 이름 : <input type="text" name="uName" value = ${user.uName }></p>
			<p> 전화번호 : <input type="text" maxlength="4" size="4" name="tel1" value="${user.uTelArr[0] }"> -
				   		<input type="text" maxlength="4" size="4" name="tel2" value="${user.uTelArr[1] }"> -
				   		<input type="text" maxlength="4" size="4" name="tel3" value="${user.uTelArr[2] }">
			</p>
			<p> 나이 : <input type="text" name="uAge" value = ${user.uAge }></p> 
			<p> <input type="submit" value="수정하기"></p>
			<a href="${pageContext.request.contextPath}/user-list"> 목록으로 </a>
	</form>

</body>
</html>