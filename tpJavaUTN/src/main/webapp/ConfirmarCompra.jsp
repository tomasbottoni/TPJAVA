<%@page import="entities.Localidad" %>
<%@page import="logic.LogicLocalidad" %>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Confirmar compra</title>
	<link rel="stylesheet" href="Carrito_style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<%String total = (String)request.getAttribute("totalPagar"); %>
	<%if(total==null) total="0.0";%>
	
	<%LogicLocalidad logicloc= new LogicLocalidad();%>
	<%LinkedList<Localidad> list = logicloc.getAll();%>
</head>
<body>
	<header>
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
	    					<a class="nav-link" aria-current="page" href="MainPage?accion=home"> Home</a>
	    				</li>
	    				<li class="nav-item">
	    					<a class="nav-link" aria-current="page" href="listProducts.jsp">Productos</a>
	    				</li>
	    				<li class="nav-item dropdown"> 
	    					<a class="nav-link active dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" href="#">Mi cuenta</a>
	    					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	    						<li><a class="dropdown-item" href="miPerfil.jsp">Perfil</a></li>
	    						<li><a class="dropdown-item" href="MainPage?accion=logout">Cerrar Sesión</a></li>
	    					</ul>
	    				</li>
	    			</ul>
	    			<ul class="navbar-nav me-4 mb-2 mb-lg-end">
	    				<li class="nav-item">
	    					<a class="nav-link" aria-current="page" href="Carro_de_compras.jsp">Mi carrito</a>
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
			<div class="col-sm">
				<form action="Compra" method="get">
					<div class="card">
						<div class="card-header">
							<h3>Complete los datos de la compra</h3>
						</div>
							<div class="card-body">
							
							<label class="form-label">Ingrese la localidad</label>
							<%for(Localidad loc : list){ %>
								<div class="form-check">
		  							<input class="form-check-input" type="radio" name="localidad" value=<%=loc.getCod_postal() %> id="flexRadioDefault1" checked>
		  							<label class="form-check-label" for="flexRadioDefault1"><%=loc.getDescripcion() %></label>
								</div>
							<%}%>
							
							<label>Domicilio:</label>
							<input name="domicilio" type="text" class="form-control" required>
							
							<label class="form-label">Elija una opcion</label>
							<div class="form-check">
		  							<input class="form-check-input" type="radio" name="opcion" value="envio" id="flexRadioDefault1" checked>
		  							<label class="form-check-label" for="flexRadioDefault1">Envio</label>
							</div>
							<div class="form-check">
		  							<input class="form-check-input" type="radio" name="opcion" value="local" id="flexRadioDefault1">
		  							<label class="form-check-label" for="flexRadioDefault1">Retirar en el local</label>
							</div>
								
								
							<label>Total a pagar:</label>
							<input name="total" type="text"  value=<%=total %> class="form-control" readonly>							
						</div>
						<div class="card-footer">
							<button type="submit" class="btn btn-danger">Confirmar compra</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>