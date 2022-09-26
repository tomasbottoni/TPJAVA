<%@page import="entities.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Página principal</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<%Usuario us = (Usuario)session.getAttribute("user");%>
	<%LogicCategoria lc = new LogicCategoria(); %>
	<%LinkedList<Categoria> listCat = lc.getAll(); %>

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
    					<a class="nav-link active" aria-current="page" href="#">Home</a>
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

	<!-- Barra lateral y main -->

	<div class="container-fluid bg-dark">
    	<div class="row flex-nowrap">
        	<div class="col-auto px-0 bg-dark">
            	<div id="sidebar" class="collapse collapse-horizontal show ">
                	<div id="sidebar-nav" class="list-group border-0 rounded-0 text-sm-start min-vh-100">
                	<%for (Categoria c : listCat){ %>
                    	<a href="MainPage?accion=list&catDen=<%=c.getDenominacion() %>" class="list-group-item  list-group-item-dark border-end-0 d-inline-block text-truncate" 
                    	data-bs-parent="#sidebar"><span><%=c.getDenominacion() %></span></a>
                    <%} %>
                	</div>
            	</div>
        	</div>
        	<main class="col ps-md-0 pt-1 p-0">
            	<a href="#" data-bs-target="#sidebar" data-bs-toggle="collapse" class="border rounded-3 p-1  text-decoration-none"><i class="bi bi-list bi-lg py-2 p-1"></i> Menu</a>
            	<div class="row">
                	<div class="col-12">
                    	<div id="carouselExampleSlidesOnly" class="carousel slide p-1" data-bs-ride="carousel">
  							<div class="carousel-inner" >
    							<div class="carousel-item active">
      								<img src="img/slide01.jpg" class="d-block w-100" height="340" alt="...">
    							</div>
    							<div class="carousel-item">
      								<img src="img/slide02.jpg" class="d-block w-100" height="340" alt="...">
    							</div>
    							<div class="carousel-item">
      								<img src="img/slide03.jpg" class="d-block w-100" height="340" alt="...">
    							</div>
  							</div>
						</div>
						<!-- Cards -->
						<div class="card-group p-2">
							<div class="card border-dark mb-3 mx-2" style="width: 18rem;">
					  			<img src="img/gabinete01.jpg" class="card-img-top" alt="...">
					  			<div class="card-body">
					    			<h5 class="card-title">Gabinete Pc Gamer Sentey X10 Rgb Acrilico Gaming Usb 3.0 Sin Fuente</h5>
					    			<p class="card-text">$ 7,209</p>
					    			<a href="./listProducts.jsp" class="btn btn-primary">Descubrilo</a>
					  			</div>
							</div>
							<div class="card border-dark mb-3 mx-2" style="width: 18rem;">
					  			<img src="img/gabinete02.jpg" class="card-img-top" alt="...">
					  			<div class="card-body">
					    			<h5 class="card-title">Gabinete Gamer Noganet 8609 Fuente 600w, 3 Coolers con LEDs</h5>
					    			<p class="card-text">$ 16.620</p>
					    			<a href="./listProducts.jsp" class="btn btn-primary">Descubrilo</a>
					  			</div>
							</div>
							<div class="card border-dark mb-3 mx-2" style="width: 18rem;">
					  			<img src="img/gabinete03.jpg" class="card-img-top" alt="...">
					  			<div class="card-body">
					    			<h5 class="card-title">Gabinete Gamer Aerocool Rift White Mid Tower Rgb Blanco</h5>
					    			<p class="card-text">$ 9,999</p>
					    			<a href="./listProducts.jsp" class="btn btn-primary">Descubrilo</a>
					  			</div>
							</div>
							<div class="card border-dark mb-3 mx-2" style="width: 18rem;">
					  			<img src="img/gabinete04.jpg" class="card-img-top" alt="...">
					  			<div class="card-body">
					    			<h5 class="card-title">Gabinete Gamer Aorus Ac300g Tg Glass Atx Black</h5>
					    			<p class="card-text">$ 16,962</p>
					    			<a href="./listProducts.jsp" class="btn btn-primary">Descubrilo</a>
					  			</div>
							</div>
						</div>
						<!-- Cards -->	
                	</div>
            	</div>
        	</main>
    	</div>
	</div>

	<!-- Barra lateral y main -->
	</body>
</html>