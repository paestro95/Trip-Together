<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${cnt>0}">
	<script>
		alert("회원이 등록되었습니다");
		location.href="/";
	</script>
</c:if>
<!-- 등록 실패시 -->
<c:if test="${cnt == null || cnt == 0}">
	<script>
		alert("회원가입에 실패하였습니다");
		history.go(-1);
	</script>
</c:if>
</body>
</html>