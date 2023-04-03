<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<tr>
			<th>No</th>
			<th>내용</th>
			<th>작성일자</th>
			<th>ID</th>	
			<th>cGroup</th>		
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
				<td>${comment.cGroup}</td>
				<td>
					<button type="button" class="btnComment">댓글</button>
					<button type="button" class="btnComment-Update">수정</button>
					<button type="button" class="btnComment-Delete">삭제</button>	
				</td>				
			</tr>
			
			<tr style="display: none;">			<!-- 대댓글 등록창 -->
				<td>
					<div>
						<textarea rows="3" cols="50" ></textarea>	
						<button type="button" class="btnComment-register" data-cGroup="${comment.cGroup}" data-cOrder="${comment.cOrder}" data-cDepth="${comment.cDepth}">등록</button>
						<button type="button" class="btnComment-cancel">취소</button>
					</div>	
				</td>	
			</tr>
		
		</c:forEach>