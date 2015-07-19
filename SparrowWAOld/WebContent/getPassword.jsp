<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,uv.es.bd.sparrow.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Password</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		String password = (String) request.getAttribute("password");
	%>

	<div class="window">
		<h2>Recuperar password</h2>
		<br/>
		<p><b>Password: </b><%=password%></p>
		<br/>
		<a href="index.jsp">Volver</a>
	</div>
	
</body>
</html>