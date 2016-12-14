<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#addBtn").click(function(){
			location.href="<c:url value="/room/addRoom"/>";
		});
	});
</script>
</head>
<body>
	<h3>강의실 목록</h3>
	<c:forEach items="${roomList.pageList}" var="room">
		<div>${room.roomNumber} 
		<a href="<c:url value="/room/delete/${room.id}"/>" style="font-size: 12px">삭제</a></div>	
	</c:forEach>
	<br/>
	<input type="button" value="강의실 추가" id="addBtn" name="addBtn">
</body>
</html>