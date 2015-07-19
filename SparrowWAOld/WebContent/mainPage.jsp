
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,uv.es.bd.sparrow.entity.User"%>
<%@page import="uv.es.bd.sparrow.entity.Chip"%>
<%@page import="uv.es.bd.sparrow.entity.Following"%>
<%@ page import="es.uv.bd.sparrow.users.UsersBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sparrow</title>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">
</head>
<body>
	<jsp:useBean id="usersBean" class="es.uv.bd.sparrow.users.UsersBean" />
	<%
		UsersBean datos = (UsersBean) session.getAttribute("usuario");
	%>
	<header> <img src="./gfx/logoPeque.png" />
	<h1>Sparrow</h1>
	<div id="cajaNombre">
		<p>
			Bienvenido
			<%=datos.getUser().getNombre()%>
		</p>
	</div>
	</header>
	<nav> 
		<a href="BuscaUsuarios">Buscar usuarios</a> 
		<a href="AddChip">Crear tema</a> 
		<a href="EditaDatos">Preferencias</a> 
		<a href="CierraSesion">Cerrar sesión</a> 
	</nav>

	<aside> <%
 	@SuppressWarnings("unchecked")
 	List<Following> seguidores = (List<Following>) request
 			.getAttribute("listaFollowers");
 	@SuppressWarnings("unchecked")
 	List<Following> seguidos = (List<Following>) request
 			.getAttribute("listaFolloweds");
 %>
	<fieldset>
		<legend>Seguidores</legend>
		<div class="listaFollows">

			<%
				if (seguidores.size() == 0) {
			%>
			No hay seguidores
			<%
				} else {
					for (Following follow : seguidores) {
			%>
			<div class="follow">
				<%=follow.getFollower().getUsername()%><a
					href="SeguirUsuario?id=<%=follow.getFollower().getId()%>"><img
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
				if (seguidos.size() == 0) {
			%>
			No sigues a nadie
			<%
				} else {
					for (Following follow : seguidos) {
			%>
			<div class="follow">
				<%=follow.getFollowed().getUsername()%>
				<a href="NoSeguirUsuario?id=<%=follow.getFollowed().getId()%>"><img
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
			Boolean hayTemas = (Boolean) request.getAttribute("hayTemas");

			if (hayTemas == false) {
				System.out.println("no hay");
		%>
		No hay temas.
		<%
			} else {

				System.out.println("Si hay. leo lista");
				@SuppressWarnings("unchecked")
				List<Chip> temas = (List<Chip>) request
						.getAttribute("listaTemas");

				for (Chip chip : temas) {
		%>

		<article>
		<div class="chip">
			<span class="nombreTag"><a href="VerPorTag?tag=<%=chip.getTag()%>"><%=chip.getTag()%></a></span> 
			<span class="autor"><%=chip.getUserBean().getUsername()%></span>
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