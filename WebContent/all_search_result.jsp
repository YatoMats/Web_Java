<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//セッションからアトリビュートデータを取得
	String trs = (String)session.getAttribute("trs");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全データ検索結果</title>
</head>
<body>
	<h2>全データ検索結果</h2>
	<table border="1">
	<tr><th>ID</th><th>名前</th><th>残高</th></tr>
	<%= trs %>
	</table>
	<br>
	<input type="button" value="データ検索画面に戻る" onclick="location.href='data_search.jsp'">
</body>
</html>