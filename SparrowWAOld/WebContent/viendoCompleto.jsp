<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,uv.es.bd.sparrow.entity.Chip" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Viendo chip</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
<%
		@SuppressWarnings("unchecked")
		Chip chip=(Chip)request.getAttribute("chipCompleto");
	%>
	<div class="window2">
		<h2>Viendo chip: <%=chip.getTag() %></h2>
		<div class="verTextoChip">
			
				<%=chip.getText() %>
			
		</div>
		<a href="MainMenu">Volver</a>
	</div>
</body>
</html>