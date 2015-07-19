<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*,uv.es.bd.sparrow.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Usuarios encontrados</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>


	<div class="window2">
		<h2>Usuarios encontrados</h2>
		<div class="listaFollows">
			<%
			String nombre,apellidos,pass,usrName,email;
			int id;
			
			@SuppressWarnings("unchecked")
			List<User>usuarios=(List<User>)request.getAttribute("lista");
			
		
			
			for (User usuario:usuarios){
				
					nombre=usuario.getNombre();
					apellidos=usuario.getApellidos();
					id=usuario.getId();	
					usrName=usuario.getUsername();
				
				
		%>
		<div class="usuarioLista">
				<%=nombre%> <%=apellidos %> <b>[<%=usrName %>]</b><a class="negro" href="SeguirUsuario?id=<%=id%>"><img width="20%" src="gfx/botonMas.png" /></a>
		</div>
			
		
		<%
			}
		%>
		</div>
		<br/>
	<a href="MainMenu">Volver</a>	
	</div>

	
	
</body>
</html>