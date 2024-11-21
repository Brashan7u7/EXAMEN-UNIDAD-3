package controller;

import configuration.ConnectionBD;
import model.UsuarioModel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "UsuarioXML", urlPatterns = {"/usuarioxml"})
public class UsuarioXML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String format = request.getParameter("format"); 
        ConnectionBD conexion = new ConnectionBD();

        try ( Connection conn = conexion.getConnectionBD()) {
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    String sql = "SELECT id, nombre, curp, descripcion, apodo, edad FROM tercerparcial WHERE id = ?";

                    try ( PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        try ( ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                UsuarioModel usuario = new UsuarioModel();
                                usuario.setId(rs.getInt("id"));
                                usuario.setNombre(rs.getString("nombre"));
                                usuario.setCurp(rs.getString("curp"));
                                usuario.setDescripcion(rs.getString("descripcion"));
                                usuario.setApodo(rs.getString("apodo"));
                                usuario.setEdad(rs.getInt("edad"));

                                
                                if ("xml".equalsIgnoreCase(format)) {
                                    response.setContentType("application/xml");
                                    response.setCharacterEncoding("UTF-8");

                                    response.getWriter().write(
                                            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                                            + "<Usuario>\n"
                                            + "    <ID>" + usuario.getId() + "</ID>\n"
                                            + "    <Nombre>" + usuario.getNombre() + "</Nombre>\n"
                                            + "    <CURP>" + usuario.getCurp() + "</CURP>\n"
                                            + "    <Descripcion>" + usuario.getDescripcion() + "</Descripcion>\n"
                                            + "    <Apodo>" + usuario.getApodo() + "</Apodo>\n"
                                            + "    <Edad>" + usuario.getEdad() + "</Edad>\n"
                                            + "</Usuario>"
                                    );
                                } else {
                                    
                                    request.setAttribute("usuario", usuario);
                                    request.getRequestDispatcher("/views/bin.jsp").forward(request, response);
                                }
                            } else {
                                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado.");
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID no es válido: " + e.getMessage());
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'id' es necesario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Muestra información de usuario en formato XML";
    }
}
