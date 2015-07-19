<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,uv.es.bd.sparrow.entity.Chip" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow. Viendo por tag</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">

</head>
<body>
	<%	
		Boolean hayChips=(Boolean)request.getAttribute("hayChips");
		String tag=(String)request.getAttribute("tagActual");
	%>
	<div class="window3">
		<h2>Chips para: <%=tag %></h2>	
		<div class="listaChips2">
		<%		
			if (hayChips==false){
		%>
			Todavía no hay chips
		<%
			} else {
				@SuppressWarnings("unchecked")
				List<Chip>chips=(List<Chip>)request.getAttribute("listaChips");
				String preview;
				for(Chip chip:chips){
					/*if (chip.getText().length()<100){
						preview=chip.getText();
					} else {
						preview=chip.getText().substring(0, 132)+"...";
					}*/
		%>
			<div class="listadoChips">
				<div class="previewChip">
					<%=chip.getText() %>
				</div>
				<a href="RespondeChip?chipActual=<%=chip.getId()%>"><img src="./gfx/botonResponder.png"/></a>
				
			</div>
		
			<%
				}
				%>
				</div>
				<%
			}
			%>
			<a href="MainMenu">Volver</a>
	</div>
	
			
			
				
			
	
	
</body>
</html>