<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,uv.es.bd.sparrow.entity.User" %>
<%@ page import="es.uv.bd.sparrow.users.UsersBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Preferencias</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
<%
		UsersBean datos=(UsersBean)session.getAttribute("usuario");
		String[] sexos={"M","V"};
		String[][] idiomas={{"de","en","es"},{"German","English","Spanish"}};
	%>
	<form action="EditaDatos" method="post">
	<div class="window">
		<h2>Preferencias</h2>
		<table width="96%">
			<tr><td>Nombre de usuario:</td><td><%=datos.getUser().getUsername() %></td></tr> 
			<tr><td>Password:</td><td><input type="text" name="password" value="<%=datos.getUser().getPassword() %>"/></td></tr>
			<tr><td>Email:</td><td><%=datos.getUser().getEMail() %></td></tr>
			<tr><td>Nombre:</td><td><input type="text" name="nombre" value="<%=datos.getUser().getNombre() %>"/></td></tr>
			<tr><td>Apellidos:</td><td><input type="text" name="apellidos" value="<%=datos.getUser().getApellidos() %>"/></td></tr>
			<tr><td>Sexo:</td>
				<td>
					<select name="sexo">
						<% for (int i=0;i<sexos.length;i++){%>
							<option value="<%=sexos[i] %>"
							<%if (datos.getUser().getSexo().equals(sexos[i])){ %>
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
					<%if (datos.getUser().getIdioma().equals(idiomas[0][i])){ %>
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