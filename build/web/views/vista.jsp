<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.UsuarioModel"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de usuarios</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Lista de usuarios</h1>
    <table>
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
                    <a href="usuario?id=<%= usuario.getId() %>" class="btn btn-info">Mostrar Bean</a>
                    <a href="usuarioxml?id=<%= usuario.getId() %>&format=xml" class="btn btn-info">Mostrar XML</a>

                    <a href="descargarXML?id=<%= usuario.getId() %>" class="btn btn-success" download="usuario_<%= usuario.getId() %>.xml">Descargar XML</a>
                    
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No hay usuarios registrados.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
