<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.UsuarioModel" %>
<html>
<head>
    <title>Detalle de Usuario</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="mb-4">Detalles del Usuario</h1>

        <%
            UsuarioModel usuario = (UsuarioModel) request.getAttribute("usuario");
            if (usuario != null) {
        %>
            <div class="card">
                <div class="card-body">
                    <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>
                    <p><strong>CURP:</strong> <%= usuario.getCurp() %></p>
                    <p><strong>Descripción:</strong> <%= usuario.getDescripcion() %></p>
                    <p><strong>Apodo:</strong> <%= usuario.getApodo() %></p>
                    <p><strong>Edad:</strong> <%= usuario.getEdad() %></p>
                </div>
            </div>
        <%
            } else {
        %>
            <div class="alert alert-warning mt-3" role="alert">
                No se encontró la información del usuario.
            </div>
        <%
            }
        %>
       
    </div>
</body>
</html>
