/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioModel;

/**
 *
 * @author BRASHAN
 */
@WebServlet(name = "Usuario", urlPatterns = {"/usuario"})
public class Usuario extends HttpServlet {

    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Usuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Usuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    System.out.println("Se ejecuta el doGet");

    String idStr = request.getParameter("id");
    ConnectionBD conexion = new ConnectionBD();

    try (Connection conn = conexion.getConnectionBD()) {
        if (idStr != null) { 
            try {
                int id = Integer.parseInt(idStr);
                String sql = "SELECT id, nombre, curp, descripcion, apodo, edad FROM tercerparcial WHERE id = ?";
                
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            UsuarioModel usuario = new UsuarioModel();
                            usuario.setId(rs.getInt("id"));
                            usuario.setNombre(rs.getString("nombre"));
                            usuario.setCurp(rs.getString("curp"));
                            usuario.setDescripcion(rs.getString("descripcion"));
                            usuario.setApodo(rs.getString("apodo"));
                            usuario.setEdad(rs.getInt("edad"));
                            
                            request.setAttribute("usuario", usuario);
                            request.getRequestDispatcher("/views/bin.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("vista.jsp?error=notfound");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID no es válido: " + e.getMessage());
            }
        } else { 
            List<UsuarioModel> listaUsuarios = new ArrayList<>();
            String sql = "SELECT id, nombre, curp, descripcion, apodo, edad FROM tercerparcial";

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCurp(rs.getString("curp"));
                    usuario.setDescripcion(rs.getString("descripcion"));
                    usuario.setApodo(rs.getString("apodo"));
                    usuario.setEdad(rs.getInt("edad"));

                    listaUsuarios.add(usuario);
                }

                request.setAttribute("listaUsuarios", listaUsuarios);
                request.getRequestDispatcher("/views/vista.jsp").forward(request, response);
            }
        }
    } catch (SQLException e) {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud: " + e.getMessage());
    }
}



    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    

    request.setCharacterEncoding("UTF-8");

    ConnectionBD conexion = new ConnectionBD();
    
    String nombre = request.getParameter("nombre");
    String curp = request.getParameter("curp");
    String descripcion = request.getParameter("descripcion");
    String apodo = request.getParameter("apodo");
    String edad = request.getParameter("edad");

    int edadInt = Integer.parseInt(edad);
    
    try {
        String sql = "INSERT INTO tercerparcial (nombre, curp, descripcion, apodo, edad) VALUES (?, ?, ?, ?, ?)";
        conn = conexion.getConnectionBD();
        ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, curp);
        ps.setString(3, descripcion);
        ps.setString(4, apodo);
        ps.setInt(5, edadInt);

        int filasInsertadas = ps.executeUpdate();
        if (filasInsertadas > 0) {
            response.sendRedirect(request.getContextPath() + "/usuario");
        } else {
            System.out.println("Ocurrió un error al insertar el usuario.");
        }
    } catch (SQLException e) {
        System.out.println("Ocurrió un error: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
