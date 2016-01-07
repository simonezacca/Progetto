<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="registrazione" method="post">
		<fieldset>
			<legend>Registrazione nuovo utente</legend>
			<table>
				<tr>
					<td><label>Cognome</label></td>
					<td><input type="text" name="cognome" value="${param.cognome}"></td>
				</tr>
				<tr>
					<td><label>Nome</label></td>
					<td><input type="text" name="nome" value="${param.nome}"></td>
				</tr>
				<tr>
					<td><label>Mail</label></td>
					<td><input type="text" name="mail" value="${param.mail}"></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><input type="text" name="password"></td>
				</tr>
				<tr>
					<td>Ruolo</td>
					<td><input type="radio" name="ruolo" value="0" checked>Locatario<input
						type="radio" name="ruolo" value="1">Gestore</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Registra" name="registraButton"></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>