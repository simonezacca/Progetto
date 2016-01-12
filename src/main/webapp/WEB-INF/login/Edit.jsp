<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="/topBar.jsp"></jsp:include>
<body>
	<form action="controller" method="post">
		<fieldset>
			<legend>Accesso utente</legend>
			<table>
				<tr>
					<td><label>Mail ${helper.errors.mail}</label></td>
					<td><input type="text" name="mail" value="${helper.data.mail}"></td>
				</tr>
				<tr>
					<td><label>Password ${helper.errors.password}</label></td>
					<td><input type="password" name="password" value="${helper.data.password}"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="confirmButton"
						value="Accedi"></td>
				</tr>
			</table>
		</fieldset>
	</form>
	<ul>
	<c:forEach var="errore" items="${messaggiErrore}">
		<li>${errore}</li>
	</c:forEach>
	</ul>
	</body>
</html>