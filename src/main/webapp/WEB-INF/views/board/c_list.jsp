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
			</tr>
		</c:forEach>