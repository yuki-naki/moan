<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="index.css" rel="stylesheet" type="text/css" media="all">
<script src="js/jquery-1.12.4.js"></script>
<head>
<body>
	<c:import url="/header.jsp" />
	<c:import url="/loginRegister.jsp" />

<p id="title" >掲示板</p>

<table id="threadTable"  border="0" >
<tr id="threadLine">
<th id="title_check">check</td>
<th id="title_title">スレッドタイトル</td>
<th id="title_creator">スレッド作成者名</th>
<th id="title_res">スレッド返信数</th>
<th id="title_creator_date">作成日</th>
<th id="title_last_creator">最終作成者</th>
<th id="title_last_update">最終更新日</th>
<th id="title_favorite">favorite</th>
</tr>

<c:forEach var="thread" items="${threads}" begin="0" end="${threadsNb - 1}">
	<tr id="res_line">
		<td id="res_line"><c:out value="${thread.threadId}" /></td>
		<td id="res_line"><a href="${pageContext.request.contextPath}/comments?threadId=${thread.threadId}&title=${thread.title}"><c:out value="${thread.title}" /></a></td>
		<td id="res_line"><c:out value="${thread.creator}" /></td>
		<td id="res_line"><c:out value="${thread.replyNb}" /></td>
		<td id="res_line"><c:out value="${thread.createdDate}" /></td>
		<td id="res_line"><c:out value="${thread.lastUser}" /></td>
		<td id="res_line"><c:out value="${thread.lastUpdate}" /></td>
		<td id="res_line">favorite</td>
	</tr>
</c:forEach>

</table>
<div>
	<button id="newThreadBtn" class="${empty sessionScope.userSession.name ? 'disabled' : ''}">New Thread</button>
	<span id="newThreadBtnError">新しいスレッドを作成するために接続する必要があります</span>
	<c:if test="${sessionScope.userSession.isAdmin}">
		<button id="deleteThreadBtn">Delete Thread</button>
	</c:if>
</div>
<div class="center">

<form action="threads" method="get">
	<input type="hidden" name="page" value="${page - 1}" />
	<input type="submit" name="back" value="前のページ">
</form>
Page <c:out value="${page}" />
<form action="threads" method="get">
	<input type="hidden" name="page" value="${page + 1}" />
	<input type="submit" name="next" value="次のページ">
</form>

<form action="threads" method="post">
<table id="newThreadTable"border="0" >

<tr id="threadLine">
<td>名前</td>
<td id="comment" >
${sessionScope.userSession.name}
</td>
</tr>

<tr id="threadLine" >
<td>タイトル</td>
<td id="comment" >
<input type="text" name="title" size="82">
</td>
</tr>

<tr id="threadLine">
<td>本文</td>

<td id="comment">
<textarea name="content" cols="60" rows="10"></textarea>
</td>
</tr>
<tr id="threadLine">
<td colspan="2"><input type="submit" name="newThread" value="Create"></td>
</tr>


</table>
</form>
</div>


<script src="js/index.js"></script>
</body>
</html>
