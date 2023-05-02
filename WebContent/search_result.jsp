<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//パラメータ取得
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	int balance = (Integer)session.getAttribute("balance");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>
	<h2>検索結果</h2>
	<table border="1">
	<tr><th>ID</th><td><%= id %></td></tr>
	<tr><th>名前</th><td><%= name %></td></tr>
	<tr><th>残高</th><td><%= balance %></td></tr>
	</table>
	<p>
	<input type="button" value="変更" onclick="location.href='data_change.jsp'">　
	<input type="button" value="削除" onclick="location.href='data_delete.jsp'">　
	</p>
	<br>
	<p>
	<input type="button" value="データ検索画面に戻る" onclick="location.href='data_search.jsp'">
	</p>
</body>
</html>