<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="uv.es.sparrow.publico.UserBean"%>
<%@ page import="uv.es.sparrow.entities.Topics"%>
<%@ page import="uv.es.sparrow.entities.MiniUser"%>
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
		ArrayList<MiniUser> seguidos=(ArrayList<MiniUser>) request.getAttribute("listaSeguidos");
		ArrayList<MiniUser> seguidores=(ArrayList<MiniUser>) request.getAttribute("listaSeguidores");
	%>

<h1>Sparrow</h1>
Bienvenido: <%=datos.getNombre() %>
<br/>

<a href="AddTopic">Crear tema</a>    <a href="EditUser">Editar datos de usuario</a>  <a href="FindUser">Buscar usuario</a>

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
	
<h2>Seguidos</h2>
	<%
		if (seguidores.size()==0){	
	%>
		No te sigue a nadie
	<%
		} else {
			
			for (MiniUser usuario:seguidores){
	%>
		<a href="FollowUser?id=<%=usuario.getId()%>"><%=usuario.getUsername() %></a><br/>
	<%
			}
		}
	%>
	
<h2>Seguidores</h2>
	<%
		if (seguidos.size()==0){	
	%>
		No sigues a nadie
	<%
		} else {
			
			for (MiniUser usuario:seguidos){
	%>
		<a href="NoFollow?id=<%=usuario.getId()%>"><%=usuario.getUsername() %></a><br/>
	<%
			}
		}
	%>
</body>
</html>