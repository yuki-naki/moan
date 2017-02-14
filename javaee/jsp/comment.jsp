<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="header.css" rel="stylesheet" type="text/css" media="all">
<link href="loginRegister.css" rel="stylesheet" type="text/css" media="all">
<link href="comment.css" rel="stylesheet" type="text/css" media="all">
<script src="js/jquery-1.12.4.js"></script>
<head>
<body>
	<c:import url="/header.jsp" />
	<c:import url="/loginRegister.jsp" />
<h3 align="center"><a href="">スレッドの新規作成はこちらから</a></h3>

<p align="center"><font size="6" color ="#FF0088"><c:out value="${title}" /></font></p>
<p align="center"><a href="${pageContext.request.contextPath}/threads">トップページへ戻る</a></p>
<form method="POST" action="${pageContext.request.contextPath}/comments">
<table id="border" width="735" border="0" >
<tr>
<td>
<table width="100%" border="0" >
<tr id="resLine" >
<td id="resLine" width="100">名前</td>
<td id="comment" >
${sessionScope.userSession}
</td>
</tr>


<tr id="resLine">
<td id="resLine" width="100" >本文</td>

<td id="comment">
<textarea cols="60" rows=4 name="content" class="maintable"></textarea>
<input type="hidden" name="threadId" value="${threadId}" />
<INPUT id="newCommentBtn" class="${empty sessionScope.userSession ? 'disabled' : ''}" TYPE="submit" VALUE="書き込む" name="newComment">
<span id="newCommentBtnError">You have to be connected to create a new comment.</span>
</td>

</table>
</td>
</tr>
</table>
<br><br>
<hr>
</form>

<c:forEach var="comment" items="${comments}">
	<p>
		[ <c:out value="${comment.commentId}" /> ]<font color="#4040FF">名前：<c:out value="${comment.commenter}" /></font>
		<font size="2">投稿日：<c:out value="${comment.createdDate}" /></font>
	</p>
	<blockquote>
	  <p><c:out value="${comment.content}" /></p>
	</blockquote>
	<hr>
</c:forEach>



<table border="0" cellspacing="3" cellpadding="1" align="center"><tr>
<td><form action="http://bbs.sekkaku.net/bbs/A/" method="get" style="display: inline; margin: 0px; padding: 0px;" accept-charset="Shift_JIS">
<input type=hidden name=id value="A">
<input type=hidden name=page value="11">
<input type=submit value="次のページ" class="mainbutton">
</form></td>
<td><form action="http://bbs.sekkaku.net/bbs//A/" method="POST" style="display: inline; margin: 0px; padding: 0px;" accept-charset="Shift_JIS">
<input type=hidden name=id value="A">
<input type=submit value="最新のページ" class="mainbutton">
</form></td>
</tr>
</table>
<script src="js/index.js"></script>
</body>
</html>