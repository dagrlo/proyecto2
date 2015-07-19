<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Entrar" method="post">
		<p />
		<label for="usernameField">Usuario:</label> 
			<input type="text" name="username" id="usernameField" required />
		<p />
		<label for="pwdField">Clave:</label> <input type="password" id="pwdField" name="password" required />
		<input type="submit" name="validar" value="Entrar" id="envia" class="estiloBotones"/>
	</form>
</body>
</html>