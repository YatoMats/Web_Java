<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//データ取得
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
<title>データ挿入</title>
</head>
<body>
	<h2>挿入するデータを入力してください。</h2>
	<form action="dataInsert" method="post">
		<p>
		　ID：<input type="text" name="id"><br>
		名前：<input type="text" name="name"><br>
		残高：<input type="text" name="balance"><br>
		</p>
		<input type="submit" value="挿入">
	</form>
	<br>
	<p style="color:red;"><%= errMessage %></p>
	<input type="button" value="データ検索画面へ戻る" onclick="location.href='data_search.jsp'">
</body>
</html>