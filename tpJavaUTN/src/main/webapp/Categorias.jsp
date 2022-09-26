<%@page import="entities.Producto"%>
<%@page import="entities.Categoria" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicCategoria"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>	Categorias</title>
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
		      <th scope="col">Denominacion</th>
<!-- 		      <th scope="col">Stock</th> -->
		    </tr>
		    <%LogicCategoria lc = new LogicCategoria();%>
			<%LinkedList<Categoria> list =  (LinkedList<Categoria>)request.getAttribute("listaCat");%>
			<%if(list == null) {list = lc.getAll();}%>
		  </thead>
		  <tbody>
		  <% for (Categoria cat : list) { %>

		    <tr>
		      <td><%=cat.getId_categoria() %></td>
		      <td><%=cat.getDenominacion() %></td>
		      <%-- 		      <td><%=pr.getStock() %></td> --%>
			  <td><a class="btn" href="ABMCcategoria?accion=modificar&id=<%=cat.getId_categoria() %>">Modificar</a></td>
			  <td><a class="btn" href="ABMCcategoria?accion=eliminar&id=<%=cat.getId_categoria() %>">Eliminar</a></td>
			  
		    </tr>
		    <% } %>
		  </tbody>
		</table>	
				<div class="btn-group">
  					<a href="ABMCcategoria?accion=nuevo" class="list-group-item list-group-item-action" aria-current="true">Nueva Categoria</a>  					 
    				<a href="./listProducts.jsp" class="list-group-item list-group-item-action" aria-current="true">Guardar</a>  
    			</div>	

		</form>
		<%if(request.getAttribute("error") != null){ %>
			<p class="text-danger fs-5"><%=(String)request.getAttribute("error") %></p>
		<%} %>
		</div>
</body>
</html>