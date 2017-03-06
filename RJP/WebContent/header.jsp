<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link href="css/header.css" rel="stylesheet" type="text/css" media="all">
</head>
<body>
	<c:choose>
		<c:when test="${not empty registerForm.errors || not empty loginForm.errors}">
			<div id="black_background" class="visible"></div>
		</c:when>
		<c:otherwise>
			<div id="black_background" class="invisible"></div>
		</c:otherwise>
	</c:choose>
	<div id="header">
		<c:choose>
			<c:when test="${empty sessionScope.userSession.name}">
				<ul>
					<li class="headerLeftLi"><span id="loginLink" class="link">Login</span></li>
					<li><span id="registerLink" class="link">Register</span></li>
				</ul>
			</c:when>
			<c:otherwise>
				<form id="logout" method="get" action="LogoutServlet">
					<ul>
						<li id="welcome" class="headerLeftLi marginLeftLi"><span>Welcome ${sessionScope.userSession.name}</span></li>
						<li class="marginLeftLi">
							<input type="hidden" name="servlet" value="${servlet}" />
							<button type="submit" id="logoutBtn">Logout</button>
						</li>
					</ul>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>