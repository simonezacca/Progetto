<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<table border="1">
	<tr>
		<td>Cognome</td><td>${tokenAutenticazione.utente.cognome}</td>
	</tr>
	<tr>
		<td>Nome</td><td>${tokenAutenticazione.utente.nome}</td>
	</tr>
	<tr>
		<td>Mail</td><td>${tokenAutenticazione.utente.mail}</td>
	</tr>
	<tr>
		<td>Password</td><td>${tokenAutenticazione.utente.password}</td>
	</tr>
	<tr>
		<td>Ruolo</td><td>${tokenAutenticazione.ruolo}</td>
	</tr>
</table>
<body>
</body>
</html>