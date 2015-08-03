
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ page import="uv.es.sparrow.publico.UserBean"%>
<%@ page import="uv.es.sparrow.entities.Topics"%>
<%@ page import="uv.es.sparrow.entities.MiniUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">
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
	<header> <img src="./gfx/logoPeque.png" />
	<h1>Sparrow</h1>
	<div id="cajaNombre">
		<p>
			Bienvenido
			<%=datos.getNombre()%>
		</p>
	</div>
	</header>
	<nav> 
		<a href="FindUser">Buscar usuarios</a> 
		<a href="AddTopic">Crear tema</a> 
		<a href="EditUser">Preferencias</a> 
		<!-- <a href="CierraSesion">Cerrar sesión</a> --> 
	</nav>

	<aside>
	<fieldset>
		<legend>Seguidores</legend>
		<div class="listaFollows">

			<%
				if (seguidos.size() == 0) {
			%>
			No hay seguidores
			<%
				} else {
					for (MiniUser follow : seguidos) {
			%>
			<div class="follow">
				<%=follow.getUsername()%><a
					href="FollowUser?id=<%=follow.getId()%>"><img
					src="gfx/botonMas.png" /></a>
			</div>

			<%
				}

				}
			%>
		</div>
	</fieldset>
	<fieldset>
		<legend>Siguiendo</legend>
		<div class="listaFollows">
			<%
				if (seguidores.size() == 0) {
			%>
			No sigues a nadie
			<%
				} else {
					for (MiniUser follow : seguidores) {
			%>
			<div class="follow">
				<%=follow.getUsername()%>
				<a href="NoFollow?id=<%=follow.getId()%>"><img
					src="gfx/botonMenos.png" /></a>
			</div>


			<%
				}

				}
			%>



		</div>
	</fieldset>
	</aside>

	<section>
	<h2>Últimos chips</h2>
	<div id="listaChips">
		<%
			

			if (temas.size() == 0) {
				System.out.println("no hay");
		%>
		No hay temas.
		<%
			} else {
								
				for (Topics tema:temas) {
		%>

		<article>
		<div class="chip">
			<span class="nombreTag"><a href="ViewByTag?tag=<%=tema.getTag()%>"><%=tema.getTag()%></a></span> 
			<span class="autor"><%=tema.getUser()%></span>
		</div>


		</article>

		<%
			}
			}
		%>



	</div>
	</section>

</body>
</html>