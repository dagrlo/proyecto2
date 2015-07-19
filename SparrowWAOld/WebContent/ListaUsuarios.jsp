<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*,uv.es.bd.sparrow.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr><th>id</th><th>Nombre</th><th>Apellidos</th><th>Pass</th><th>Usuario</th><th>Email</th></tr>
		<%
			String nombre,apellidos,pass,usrName,email;
			int id;
			
			@SuppressWarnings("unchecked")
			List<User>usuarios=(List<User>)request.getAttribute("usuarios");
			
			for (User usuario:usuarios){
				nombre=usuario.getNombre();
				apellidos=usuario.getApellidos();
				id=usuario.getId();
				pass=usuario.getPassword();				
				usrName=usuario.getUsername();
				email=usuario.getEMail();
		%>
		<tr>
			<td><%= id %></td>
			<td><%= nombre %></td>
			<td><%= apellidos %></td>
			<td><%= pass %></td>
			<td><%= usrName %></td>
			<td><%= email %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>