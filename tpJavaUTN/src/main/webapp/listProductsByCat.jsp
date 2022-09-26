<%@page import="entities.Producto"%>
<%@page import="data.DataProducto"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta charset="ISO-8859-1">
	<title>Productos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" 
	integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" 
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<!--CABECERA-->
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">
				<img src="img/java-logo.png" width="150px">
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
    		</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
    			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
    				<li class="nav-item">
    					<a class="nav-link active" aria-current="page" href="MainPage?accion=home">Home</a>
    				</li>
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="./listProducts.jsp">Productos</a>
    				</li>
    				<li class="nav-item dropdown"> 
    					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" href="#">Mi cuenta</a>
    					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
    						<li><a class="dropdown-item" href="./miPerfil.jsp">Perfil</a></li>
    						<li><a class="dropdown-item" href="MainPage?accion=logout">Cerrar Sesión</a></li>
    					</ul>
    				</li>
    			</ul>
    			<ul class="navbar-nav me-4 mb-2 mb-lg-end">
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="Carrito?accion=carrito">Mi carrito</a>
    				</li>
    			</ul>
    			<form class="d-flex">
        			<input class="form-control me-2" type="search" placeholder="¿Qué estas buscando?" aria-label="Search"
        			size="40">
        			<button class="btn btn-outline-success" type="submit">Buscar</button>
      			</form>
    		</div>
		</div>
	</nav>
	<!--CABECERA-->
	<%	
	LinkedList<Producto> list = (LinkedList<Producto>)request.getAttribute("list");
	
	%>
	<div class="container">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">Descripción</th>
			      <th scope="col">Precio</th>
			    </tr>
			  </thead>
			  <tbody>
			  <% for (Producto pr : list) { %>
			    <tr>
			      <td><%=pr.getDescripcion() %></td>
			      <td><%=pr.getPrecio() %></td>
			      <td>	
			      <% for (int i=0; i<pr.getPromedio_valoracion(); i++) { %>
			      		<span class="fa-solid fa-star" id="1estrella"></span>
				  <% } %>
				  </td>
				  <% String denominacion = pr.getCategoria().getDenominacion(); %>
				  <td><a class="btn btn-dark" href="Carrito?accion=agregarCarritoxProd&id=<%=pr.getId()%>&catDen=<%=denominacion%>">Agregar al carrito</a></td>
			    </tr>
			  <% } %>
			  </tbody>
			</table>	
	</div>
	
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>