<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="$!staticServer/static/js/lib/jquery/jquery-1.12.4.min.js"></script>
</head>
<body>
	<c:forEach var="user" items="${users }">
		<a href="user.userId">${user.userAccount }</a>
	</c:forEach>
</body>
</html>