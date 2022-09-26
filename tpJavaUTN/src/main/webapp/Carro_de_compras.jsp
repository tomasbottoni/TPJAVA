<%@page import="java.util.ArrayList"%>
<%@page import="entities.*" %>
<%@page import="logic.LogicProducto" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Compras</title>
	<link rel="stylesheet" href="Carrito_style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<%ArrayList<int[]> listaCarrito = (ArrayList<int[]>)request.getSession().getAttribute("listaCarrito");%>
	<%if(listaCarrito == null) {listaCarrito = new ArrayList<>();};%>
	
	<%String total = (String)request.getAttribute("totalPagar"); %>
	<%if(total==null) total="0.0";%>
	
	<%LogicProducto lp= new LogicProducto();%>
	<%Usuario us = (Usuario)session.getAttribute("user");%>
</head>
<body id="contenedor">
	
	<header id="cabecera">
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
    					<a class="nav-link" aria-current="page" href="MainPage?accion=home">Home</a>
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
    			<%if(us.getTipoUsuario().getId_TipoUsuario() == 1){ %>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" href="#">Configuracion</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="./Fletes.jsp">Fletes</a></li>
                            <li><a class="dropdown-item" href="./Localidades.jsp">Localidades</a></li>
                            <li><a class="dropdown-item" href="./Zonas.jsp">Zonas</a></li>
                        </ul>
                    </li>
                    <%} %>
    				<li class="nav-item">
    					<a class="nav-link active" aria-current="page" href="Carrito?accion=carrito">Mi carrito</a>
    				</li>
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="./MisCompras.jsp">Mis Compras</a>
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
	</header>
	<div id=productos class="container mt-4">
		<div class="row">
			<div class="col-sm-8">
				<table class="table table-striped">
			  <thead>
			    <tr>
	<!-- 		  <th scope="col">ID</th> -->
			      <th scope="col">Categoria</th>
			      <th scope="col">Descripción</th>
			      <th scope="col">Cantidad</th>
			      <th scope="col">Precio</th>
	<!-- 		  <th scope="col">Stock</th> -->
			    </tr>
			  </thead>
			  <tbody>
			  
			  <% for (int i=0; i<listaCarrito.size(); i++) { %>
			  <% Producto p = lp.getOne(listaCarrito.get(i)[0]);%>
			    <tr>
			      <td><%=p.getCategoria().getDenominacion() %></td>	
			      <td><%=p.getDescripcion() %></td>
			      <td><%=listaCarrito.get(i)[1] %></td>
			      <td><%=p.getPrecio() %></td>
			    </tr>
			    <% } %>
			  </tbody>
			</table>
			</div>
			<div class="col-sm-4">
				<div class="card">
					<div class="card-header">
						<h3>Generar Compra</h3>
					</div>
					<div class="card-body">
						<label>Total a pagar:</label>
						<input type="text" value=<%=total %> class="form-control">
					</div>
					<div class="card-footer">
						<a class="btn btn-danger btn-block" href="Carrito?accion=comprar">Comprar</a>
						<a class="btn btn-danger btn-block" href="Carrito?accion=limpiar">Limpiar carrito</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>