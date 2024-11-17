package Servlets;

import Logica.Usuario;
import Persistencia.UsuarioDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String mensaje = "";

        try {
            if (action == null || "login".equals(action)) {
                String correo = request.getParameter("email");
                String contrasena = request.getParameter("password");

                if (correo == null || contrasena == null) {
                    request.setAttribute("error", "Por favor ingresa el correo y la contraseña.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
                System.out.println("Usuario encontrado: " + (usuario != null ? usuario.getNombre() : "null"));

                if (usuario != null && usuario.getContrasena() != null && BCrypt.checkpw(contrasena, usuario.getContrasena())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);

                    if ("admin".equals(usuario.getRol())) {
                        response.sendRedirect("admin.jsp");
                    } else {
                        response.sendRedirect("user.jsp");
                    }
                } else {
                    if (usuario == null) {
                        request.setAttribute("error", "El usuario no existe.");
                    } else {
                        request.setAttribute("error", "Contraseña incorrecta.");
                    }
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else if ("agregar".equals(action)) {
                Usuario usuario = new Usuario();
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setApellido(request.getParameter("apellido"));
                usuario.setCorreo(request.getParameter("correo"));
                String contrasena = request.getParameter("contrasena");
                if (contrasena == null || contrasena.isEmpty()) {
                    request.setAttribute("error", "Por favor ingresa una contraseña.");
                    request.getRequestDispatcher("agregarUsuario.jsp").forward(request, response);
                    return;
                }
                String hashedContrasena = BCrypt.hashpw(contrasena, BCrypt.gensalt());
                usuario.setContrasena(hashedContrasena);
                usuario.setRol(request.getParameter("rol"));

                usuario.setAuthCode(null);
                usuario.setAuthEnabled(false); 

                int idUsuario = usuarioDAO.guardarUsuario(usuario);
                mensaje = idUsuario > 0 ? "Usuario agregado con éxito" : "Error al agregar usuario";

            } else if ("editar".equals(action)) {
                int idUsuario = Integer.parseInt(request.getParameter("usuarioId"));

                Usuario usuarioExistente = usuarioDAO.buscarPorId(idUsuario);
                if (usuarioExistente == null) {
                    mensaje = "Usuario no encontrado";
                } else {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(idUsuario);
                    usuario.setNombre(request.getParameter("nombre"));
                    usuario.setApellido(request.getParameter("apellido"));
                    usuario.setCorreo(request.getParameter("correo"));
                    usuario.setRol(request.getParameter("rol"));

                    String contrasena = request.getParameter("contrasena");
                    if (contrasena != null && !contrasena.isEmpty()) {
                        String hashedContrasena = BCrypt.hashpw(contrasena, BCrypt.gensalt());
                        usuario.setContrasena(hashedContrasena);
                    } else {
                        usuario.setContrasena(usuarioExistente.getContrasena());
                    }

                    usuario.setAuthCode(null);
                    usuario.setAuthEnabled(usuarioExistente.isAuthEnabled()); 

                    boolean actualizado = usuarioDAO.actualizarUsuario(usuario);
                    mensaje = actualizado ? "Usuario actualizado con éxito" : "Error al actualizar usuario";
                }
            } else if ("eliminar".equals(action)) {
                int idUsuario = Integer.parseInt(request.getParameter("usuarioId"));
                boolean eliminado = usuarioDAO.eliminarUsuario(idUsuario);
                mensaje = eliminado ? "Usuario eliminado con éxito" : "Error al eliminar usuario";
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"" + mensaje + "\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Error: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}