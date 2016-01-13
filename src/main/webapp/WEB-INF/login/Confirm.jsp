<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="/topBar.jsp"></jsp:include>
<body>
	Utente autenticato correttamente
	<table border="1">
		<tr>
			<td>Username</td>
			<td>${helper.data.mail}</td>
		</tr>
		<tr>
			<td>Password</td>
			<td>${helper.data.password}</td>
		</tr>
		<tr>
			<td>Ruolo</td>
			<td>${helper.data.ruolo}</td>
		</tr>
	</table>
	<form method="post" action="controller">
			<input type="submit" name="logoutButton" value="Esci">
	</form>
</body>
</html>