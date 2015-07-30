<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienenido a Sparrow</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">
</head>
<body>
	<div id="menuIndex">
		<img src="./gfx/logoGrande.png" />
		<h1>Sparrow</h1>
		<div id="cajaEntrada">
		<form action="Login" method="post">
			<table align="center">
				<tr>
					<td><b>Nombre:</b></td>
					<td><input type="text" name="usr" required /></td>
				</tr>
				<tr>
					<td><b>Clave:</b></td>
					<td><input type="password" name="pass" required /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Entrar..." /></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2"><a href="RegistraUsuario">Crear cuenta</a></td>
				</tr>
				<tr>
					<td colspan="2"><a href="RecuperaPassword">Recuperar password</a></td>
				</tr>
			</table>
			</form>
		</div>
	</div>	
</body>
</html>