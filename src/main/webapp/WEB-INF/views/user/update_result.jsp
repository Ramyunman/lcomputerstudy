<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update 완료</title>
</head>
<body>
	<h1> User 정보 수정이 완료 되었습니다.</h1>
	<a href="${pageContext.request.contextPath}/user-detail/${user.uIdx}"> 돌아가기 </a>
<!--  	<a href="${pageContext.request.contextPath}/user-list}"> 돌아가기 </a>		-->
</body>
</html>