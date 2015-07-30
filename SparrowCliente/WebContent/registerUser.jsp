<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Registro</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
	<form action="RegistraUsuario" method="post">
		<div class="window">
			<h2>Registrarse como nuevo usuario</h2>
			<table>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" id="nombreField"
						maxlength="30" required /></td>
				</tr>
				<tr>
					<td>Apellidos:</td>
					<td><input type="text" name="apellidos" id="apellidosField"
						maxlength="60" required /></td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><select name="sexo" id="sexoField">
							<option value="M">M</option>
							<option value="V">V</option>
					</select></td>
				</tr>
				<tr>
					<td>Idioma:</td>
					<td><select name="idioma" id="idiomaField">
							<option value="de">German</option>
							<option value="en">English</option>
							<option value="es">Spanish</option>
					</select></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="email"	name="email" id="emailField" maxlength="60" required /></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" id="usernameField" maxlength="20" required /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="passField" maxlength="20" required /></td>
				</tr>
				<tr>
					<td>Repite password:</td>
					<td>
						<input type="password" name="password2" id="passField2" onkeyup="compara()" maxlength="20" required />
						<div id="estado"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="validar" value="Entrar" id="envia" /></td>
				</tr>
			</table>	
		</div>
	</form>
	<script>
		function compara(){
			var p1=document.getElementById("passField").value;
			var p2=document.getElementById("passField2").value;
			var envia=document.getElementById("envia");
			var estado=document.getElementById("estado");
			
			if (p1!=p2){
					estado.innerHTML="No coincide";
					estado.style.color="#fff";
					estado.style.backgroundColor="#a00";
					envia.disabled=true;
			} else {
				estado.innerHTML="OK";
				estado.style.color="#fff";
				estado.style.backgroundColor="#0a0";
				envia.disabled=false;
			}
		}
	</script>
</body>
</html>