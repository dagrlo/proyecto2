<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Nuevo Chip</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
<form action="AddTopic" method="post">
	<div class="window2">
		<h2>Nuevo Chip</h2>
		<table width="96%">
			<tr><td>Tag:</td><td><input type="text" name="tag"/></td></tr>
			<tr><td colspan="2">Texto:</td></tr>
			<tr><td colspan="2"><textarea name="texto" cols="95" rows="15" maxlenght="500"></textarea></td></tr>
			<tr><td colspan="2"><input type="submit" value="Crear"/></td></tr>
		</table>
		<br/>
		<a href="MainMenu">Volver</a>	
	</div>
	</form>
</body>
</html>