<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<td>
					<c:choose>
						<c:when test="${isAdmin and comment.user.username == loginUser.username}">
							<!-- 1. 관리자이면서 작성자 -->
							<button type="button" class="btnComment">댓글</button>
							<button type="button" class="btnComment-Update">수정</button>
							<button type="button" class="btnComment-Delete" cIdx="${comment.cIdx }">삭제</button>
						</c:when>
						<c:when test="${isAdmin and comment.user.username != loginUser.username}">
							<!-- 2. 관리자이면서 작성자가 아님 -->
							<button type="button" class="btnComment">댓글</button>
							<button type="button" class="btnComment-Delete" cIdx="${comment.cIdx }">삭제</button>
						</c:when>
						<c:when test="${!isAdmin and comment.user.username == loginUser.username}">
							<!-- 3. 관리자가 아니면서 작성자 -->
							<button type="button" class="btnComment">댓글</button>
							<button type="button" class="btnComment-Update">수정</button>
							<button type="button" class="btnComment-Delete" cIdx="${comment.cIdx }">삭제</button>
						</c:when>
						<c:when test="${!isAdmin and comment.user.username != loginUser.username}">
							<!-- 4. 관리자가 아니면서 작성자도 아님 -->
							<button type="button" class="btnComment">댓글</button>
						</c:when>
					</c:choose>	
				</td>					
			</tr>
			
			<tr style="display: none;">			<!-- 대댓글 등록창 -->
				<td>
					<div>
						<textarea rows="3" cols="50" ></textarea>	
						<button type="button" class="btnComment-register" 
							cGroup="${comment.cGroup}" cOrder="${comment.cOrder}" cDepth="${comment.cDepth}">등록</button>
						<button type="button" class="btnComment-cancel">취소</button>
					</div>	
				</td>	
			</tr>
		
			<tr style="display: none;">		<!-- 대댓글 수정창 -->
				<td>
					<div>
						<textarea rows="3" cols="50"> ${comment.cContent } </textarea>
						<button type="button" class="btnComment-Update-register" cIdx="${comment.cIdx}">등록</button>
						<button type="button" class="btnComment-Update-cancel">취소</button>
					</div>	
				</td>
			</tr>	
		</c:forEach>