<%@page import="entities.Zona"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicZona"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>	Zonas</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="" method="POST" autocomplete="off">
			<table class="table table-striped">
		  <thead>
		    <tr>
<!-- 		      <th scope="col">ID</th> -->
		      <th scope="col">Codigo de Zona</th>
		      <th scope="col">Descripcion</th>
		      <th scope="col">Flete</th>
<!-- 		      <th scope="col">Stock</th> -->
		    </tr>
		    <%LogicZona lz = new LogicZona();%>
			<%LinkedList<Zona> list =  (LinkedList<Zona>)request.getAttribute("listaZona");%>
			<%if(list == null) {list = lz.getAll();}%>
		  </thead>
		  <tbody>
		  <% for (Zona zo : list) { %>

		    <tr>
		      <td><%=zo.getCod_zona() %></td>
		      <td><%=zo.getDescripcion() %></td>
		       <td><%=zo.getFlete().getNombre() %></td>
		      <%-- 		      <td><%=pr.getStock() %></td> --%>
			  <td><a class="btn" href="ABMCadmin?accion=modificarZona&id=<%=zo.getCod_zona() %>">Modificar</a></td>
			  <td><a class="btn" href="ABMCadmin?accion=eliminarZona&id=<%=zo.getCod_zona() %>">Eliminar</a></td>
			  
		    </tr>
		    <% } %>
		  </tbody>
		</table>	
				<div class="btn-group">
  					<a href="ABMCadmin?accion=nuevaZona" class="list-group-item list-group-item-action" aria-current="true">Nueva Zona</a>  					 
    				<a href="MainPage?accion=home" class="list-group-item list-group-item-action" aria-current="true">Guardar</a>  
    			</div>	

		</form>
		<%if(request.getAttribute("error") != null){ %>
			<p class="text-danger fs-5"><%=(String)request.getAttribute("error") %></p>
		<%} %>
		</div>
</body>
</html>