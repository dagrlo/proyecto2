<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,uv.es.sparrow.entities.Chips" %>
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
				@SuppressWarnings("unchecked")
				ArrayList<Chips> chips=(ArrayList<Chips>)request.getAttribute("listaChips");
				String preview;
				
				if (chips.size()==0){
		%>
				No hay respuestas		
		<%
				} else 
				{
				for(Chips chip:chips){				
		%>
			<div class="listadoChips">
				<div class="previewChip">
					<%=chip.getTexto() %>
					[<%=chip.getAutor() %>]
				</div>
				<a href="ChipResponse?chipActual=<%=chip.getId()%>"><img src="./gfx/botonResponder.png"/></a>
				
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