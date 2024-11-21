<%@page contentType="application/xml" pageEncoding="UTF-8"%>
<%@page import="bin.UsuarioBin"%>
<%@page import="configuration.ConnectionBD"%>
<%@page import="java.sql.*"%>

<%
   
    int id = Integer.parseInt(request.getParameter("id"));
    UsuarioBin usuarioBin = null;

    try {
      
        ConnectionBD conn = new ConnectionBD();
        Connection connection = conn.getConnectionBD();

      
        String query = "SELECT * FROM tercerparcial WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

      
        if (rs.next()) {
            usuarioBin = new UsuarioBin();
            usuarioBin.setId(rs.getInt("id"));
            usuarioBin.setNombre(rs.getString("nombre"));
            usuarioBin.setCurp(rs.getString("curp"));
            usuarioBin.setDescripcion(rs.getString("descripcion"));
            usuarioBin.setApodo(rs.getString("apodo"));
            usuarioBin.setEdad(rs.getInt("edad"));
        }

    
        connection.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }

 
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
