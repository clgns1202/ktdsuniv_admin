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
				if($("#roomNumber").val() == ""){
					alert("강의실을 입력해주세요");
				}else if($("#seatCount").val() == ""){
					alert("좌석수를 입력해주세요");
				}else{
					$.post("<c:url value='/room/checkDuplicateRoomNumber'/>",{"roomNumber" : $("#roomNumber").val()}, function(data){
									if(data==true){
					                    alert("강의실 이릉이 중복됩니다.");
					                }
									else{
										$.post("<c:url value='/room/doAddRoom'/>" , $( "#addForm" ).serialize(), function(data){
											 location.href= "<c:url value='/room/roomList'/>"; 
										});
									} 
					});
				}	
			});
	});
</script>
</head>
<body>
	<h3>강의실 등록</h3>
	<form method="post" id="addForm" name="addForm" >
		호실 : <input type="text" id="roomNumber" name="roomNumber">
		<span id="duplicateResult"></span>
        <br/>
		전체 좌석 수 : <input type="text" id="seatCount" name="seatCount"><br/>
		<input type="button" value="등록" id="addBtn" name="addBtn">
	</form>
</body>
</html>