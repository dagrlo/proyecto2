<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Respondiendo</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
	<div class="window2">
	<%
		@SuppressWarnings("unchecked")
		String chip=(String)request.getAttribute("chipAResponder");
	%>
	
	<h2>Respuesta:</h2>
	<form action="ChipResponse" method="post">
		<input type="hidden" name="chipId" value="<%=chip%>"/>
		<table width="96%">
			<tr><td><textarea cols="95" rows="15" maxlength="500" name="respuesta"></textarea></td></tr>
			<tr><td><input type="submit" value="Enviar respuesta"/></td></tr>
		</table>
		
		
	</form>
	<a href="MainMenu">Volver</a>
	</div>
</body>
</html>