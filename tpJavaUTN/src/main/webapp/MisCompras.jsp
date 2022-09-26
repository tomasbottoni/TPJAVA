<%@page import="entities.Venta_Producto" %>
<%@page import="entities.Usuario" %>
<%@page import="entities.Localidad" %>
<%@page import="logic.LogicVenta" %>
<%@page import="logic.LogicLocalidad" %>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Mis compras</title>
	<link rel="stylesheet" href="css/MisCompras_style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	
	<%Usuario us = (Usuario)session.getAttribute("user");%>
	
	<%LogicVenta lv = new LogicVenta(); %>
	<% LinkedList<Venta_Producto> list = lv.getAllVentaProducto(us.getId_usuario()); %>
	<% if(list == null) {list = new  LinkedList<Venta_Producto>();}; %>
	
	<%LogicLocalidad loclog = new LogicLocalidad(); %>
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
    					<a class="nav-link" aria-current="page" href="Carrito?accion=carrito">Mi carrito</a>
    				</li>
    				<li class="nav-item">
    					<a class="nav-link active" aria-current="page" href="./MisCompras.jsp">Mis Compras</a>
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
	
	
	<div id="cajaCompra">
	<%int cont = 1; %>
	<%for (Venta_Producto vp : list) { %>
	<%Localidad loc = loclog.getOne(vp.getVenta().getCod_postal()); %>
	<div>
		    <h5 class="card-title"> Compra <%=cont++%></h5>
		    <p class="card-text">Fecha: <%= vp.getVenta().getFechaVenta()%></p>
		    <p class="card-text">Estado: <%= vp.getVenta().getEstado()%></p>
		    <p class="card-text">Producto: <%= vp.getProd().getDescripcion()%></p>
		    <p class="card-text">Precio: <%= vp.getProd().getPrecio()%></p>
		    <p class="card-text">LLega el: <%= vp.getVenta().getFechaVenta().plusDays(loc.getDias_de_tardanza())%></p>
		    <%if(vp.getVenta().getEstado().equals("entregado") && vp.ifWasValued()==false){ %>
				<form id="form" method="post" action="ABMCproducto">
			  		<p class="clasificacion">
			  			<button type="submit" id="btnCalificar" class="btn btn-primary">Calificar</button>
			    		<input id="<%= vp.getProd().getDescripcion()%>1" type="radio" name="estrellas" value="<%=vp.getVenta().getId_venta()%>,<%= vp.getProd().getId()%>,5" style="display: none">
			    		<!--
			    		-->
			    		<label for="<%= vp.getProd().getDescripcion()%>1">★</label>
			    		<!--
			   		 	-->
			   		    <input id="<%= vp.getProd().getDescripcion()%>2" type="radio" name="estrellas" value="<%=vp.getVenta().getId_venta()%>,<%= vp.getProd().getId()%>,4" style="display: none">
			   		    <!--
			    		-->
			    		<label for="<%= vp.getProd().getDescripcion()%>2">★</label>
			    		<!--
			    		-->
			   			<input id="<%= vp.getProd().getDescripcion()%>3" type="radio" name="estrellas" value="<%=vp.getVenta().getId_venta()%>,<%= vp.getProd().getId()%>,3" style="display: none">
			   			<!--
			    		-->
			    		<label for="<%= vp.getProd().getDescripcion()%>3">★</label>
			    		<!--
			    		-->
			    		<input id="<%= vp.getProd().getDescripcion()%>4" type="radio" name="estrellas" value="<%=vp.getVenta().getId_venta()%>,<%= vp.getProd().getId()%>,2" style="display: none">
			    		<!--
			    		-->
			    		<label for="<%= vp.getProd().getDescripcion()%>4">★</label>
			    		<!--
			    		-->
			    		<input id="<%= vp.getProd().getDescripcion()%>5" type="radio" name="estrellas" value="<%=vp.getVenta().getId_venta()%>,<%= vp.getProd().getId()%>,1" style="display: none">
			    		<!--
			    		-->
			    		<label for="<%= vp.getProd().getDescripcion()%>5">★</label>
			 		</p>
			 		
				</form>
			<%} %>
			<%if(vp.getVenta().getEstado().equals("pendiente") && us.getTipoUsuario().getId_TipoUsuario() == 1){%>
				<form method="post" action="Carrito">
					<input name="cambiarEstado" value="<%=vp.getVenta().getId_venta()%>" style="display: none">
					<button type="submit" class="btn btn-primary" >Cambiar Estado</button>
				</form>
			<%} %>
	 </div>
	<%}%>
	</div>

</body>
</html>