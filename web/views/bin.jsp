<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.UsuarioModel" %>
<html>
<head>
    <title>Detalle de Usuario</title>
</head>
<body>
    <h1>Detalles del Usuario</h1>
    
    <%
        UsuarioModel usuario = (UsuarioModel) request.getAttribute("usuario");
        if (usuario != null) {
    %>
        <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>
        <p><strong>CURP:</strong> <%= usuario.getCurp() %></p>
        <p><strong>Descripción:</strong> <%= usuario.getDescripcion() %></p>
        <p><strong>Apodo:</strong> <%= usuario.getApodo() %></p>
        <p><strong>Edad:</strong> <%= usuario.getEdad() %></p>
    <%
        } else {
    %>
        <p>No se encontró la información del usuario.</p>
    <%
        }
    %>
</body>
</html>
