<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="uv.es.sparrow.publico.UserBean"%>
<%@ page import="uv.es.sparrow.entities.Topics"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="userBean" class="uv.es.sparrow.publico.UserBean" />
	<%
		UserBean datos = (UserBean) session.getAttribute("UserBean");
	
		@SuppressWarnings("unchecked")
		List<Topics> temas = (List<Topics>) request.getAttribute("listaTemas");
	%>

<h1>Sparrow</h1>
Bienvenido: <%=datos.getNombre() %>
<br/>
<a href="AddTopic">Crear tema</a>
<h2>Temas:</h2>
	<%
		if (temas.size()==0){
	%>
		No hay temas
	<%
		} else {
			for (Topics tema:temas){		
	%>
		<a href="ViewByTag?tag=<%=tema.getTag() %>"><%=tema.getTag() %></a><br/>
	<%
			}
		}
	%>
</body>
</html>