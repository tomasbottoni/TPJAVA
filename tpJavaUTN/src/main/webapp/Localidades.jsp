<%@page import="entities.*"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>	Localidades</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>

<body>
	<div class="container">
		<form action="" method="POST" autocomplete="off">
			<table class="table table-striped">
		  <thead>
		    <tr>
<!-- 		      <th scope="col">ID</th> -->
		      <th scope="col">Codigo Postal</th>
		      <th scope="col">Descripcion</th>
		      <th scope="col">Zona</th>
<!-- 		      <th scope="col">Stock</th> -->
		    </tr>
		    <%LogicLocalidad ll = new LogicLocalidad();%>
		    <%LogicZona lz = new LogicZona(); %>
			<%LinkedList<Localidad> list =  (LinkedList<Localidad>)request.getAttribute("listaLoca");%>
			<%if(list == null) {list = ll.getAll();}%>
		  </thead>
		  <tbody>
		  <% for (Localidad lo : list) { %>
			<%Zona z = lz.getOne(lo.getZona().getCod_zona());%>
		    <tr>
		      <td><%=lo.getCod_postal() %></td>
		      <td><%=lo.getDescripcion() %></td>
		       <td><%=z.getDescripcion() %></td>
		      <%-- 		      <td><%=pr.getStock() %></td> --%>
			  <td><a class="btn" href="ABMCadmin?accion=modificarLoca&codPostal=<%=lo.getCod_postal() %>">Modificar</a></td>
			  <td><a class="btn" href="ABMCadmin?accion=eliminarLoca&id=<%=lo.getCod_postal() %>">Eliminar</a></td>
			  
		    </tr>
		    <% } %>
		  </tbody>
		</table>	
				<div class="btn-group">
  					<a href="ABMCadmin?accion=nuevaLoca" class="list-group-item list-group-item-action" aria-current="true">Nueva Localidad</a>  					 
    				<a href="MainPage?accion=home" class="list-group-item list-group-item-action" aria-current="true">Guardar</a>  
    			</div>	

		</form>
		<%if(request.getAttribute("error") != null){ %>
			<p class="text-danger fs-5"><%=(String)request.getAttribute("error") %></p>
		<%} %>
		</div>
</body>
</html>