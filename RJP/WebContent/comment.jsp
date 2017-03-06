<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="css/comment.css" rel="stylesheet" type="text/css" media="all">
<script src="js/jquery-1.12.4.js"></script>
<head>
<body onload="disableBackButton()">
	<c:import url="/header.jsp" />
	<c:import url="/loginRegister.jsp" />
<h1 id="titleH1"><a id="titleA" href="${pageContext.request.contextPath}/threads">${title}</a></h1>
<div id="main">
	<c:forEach var="comment" items="${comments}" varStatus="status" begin="${(page - 1) * commentsNb}" end="${(page * commentsNb) - 1}">
		<div id="${status.index == 0 ? 'firstComment' : ''}">
			<p class="commentInfo">
				[ <c:out value="${comment.commentId}" /> ]
				<span class="res_name">
				名前：<c:out value="${comment.commenter}" />
				</span>
				<span class="font">
				投稿日：<c:out value="${comment.createdDate}" />
				</span>
			</p>
			<form method="post" action="${pageContext.request.contextPath}/comments">
				<input type="hidden" name="commentId" value="${comment.commentId}" />
				<p class="content">
					<c:out value="${comment.content}" escapeXml="false" /><br>
					<c:if test="${status.last && comment.commenter eq sessionScope.userSession.name}">
						<c:set var="isLast" value="${true}"/>
						<c:set var="commentId" value="${comment.commentId}"></c:set>
						<c:if test="${! comment.isDeleted}">
							<button type="button" class="updateDeleteBtn" data-content="${comment.content}" id="updateCommentBtn">Update</button><br>
						</c:if>
						<c:if test="${not sessionScope.userSession.isAdmin  && ! comment.isDeleted}">
							<input class="updateDeleteBtn" type="submit" name="deleteComment" value="Delete" />
						</c:if>
					</c:if>
					<c:if test="${sessionScope.userSession.isAdmin && ! comment.isDeleted}">
						<input class="updateDeleteBtn" type="submit" name="deleteComment" value="Delete" />
					</c:if>
				</p>
			</form>
			<hr>
			</div>
	</c:forEach>
</div>

<div id="pageDiv" class="center">
	<form class="pageForm" action="${pageContext.request.contextPath}/comments" method="get">
		<input type="hidden" name="page" value="${page - 1}" />
		<input type="submit" ${page == 1 ? "disabled='disabled'" : ''} name="back" value="前のページ">
	</form>
	<span id="page">Page <c:out value="${page}" /></span>
	<form class="pageForm" action="${pageContext.request.contextPath}/comments" method="get">
		<input type="hidden" name="page" value="${page + 1}" />
		<input type="submit" ${fn:length(comments) <= (page * commentsNb) ? "disabled='disabled'" : ''} name="next" value="次のページ">
	</form>
</div>
<form method="post" action="${pageContext.request.contextPath}/comments">
<table id="border">
	<tr id="resLine">
		<td id="resLine"></td>
		<td id="comment">
		<span class="res_name">名前：${sessionScope.userSession.name}</span>
		</td>
	</tr>


	<tr id="resLine">
		<td id="resLine"></td>
		<td id="comment">
		<textarea id="textareaComment" cols="100%" rows=15 name="content" placeholder="ここに文書を入力してください"></textarea>
		<input type="hidden" name="threadId" value="${threadId}" />
		<input type="hidden" name="commentId" value="${commentId}" />
		</td>
	</tr>
	<tr id="newCommentRow">
		<td></td>
		<td id="newCommentCol">
			<div class="spancolor">
				<input id="newCommentBtn" ${isLast || empty sessionScope.userSession ? "disabled='disabled'" : ''} type="submit" value="書き込む" name="newComment">
				<span id="newCommentBtnError" class="${not empty sessionScope.userSession ? 'disabled' : ''}">新しいコメントを作成するにはログインする必要があります。</span>
			</div>
		</td>
	</tr>
</table>
</form>
<br>
<div class="center buttonpad">
	<a id="topPage" href="${pageContext.request.contextPath}/threads">トップページへ</a>
</div>
<img id="go_top" src="img/go_top.png" alt="トップへ戻る">
<script src="js/index.js"></script>
</body>
</html>