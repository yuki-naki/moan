<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="loginRegister.css" rel="stylesheet" type="text/css">
<head>
<body>
	<c:choose>
		<c:when test="${loginRegisterServlet && not empty registerForm.errors}">
			<div id="registerDiv" class="visible">
		</c:when>
		<%-- <c:when test="${loginRegisterServlet && registerForm.success}">
			<div id="registerDiv">
			<script type="text/javascript">formFadeOut()</script>
		</c:when>--%>
		<c:otherwise>
			<div id="registerDiv" class="invisible">
		</c:otherwise>
	</c:choose>

		<span class="close">X</span>
		<form class="loginForm" method="post" action="LoginRegisterServlet">
			<fieldset>
				<legend>登録</legend>
				<table>
					<tr>
						<td colspan="2"><span class="error">${registerForm.errors['nameRegister']}</span></td>
					</tr>
					<tr>
						<td><label for="nameRegister">ユーザー名</label></td>
						<td><input type="text" class="loginFormInput" id="nameRegister" name="nameRegister" value="<c:out value="${user.name}"/>" /></td>
					</tr>
					<tr>
						<td colspan="2"><span class="error">${registerForm.errors['passRegister']}</span></td>
					</tr>
					<tr>
						<td><label for="passRegister">パスワード</label></td>
						<td><input type="password" class="loginFormInput" id="passRegister" name="passRegister" /></td>
					</tr>
					<tr>
						<td colspan="2"><span class="error">${registerForm.errors['confirm']}</span></td>
					</tr>
					<tr>
						<td><label for="confirm">パスワード再入力</label></td>
						<td><input type="password" class="loginFormInput" id="confirm" name="confirm" /></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="hidden" name="servlet" value="${servlet}" />
							<input class="loginFormSubmit" type="submit" value="Register" name="register"/>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>


	<c:choose>
		<c:when test="${loginRegisterServlet && not empty loginForm.errors}">
			<div id="loginDiv" class="visible">
		</c:when>
		<c:otherwise>
			<div id="loginDiv" class="invisible">
		</c:otherwise>
	</c:choose>

		<span class="close">X</span>
		<form class="loginForm" method="post" action="LoginRegisterServlet">
			<fieldset>
				<legend>ログイン</legend>
				<table>
					<tr>
						<td colspan="2"><span class="error">${loginForm.errors['nameLogin']}</span></td>
					</tr>
					<tr>
						<td><label for="nameLogin">ユーザー名　</label></td>
						<td><input type="text" id="nameLogin" class="loginFormInput" name="nameLogin" /></td>
					</tr>
					<tr>
						 <td colspan="2"><span class="error">${loginForm.errors['passLogin']}</span></td>
					</tr>
					<tr>
						<td><label for="passLogin">パスワード　</label></td>
						<td><input type="password" id="passLogin" class="loginFormInput" name="passLogin" /></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<!--<input type="hidden" name="servlet" value="${pageContext.request.servletPath}" />-->
							 <input type="hidden" name="servlet" value="${servlet}" />
							<input class="loginFormSubmit" type="submit" value="Login" name="login"/>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>