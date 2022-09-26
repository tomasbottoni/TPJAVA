<%@page import="logic.LogicLocalidad"%>
<%@page import="entities.Localidad"%>
<%@page import="entities.Zona" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicZona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Localidades</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body>
	<%LogicLocalidad ll = new LogicLocalidad();%>
	<%Localidad lo = (Localidad)request.getAttribute("localidad");%>
	<div class="container">
		<form action="ABMCadmin?accion=updateLoca" method="post">
			
			<div class="mb-3">
				<label class="form-label">Código Postal</label>
		    	<input name="codPostal" type="text" class="form-control" value="<%=lo.getCod_postal() %>">
		  	</div>
		  	
			<div class="mb-3">
		    	<label class="form-label">Descripcion</label>
		    	<input name="descripcion" type="text" class="form-control" value="<%=lo.getDescripcion() %>">
		  	</div>		  	
		  	<%LogicZona lz = new LogicZona();%>
			<%LinkedList<Zona> list =  (LinkedList<Zona>)request.getAttribute("listaZona");%>
			<%if(list == null) {list = lz.getAll();}%>
		  	
		  	<div class="btn-group-vertical" role="group" aria-label="Basic checkbox toggle button group">
		  	<label class="label px-1">Seleccione una zona</label>
		  	
		   <% Integer i=0;
		   	String id = null;
		   for (Zona zo : list) { 
		   		i++;
		   		id = "btncheck"+i;
		   %>
		  	<input name="tipoZona" type="radio" class="btn-check" id=<%=id%> autocomplete="off" value="<%=zo.getDescripcion()%>">
			<label class="btn btn-outline-primary" for=<%=id%>><%=zo.getDescripcion()%></label>
				
			<% }%>>

			</div>
	  		<button id="guardar" name="guardar" type="submit" class="btn btn-primary">Guardar</button>
		</form>
	</div>
</body>
</html>