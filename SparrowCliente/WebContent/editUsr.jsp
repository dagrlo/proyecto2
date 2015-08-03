<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ page import="uv.es.sparrow.entities.Users"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Preferencias</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
<%
		Users datos = (Users) request.getAttribute("datosUsr");

		String[] sexos={"M","V"};
		String[][] idiomas={{"de","en","es"},{"German","English","Spanish"}};
	%>
	<form action="EditUser" method="post">
	<div class="window">
		<h2>Preferencias</h2>
		<table width="96%">
			<tr><td>Nombre de usuario:</td><td><%=datos.getUsername() %></td></tr>
			<input type="hidden" name="username" value="<%=datos.getUsername() %>"/> 
			<tr><td>Password:</td><td><input type="text" name="password" value="<%=datos.getPasswordString() %>"/></td></tr>
			<tr><td>Email:</td><td><%=datos.getEMail() %></td></tr>
			<input type="hidden" name="email" value="<%=datos.getEMail() %>"/>
			<tr><td>Nombre:</td><td><input type="text" name="nombre" value="<%=datos.getNombre() %>"/></td></tr>
			<tr><td>Apellidos:</td><td><input type="text" name="apellidos" value="<%=datos.getApellidos() %>"/></td></tr>
			<tr><td>Sexo:</td>
				<td>
					<select name="sexo">
						<% for (int i=0;i<sexos.length;i++){%>
							<option value="<%=sexos[i] %>"
							<%if (datos.getSexo().equals(sexos[i])){ %>
							selected
							<%} %>
						><%=sexos[i] %></option>
						<%} %>	
					</select>
					</td></tr>
			<tr><td>Idioma:</td>
				<td>
				<select name="idioma">
				<%for (int i=0;i<3;i++){ %>
				<option value="<%=idiomas[0][i] %>"
					<%if (datos.getIdioma().equals(idiomas[0][i])){ %>
							selected
							<%} %>
						><%=idiomas[1][i] %></option>
					<%} %>
				</select>
				</td>
			</tr>
			<tr><td colspan="2"><input type="submit" value="Actualizar"/></td></tr>
		</table>
		<br/>
		<a href="MainMenu">Volver</a>
	</div>

	
	
	</form>
</body>
</html>