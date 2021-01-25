<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!--<form action ="${pageContext.request.contextPath }/guest/insert" method="post"> -->
	<!-- map이용 -->
	<form action ="${pageContext.request.contextPath }/guest/insert2" method="post">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<!-- 리스트 출력 -->
	<!-- jstl 반복문 -->
	<c:forEach items="${requestScope.guestList}" var="vo">
		<table border="1">
			<tr>
				<td>${vo.no}</td>
				<td>${vo.name}</td>
				<td>${vo.regDate}</td>
				<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no=${vo.no}">삭제</a></td>

			</tr>
			<tr>
				<td colspan="4">${vo.content}</td>
			</tr>
		</table> 
	<br>
	</c:forEach>
	
</body>
</html>