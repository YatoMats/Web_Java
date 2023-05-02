<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//セッションからアトリビュートデータの取得
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データ変更完了</title>
</head>
<body>
	<h2>ID:<%= id %> のデータ変更完了</h2>
	<table border="1">
	<tr><th>ID</th><td><%= id %></td></tr>
	<tr><th>名前</th><td><%= name %></td></tr>
	<tr><th>残高</th><td><%= session.getAttribute("balance") %></td></tr>
	</table>
	<br>
	<input type="button" value="データ検索画面に戻る" onclick="location.href='data_search.jsp'">
</body>
</html>