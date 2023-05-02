<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	int balance = (Integer)session.getAttribute("balance");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認</title>
</head>
<body>
	<h2>次の情報を削除してもよろしいですか？</h2>
	<form action="dataDelete" method="post">
	<p>ID：<%= id %><br>
	名前：<%= name %><br>
	残高：<%= balance %><br>
	<input type="submit" value="削除">
	</form>
	<br>
	<input type="button" value="検索結果画面へ戻る" onclick="location.href='search_result.jsp'">
</body>
</html>