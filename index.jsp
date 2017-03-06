<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="css/index.css" rel="stylesheet" type="text/css" media="all">
<script src="js/jquery-1.12.4.js"></script>
<head>
<body onload="disableBackButton()">
	<c:import url="/header.jsp" />
	<c:import url="/loginRegister.jsp" />

<p id="title">掲示板</p>

<form action="${pageContext.request.contextPath}/threads" method="get" id="searchForm" class="threadsCenter">
	<c:if test="${selectedSort == 'title'}">
		<input type="hidden" name="titleSortSearch" value="${titleAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'creator'}">
		<input type="hidden" name="creatorSortSearch" value="${creatorAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'reply'}">
		<input type="hidden" name="replySortSearch" value="${replyAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'createdDate'}">
		<input type="hidden" name="createdDateSortSearch" value="${createdDateAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'lastUser'}">
		<input type="hidden" name="lastUserSortSearch" value="${lastUserAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'lastUpdate'}">
		<input type="hidden" name="lastUpdateSortSearch" value="${lastUpdateAscSort}" />
	</c:if>
	<input type="text" name="searchInput" id="searchInput" placeholder="スレッドキーワード" value="${searchTxt}">
	<button id="searchBtn"></button>
	<c:if test="${fn:trim(searchTxt) != '' }">
		<div id="searchResult"><span id="searchResultNb"><c:out value="${fn:length(threads)}" /></span>件が見つかれました</div>
	</c:if>
</form>

<form action="${pageContext.request.contextPath}/threads" method="get" class="threadsCenter">
	<c:if test="${fn:trim(searchTxt) != '' }">
		<input type="hidden" name="searchInput" value="${searchTxt}" />
	</c:if>
	<table id="threadTable">
	<tr id="threadLine">
		<c:if test="${sessionScope.userSession.isAdmin}">
			<th class="center" width="20"></th>
		</c:if>
		<th class="center">
			<div id="titleDiv" class="sortDiv">
				<span class="sortSpan">スレッドタイトル</span> <button name="titleSort" id="titleSortBtn" value="${titleAscSort}" class="${titleAscSort ? 'sortBtn sortAsc' : 'sortBtn sortDesc'}"></button>
			</div>
		</th>
		<th class="center">
			<div id="creatorDiv" class="sortDiv">
				<span class="sortSpan">スレッド作成者名</span> <button name="creatorSort" id="creatorSortBtn" value="${creatorAscSort}" class="${creatorAscSort ? 'sortBtn sortAsc' : 'sortBtn sortDesc'}"></button>
			</div>
		</th>
		<th class="center">
			<div id="replyDiv" class="sortDiv">
				<span class="sortSpan">スレッド返信数</span> <button name="replySort" id="replySortBtn" value="${replyAscSort}" class="${replyAscSort ? 'sortBtn sortAsc' : 'sortBtn sortDesc'}"></button>
			</div>
		</th>
		<th class="center">
			<div id="createdDateDiv" class="sortDiv">
				<span class="sortSpan">作成日</span> <button name="createdDateSort" id="createdDateSortBtn" value="${createdDateAscSort}" class="${createdDateAscSort ? 'sortBtn sortAsc' : 'sortBtn sortDesc'}"></button>
			</div>
		</th>
		<th class="center">
			<div id="lastUserDiv" class="sortDiv">
				<span class="sortSpan">最終作成者</span> <button name="lastUserSort" id="lastUserSortBtn" value="${lastUserAscSort}" class="${lastUserAscSort ? 'sortBtn sortAsc' : 'sortBtn sortDesc'}"></button>
			</div>
		</th>
		<th class="center">
			<div id="lastUpdateDiv" class="sortDiv">
				<span class="sortSpan">最終更新日</span> <button name="lastUpdateSort" id="lastUpdateSortBtn" value="${lastUpdateAscSort}" class="${lastUpdateAscSort ? 'sortBtn sortAsc' : 'sortBtn sortDesc'}"></button>
			</div>
		</th>
	</tr>

	<c:forEach var="thread" items="${threads}" begin="${(page - 1) * threadsNb}" end="${(page * threadsNb) - 1}">
		<tr class="res_line">
			<c:if test="${sessionScope.userSession.isAdmin}">
				<td class="res_line"><input type="checkbox" name="deleteThreadCheckBox" value="${thread.threadId}"></td>
			</c:if>
			<td id="titleTd" class="res_line"><a href="${pageContext.request.contextPath}/comments?threadId=${thread.threadId}&title=${thread.title}"><c:out value="${thread.title}" /></a></td>
			<td class="res_line"><c:out value="${thread.creator}" /></td>
			<td class="res_line"><c:out value="${thread.replyNb}" /></td>
			<td class="res_line"><c:out value="${thread.createdDate}" /></td>
			<td class="res_line"><c:out value="${thread.lastUser}" /></td>
			<td class="res_line"><c:out value="${thread.lastUpdate}" /></td>
		</tr>
	</c:forEach>

	</table>
	<c:if test="${sessionScope.userSession.isAdmin}">
		<input type="submit" id="deleteThreadBtn" name="deleteThreadBtn" value="Delete Thread">
	</c:if>
