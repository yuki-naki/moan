<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="header.css" rel="stylesheet" type="text/css" media="all">
<script src="js/jquery-1.12.4.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${loginRegisterServlet && (not empty registerForm.errors || not empty loginForm.errors)}">
			<div id="black_background" class="visible"></div>
		</c:when>
		<c:otherwise>
			<div id="black_background" class="invisible"></div>
		</c:otherwise>
	</c:choose>
	<div id="header">
		<c:choose>
			<c:when test="${empty sessionScope.userSession}">
				<ul>
					<li class="headerLeftLi"><span id="loginLink" class="link">Login</span></li>
					<li><span id="registerLink" class="link">Register</span></li>
				</ul>
			</c:when>
			<c:otherwise>
				<form id="logout" method="get" action="LogoutServlet">
					<ul>
						<li id="welcome" class="headerLeftLi"><span>Welcome ${sessionScope.userSession}</span></li>
						<c:set var="jsp" value="${pageContext.request.servletPath}" scope="session" />
						<li><button type="submit" id="logoutBtn">Logout</button></li>
					</ul>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>