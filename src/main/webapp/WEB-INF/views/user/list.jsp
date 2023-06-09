<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.springframework.security.core.authority.SimpleGrantedAuthority" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User 목록</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
		width:200px;
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
</style>
<body>
<h1> User 목록 </h1>
	<table>
		<tr>
			<td colspan="3">전체 회원 수: ${pagination.amount}</td>
		<tr>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<c:set var="adminRole" value="ROLE_ADMIN"/>
			<c:if test="${isAdmin}">
			<th>관리자</th>
			</c:if>
		</tr>
		<c:forEach items="${userList}" var="user" varStatus="status">
			<tr>
				<td><a href="${pageContext.request.contextPath}/user-detail/${user.uIdx}">${(user.currentPage - 1) * user.pageSize + status.index + 1}</a></td>
				<td>${user.username}</td>
				<td>${user.uName}</td>
				<c:set var="adminRole" value="ROLE_ADMIN"/>
				<c:if test="${isAdmin}">
					<td>
						<button type="button" class="adminOn">ON</button>
    					<button type="button" class="adminOff">OFF</button>
    				</td>
    			</c:if>
			</tr>
		</c:forEach>
	</table>
	&nbsp;
	<a href="${pageContext.request.contextPath}/"> 홈으로 </a>
	
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
						<a href="${pageContext.request.contextPath}/user-list?page=${pagination.prevPage}">
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
						<li>
							<a href="${pageContext.request.contextPath}/user-list?page=${i}">${i}</a>
						</li>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">	
						<a href="${pageContext.request.contextPath}/user-list?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose>
		</ul>
	</div>

<script>
$(document).on('click','.adminOn',function() {
	let Username = $(this).closest('tr').find('td:nth-child(2)').text();
	$.ajax({
		method : 'POST',
		url : '/user-addRoleAdmin',
		data : { username : Username },
		success : function(response) {
			console.log('adminOn성공');
		},
		error : function(xhr, status, error) {
			console.error(error);
		}
	});
});

$(document).on('click','.adminOff',function() {
	let Username = $(this).closest('tr').find('td:nth-child(2)').text();
	$.ajax({
		method : 'POST',
		url : '/user-removeRoleAdmin',
		data : { username : Username },
		success : function(response) {
			console.log('adminOff성공');
		},
		error : function(xhr, status, error) {
			console.error(error);
		}
	});
});


</script>
</body>
</html>