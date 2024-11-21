<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.UsuarioModel"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de usuarios</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Lista de usuarios</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>CURP</th>
                    <th>Nombre</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<UsuarioModel> listaUsuarios = (ArrayList<UsuarioModel>) request.getAttribute("listaUsuarios");

                    if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
                        for (UsuarioModel usuario : listaUsuarios) {
                %>
                <tr>
                    <td><%= usuario.getCurp() %></td>
                    <td><%= usuario.getNombre() %></td>
                    <td>
                        <a href="usuario?id=<%= usuario.getId() %>" class="btn btn-info btn-sm">Mostrar Bean</a>
                        <a href="usuarioxml?id=<%= usuario.getId() %>&format=xml" class="btn btn-info btn-sm">Mostrar XML</a>
                        <a href="descargarXML?id=<%= usuario.getId() %>" class="btn btn-success btn-sm" download="usuario_<%= usuario.getId() %>.xml">Descargar XML</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3" class="text-center">No hay usuarios registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
