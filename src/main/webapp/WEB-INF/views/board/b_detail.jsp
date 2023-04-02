<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Board 상세 정보 </title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	<a href="${pageContext.request.contextPath}/board-replyBeforeSignUp/${board.bIdx}">답글 등록</a>
	<button type="button" class="o_btnComment"> 댓글 등록 </button>
	
		<div style="display: none;">
			<textarea rows="3" cols="50" ></textarea>	
			<button type="button" class="o_btnComment-register">등록</button>
			<button type="button" class="o_btnComment-cancel">취소</button>
		</div>
	
	<h2> Comment 목록 </h2>
	<table id="commentList">
		<tr>
			<th>No</th>
			<th>내용</th>
			<th>작성일자</th>
			<th>ID</th>			
		</tr>
		
		<c:forEach items="${commentList}" var="comment" varStatus="status">
			<tr>
				<td>${comment.cIdx }</td>
				
				<c:choose>
					<c:when test="${comment.cDepth > 0 }">
						<td style="text-align: left;">
							<c:forEach var="i" begin="1" end="${comment.cDepth}" step="1">
							&nbsp;&nbsp;
							</c:forEach>
							ㄴ${comment.cContent}
						</td>
					</c:when>
					<c:when test = "${comment.cDepth == 0 }">
						<td style="text-align: left; padding-left: 10px;">${comment.cContent}</td>
					</c:when>
				</c:choose>
				
				<td>${comment.cDateTime}</td>
				<td>${comment.user.username}</td>				
			</tr>
		</c:forEach>
	</table>
	
<script>
$(document).on('click', '.o_btnComment', function () {				//원댓글 달기 버튼		
	console.log('원댓글 달기 버튼')
	$(this).next().css('display','');
});

$(document).on('click', '.o_btnComment-register', function () {		//원댓글 등록 버튼		
	let B_Idx = '${board.bIdx}';
	let C_Content = $(this).prev('textarea').val();
	
	$.ajax({
		method : 'POST',
		url : "/comment-signup",
		data : { bIdx:B_Idx, cContent:C_Content }
	})
	.done(function( msg ) {
		console.log(msg);
	   	$('#commentList').html(msg);
	});
	console.log('원댓글 등록 버튼')
	$(this).parent().css('display','none');
});

$(document).on('click', '.o_btnComment-cancel', function () {		//원댓글 취소 버튼		
	console.log('원댓글 취소 버튼')
	$(this).parent().css('display','none');
});	
</script>
	
</body>
</html>