<%@page import="entities.Zona"%>
<%@page import="entities.Flete" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicFlete"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Zonas</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="ABMCadmin?accion=insertZona" method="POST" autocomplete="off">
			<div class="mb-3">
		    	<label class="form-label">Descripcion</label>
		    	<input id="descripcion" name="descripcion" type="text" class="form-control">
		  	</div>
		  	<%LogicFlete lf = new LogicFlete();%>
			<%LinkedList<Flete> list =  (LinkedList<Flete>)request.getAttribute("listaFle");%>
			<%if(list == null) {list = lf.getAll();}%>
		  	
		  	<div class="btn-group-vertical" role="group" aria-label="Basic checkbox toggle button group">
		  	<label class="label px-1">Seleccione un flete</label>
		  	
		   <% Integer i=0;
		   	String id = null;
		   for (Flete fle : list) { 
		   		i++;
		   		id = "btncheck"+i;
		   %>
		  	<input name="tipoFlete" type="radio" class="btn-check" id=<%=id%> autocomplete="off" value="<%=fle.getNombre()%>">
			<label class="btn btn-outline-primary" for=<%=id%>><%=fle.getNombre()%></label>
				
			<% }%>>

			</div>
	  		<button type="submit" class="btn btn-primary">Guardar</button>
		</form>
	</div>
</body>
</html>