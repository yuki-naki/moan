<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="comment.css" rel="stylesheet" type="text/css" media="all">

<head>
<body>
<h1 id="title">コメント</h1>
<hr>

<c:forEach var="comment" items="${comments}">
	<p>
		[ <c:out value="${comment.commentId}" /> ]
		<div class="res_name">
		名前：<c:out value="${comment.commenter}" />
		</div>
		<div class="font">
		投稿日：<c:out value="${comment.createdDate}" />
		</div>
	</p>
	<blockquote>
	  <p><c:out value="${comment.content}" /></p>
	</blockquote>
	<hr>
</c:forEach>

	<div class="center buttonpad">
		<input type=submit value="次のページ" class="mainbutton">
		<input type=submit value="最新のページ" class="mainbutton">
	</div>
	
<table id="border">
	<tr id="resLine">
		<td id="resLine">名前</td>
		<td id="comment">
		${sessionScope.userSession}
		</td>
	</tr>


	<tr id="resLine">
		<td id="resLine">本文</td>
		<td id="comment">
		<textarea cols="100%" rows=15 name="content" ></textarea>
		<input type="hidden" name="threadId" value="${threadId}" />
		</td>
	</tr>	
</table>
		<div class="spancolor buttonposition">
		
		<INPUT id="newCommentBtn" class="${empty sessionScope.userSession ? 'disabled' : ''}" TYPE="submit" VALUE="書き込む" name="newComment">
		
		<span  id="newCommentBtnError">新しいスレッドを作成するには接続する必要があります。</span>
		</div>
		
		<br>
		
		<div class="center buttonpad">
		
		<input type="submit" value="トップページへ">
		
		</div>

</body>
</html>
