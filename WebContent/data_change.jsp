<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	//int balance = (Integer)session.getAttribute("balance");

	String errMessage = (String)request.getAttribute("errMessage");
	if(errMessage == null)
	{
		errMessage = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データ変更</title>
</head>
<body>
	<h2>データ変更</h2>
	<form action="dataChange" method="post">
		<p>ID:<%= id %><br>
		名前：<input type="text" name="name" value="<%= name %>"><br>
		残高：<input type="text" name="balance" value="<%= session.getAttribute("balance") %>">
		</p>
		<input type="submit" value="変更">
	</form>
	<br>
	<p style="color:red;"><%= errMessage %></p>
	<input type="button" value="データ検索結果画面へ戻る" onclick="location.href='search_result.jsp'">
</body>
</html>