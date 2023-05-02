<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//再入力時はエラーメッセージが作られているが
	//最初の入力時にはエラーメッセージは存在せずnull
	//ブラウザにnullが表示されないように""（空文字)を代入
	String errMessage = (String)request.getAttribute("errMessage");
	if(errMessage == null){
		errMessage = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID番号で検索</title>
</head>
<body>
	<h2>ID番号を入力して下さい</h2>
	<form action="dataSearch" method="post">
		<p>
		ID:<input type="text" name="id"><br>
		<!-- パスワード：<input type="text" name="pass">-->
		</p>
		<input type="submit" value="送信">
	</form>
	<p></p>
	<p style="color:red"><%= errMessage %></p>
	<br>

	<p>
	<input type="button" value="新規データ挿入" onclick="location.href='data_insert.jsp'">　
	</p>

	<form action="allSearch" method="post">
		<input type="submit" value="全データ表示">
	</form>
</body>
</html>