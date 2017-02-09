<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link href="thread.css" rel="stylesheet" type="text/css" media="all">
<head>
<body>

<p id="title" >掲示板</p>
<p id="subtitle" >何か記念に書いてください！</p>

<table id="border" width="735" border="0" >
<tr>
<td>
<table width="100%" border="0" >
<tr id="threadLine" >
<td id="threadLine" width="100">名前</td>
<th id="comment" >
<input type="text" name="name" size="60">
</th>
</tr>

<tr id="threadLine" >
<td id="threadLine" width="100">タイトル</td>
<td id="comment" >
<input type="text" name="sub" size="60">
</td>
</tr>

<tr id="threadLine">
<td id="threadLine" width="100">本文</td>

<td id="comment">
<textarea name="mes" cols="60" rows="10"></textarea>
</td>
</tr>
<!--
<tr id="threadLine">
<td id="threadLine" width="100">アップロード</td>
<td id="comment">
<input type="file" name="file" size="60"><br>
<input type="file" name="op11" size="60">
</td>
</tr>-->
</table>
</td>
</tr>
</table>
<br><br>

<!--仮-->
<table id="border" width="730" border="1" >

<td>
<table width="100%" border="0" >
<tr id="threadLine">
<th id="res_titly" nowrap width="100">スレッドID</td>
<th id="res_titly" nowrap width="350">スレッドタイトル</td>
<th id="res_titly" nowrap width="80">スレッド作成者名</th>
<th id="res_titly" nowrap width="70">スレッド返信数</th>
<th id="res_titly" nowrap width="150">作成日</th>
<th id="res_titly" nowrap width="80">最終作成者</th>
<th id="res_titly" nowrap width="60">最終更新日</th>
<th id="res_titly" nowrap width="60" >favorite</th>
</tr>

<c:forEach var="thread" items="${threads}">
	<tr id="res_line">
		<td id="res_line" width="20"><c:out value="${thread.threadId}" /></td>
		<td id="res_line"><c:out value="${thread.title}" /></td>
		<td id="res_line"><c:out value="${thread.creator}" /></td>
		<td id="res_line"><c:out value="${thread.replyNb}" /></td>
		<td id="res_line"><c:out value="${thread.createdDate}" /></td>
		<td id="res_line"><c:out value="${thread.lastUser}" /></td>
		<td id="res_line"><c:out value="${thread.lastUpdate}" /></td>
		<td id="res_line">favorite</td>
	</tr>
</c:forEach>

</table>
</td>
</tr>
</table>
<div class="center">
<input type="submit" name="next" value="次のページ">
<input type="submit" name="back" value="前のページ">
</div>
</body>
</html>
