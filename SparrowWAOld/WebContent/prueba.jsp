<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="es.uv.bd.sparrow.users.UsersBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="usersBean" class="es.uv.bd.sparrow.users.UsersBean"/>
prueba<br/>
 	Logeado:<%=usersBean.getLogged() %>
 	<a href="mainPage.jsp">return</a>
</body>
</html>