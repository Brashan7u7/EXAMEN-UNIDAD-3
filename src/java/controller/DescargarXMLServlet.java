package controller;

import configuration.ConnectionBD;
import model.UsuarioModel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DescargarXMLServlet", urlPatterns = {"/descargarXML"})
public class DescargarXMLServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        UsuarioModel usuarioBin = null;

        try (Connection conn = new ConnectionBD().getConnectionBD()) {
            String query = "SELECT id, nombre, curp, descripcion, apodo, edad FROM tercerparcial WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        usuarioBin = new UsuarioModel();
                        usuarioBin.setId(rs.getInt("id"));
                        usuarioBin.setNombre(rs.getString("nombre"));
                        usuarioBin.setCurp(rs.getString("curp"));
                        usuarioBin.setDescripcion(rs.getString("descripcion"));
                        usuarioBin.setApodo(rs.getString("apodo"));
                        usuarioBin.setEdad(rs.getInt("edad"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
            return;
        }

        if (usuarioBin != null) {
            response.setContentType("application/xml");
            response.setHeader("Content-Disposition", "attachment; filename=usuario_" + usuarioBin.getId() + ".xml");
            response.setCharacterEncoding("UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                out.write("<Usuario>\n");
                out.write("    <ID>" + usuarioBin.getId() + "</ID>\n");
                out.write("    <Nombre>" + usuarioBin.getNombre() + "</Nombre>\n");
                out.write("    <CURP>" + usuarioBin.getCurp() + "</CURP>\n");
                out.write("    <Descripcion>" + usuarioBin.getDescripcion() + "</Descripcion>\n");
                out.write("    <Apodo>" + usuarioBin.getApodo() + "</Apodo>\n");
                out.write("    <Edad>" + usuarioBin.getEdad() + "</Edad>\n");
                out.write("</Usuario>");
            }
        } else {
            response.setContentType("application/xml");
            try (PrintWriter out = response.getWriter()) {
                out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                out.write("<Error>No se encontró el usuario.</Error>");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Descarga el XML con la información del usuario";
    }
}
