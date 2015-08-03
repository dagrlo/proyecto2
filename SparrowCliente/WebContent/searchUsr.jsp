<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Busca usuarios</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
	<form action="FindUser" method="post">
		<div class="window">
			<h2>Busqueda de usuarios</h2>
			<table>
				<tr><td>Apellidos del usuario:</td><td><input type="text" name="apellidos"/></td>
				<%
					if (request.getAttribute("error")!=null){
						String error=(String)request.getAttribute("error");
					
				%>
				<p><%=error %></p>
				<%} %>
				<tr><td colspan="2"><input type="submit" value="Buscar..."/></td></tr>
			</table>
			<br/>
			<a href="MainMenu">Volver</a>
		</div>		
	</form>
	
</body>
</html>