<%@page contentType="application/xml" pageEncoding="UTF-8"%>
<%@page import="bin.UsuarioBin"%>
<%@page import="configuration.ConnectionBD"%>
<%@page import="java.sql.*"%>

<%
    // Obtener el parámetro 'id' de la solicitud
    int id = Integer.parseInt(request.getParameter("id"));
    UsuarioBin usuarioBin = null;

    try {
        // Establecer la conexión a la base de datos
        ConnectionBD conn = new ConnectionBD();
        Connection connection = conn.getConnectionBD();

        // Preparar y ejecutar la consulta SQL para obtener los datos del usuario
        String query = "SELECT * FROM tercerparcial WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        // Si se encuentra el usuario, llenar el objeto UsuarioBin
        if (rs.next()) {
            usuarioBin = new UsuarioBin();
            usuarioBin.setId(rs.getInt("id"));
            usuarioBin.setNombre(rs.getString("nombre"));
            usuarioBin.setCurp(rs.getString("curp"));
            usuarioBin.setDescripcion(rs.getString("descripcion"));
            usuarioBin.setApodo(rs.getString("apodo"));
            usuarioBin.setEdad(rs.getInt("edad"));
        }

        // Cerrar la conexión
        connection.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }

    // Si el usuario fue encontrado, generar el XML
    if (usuarioBin != null) {
%>
<?xml version="1.0" encoding="UTF-8"?>
<Usuario>
    <ID><%= usuarioBin.getId() %></ID>
    <Nombre><%= usuarioBin.getNombre() %></Nombre>
    <CURP><%= usuarioBin.getCurp() %></CURP>
    <Descripcion><%= usuarioBin.getDescripcion() %></Descripcion>
    <Apodo><%= usuarioBin.getApodo() %></Apodo>
    <Edad><%= usuarioBin.getEdad() %></Edad>
</Usuario>
<%
    } else {
%>
<?xml version="1.0" encoding="UTF-8"?>
<Error>No se encontró el usuario.</Error>
<%
    }
%>
