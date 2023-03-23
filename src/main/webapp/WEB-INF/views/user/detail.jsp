<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> User 정보 읽기 </title>
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
	<h1>User 상세 정보</h1>
	
	<table>
		<tr>
			<td>회원 번호</td>
			<td>${user.uIdx }</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>${user.username }</td>
		</tr>
		<tr>
			<td>회원 이름</td>
			<td>${user.uName }</td>
		</tr>
		<tr>
			<td>회원 전화번호</td>
			<td>${user.uTel }</td>
		</tr>
		<tr>
			<td>회원 나이</td>
			<td>${user.uAge }</td>
		</tr>
		<tr style="height:50px;">
			<td style="border:none;">
				<a href="${pageContext.request.contextPath}/before-user-update/${user.uIdx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;">수정</a>
			</td>
			<td style="border:none;">
				<a href="${pageContext.request.contextPath}/user-delete/${user.uIdx}" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>
			</td>
		</tr>
		
	</table>
	<a href="${pageContext.request.contextPath}/user-list"> 목록으로 </a>
	
</body>
</html>