</form>
<div class="threadsCenter">
	<button id="newThreadBtn" class="${empty sessionScope.userSession.name ? 'disabled' : ''}">New Thread</button>
	<span id="newThreadBtnError">新しいスレッドを作成するにはログインする必要があります。</span>
</div>

<div class="center">

<form class="pageForm" action="threads" method="get">
	<input type="hidden" name="page" value="${page - 1}" />
	<c:if test="${fn:trim(searchTxt) != '' }">
		<input type="hidden" name="searchInput" value="${searchTxt}" />
	</c:if>
	<c:if test="${selectedSort == 'title'}">
		<input type="hidden" name="titleSort" value="${titleAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'creator'}">
		<input type="hidden" name="creatorSort" value="${creatorAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'reply'}">
		<input type="hidden" name="replySort" value="${replyAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'createdDate'}">
		<input type="hidden" name="createdDateSort" value="${createdDateAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'lastUser'}">
		<input type="hidden" name="lastUserSort" value="${lastUserAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'lastUpdate'}">
		<input type="hidden" name="lastUpdateSort" value="${lastUpdateAscSort}" />
	</c:if>
	<input type="submit" ${page == 1 ? "disabled='disabled'" : ''} name="back" value="前のページ">
</form>
<span id="page">Page <c:out value="${page}" /></span>
<form class="pageForm" action="threads" method="get">
	<input type="hidden" name="page" value="${page + 1}" />
	<c:if test="${fn:trim(searchTxt) != '' }">
		<input type="hidden" name="searchInput" value="${searchTxt}" />
	</c:if>
	<c:if test="${selectedSort == 'title'}">
		<input type="hidden" name="titleSort" value="${titleAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'creator'}">
		<input type="hidden" name="creatorSort" value="${creatorAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'reply'}">
		<input type="hidden" name="replySort" value="${replyAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'createdDate'}">
		<input type="hidden" name="createdDateSort" value="${createdDateAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'lastUser'}">
		<input type="hidden" name="lastUserSort" value="${lastUserAscSort}" />
	</c:if>
	<c:if test="${selectedSort == 'lastUpdate'}">
		<input type="hidden" name="lastUpdateSort" value="${lastUpdateAscSort}" />
	</c:if>
	<input type="submit" ${fn:length(threads) <= (page * threadsNb) ? "disabled='disabled'" : ''} name="next" value="次のページ">
</form>

<form action="threads" method="post">
<table id="newThreadTable">

<tr id="threadLine">
<td class="center newThreadTd">名前</td>
<td id="comment" >
${sessionScope.userSession.name}
</td>
</tr>

<tr id="threadLine" >
<td class="center newThreadTd">タイトル</td>
<td id="comment" class="newThreadTdInput">
<input type="text" name="title" id="newThreadNameInput">
</td>
</tr>

<tr id="threadLine" class="newThreadTdTxtArea">
<td class="center newThreadTd">本文</td>

<td id="comment">
<textarea id="newThreadTxtArea" name="content" cols="60" rows="10"></textarea>
</td>
</tr>
</table>
<div id="submitNewThreadDiv">
<input id="submitNewThreadBtn" type="submit" name="newThread" value="Create">
</div>
</form>
</div>

<script src="js/index.js"></script>
</body>
</html>
