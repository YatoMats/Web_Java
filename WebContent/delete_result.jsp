<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//データの取得
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データ削除完了</title>
</head>
<body>
	<h2>ID:<%= id %> のデータ削除完了</h2>
	<br>
	<input type="button" value="データ検索画面に戻る" onclick="location.href='data_search.jsp'">
</body>
</html>