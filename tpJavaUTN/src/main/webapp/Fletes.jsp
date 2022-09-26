<%@page import="entities.Flete"%>
<%@page import="entities.Categoria" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicFlete"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>	Fletes</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="" method="POST" autocomplete="off">
			<table class="table table-striped">
		  <thead>
		    <tr>
<!-- 		      <th scope="col">ID</th> -->
		      <th scope="col">Id</th>
		      <th scope="col">Nombre</th>
<!-- 		      <th scope="col">Stock</th> -->
		    </tr>
		    <%LogicFlete lf = new LogicFlete();%>
			<%LinkedList<Flete> list =  (LinkedList<Flete>)request.getAttribute("listaFle");%>
			<%if(list == null) {list = lf.getAll();}%>
		  </thead>
		  <tbody>
		  <% for (Flete fle : list) { %>

		    <tr>
		      <td><%=fle.getId_flete() %></td>
		      <td><%=fle.getNombre() %></td>
		      <%-- 		      <td><%=pr.getStock() %></td> --%>
			  <td><a class="btn" href="ABMCadmin?accion=modificarFlete&id=<%=fle.getId_flete() %>">Modificar</a></td>
			  <td><a class="btn" href="ABMCadmin?accion=eliminarFlete&id=<%=fle.getId_flete() %>">Eliminar</a></td>
			  
		    </tr>
		    <% } %>
		  </tbody>
		</table>	
			<div class="btn-group">
  					<a href="ABMCadmin?accion=nuevoFlete" class="list-group-item list-group-item-action" aria-current="true">Nuevo Flete</a>  					 
    				<a href="MainPage?accion=home" class="list-group-item list-group-item-action" aria-current="true">Guardar</a>  
    		</div>	
		</form>
		<%if(request.getAttribute("error") != null){ %>
			<p class="text-danger fs-5"><%=(String)request.getAttribute("error") %></p>
		<%} %>
		</div>
</body>
</html>