<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Recupera Password</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">
</head>
<body>
	<form action="RecuperaPassword" method="post">
		<div class="window">
			<h2>Recuperar password</h2> 
			<table>
				<tr><td>Usuario:</td><td><input type="text" name="nombre" id="nombreField" required /></td></tr>
				<tr><td>Email:</td><td><input type="email" name="email" id="mailField" required /> </td></tr>
				<tr><td colspan="2"><input type="submit" name="validar" value="Entrar" id="envia" /></td></tr>
			</table>										
		</div>
	</form>
</body>
</html>