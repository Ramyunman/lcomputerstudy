<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board 등록</h1>
		<hr>
		
		<form action="/b_signup" method="post">
		<!-- csrf -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<p> 제목 : <input type = "text" name="bTitle" placeholder="제목 입력"> </p>
			<p> 내용 : </p>
			<textarea name="bContent" rows="10" cols="50" placeholder="내용 입력"></textarea>  
	<!--	<p> 파일 : <input type = "file" name="b_fileName" placeholder=" 파일이름 입력"> </p>	-->
		  
			  <button type="submit">가입하기</button>
		</form>
</body>
</html>