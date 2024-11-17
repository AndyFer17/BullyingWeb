package Servlets;

import Logica.Cliente;
import Logica.Usuario;
import Persistencia.UsuarioDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono"); 

        if (contrasena == null || contrasena.isEmpty()) {
            request.setAttribute("error", "La contraseña no puede estar vacía.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return; 
        }

        String contrasenaHash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(contrasenaHash); 
        nuevoUsuario.setRol("user"); 
        nuevoUsuario.setAuthCode(null);
        nuevoUsuario.setAuthEnabled(false);

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setDireccion(direccion); 
        nuevoCliente.setTelefono(telefono); 

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean guardado = usuarioDAO.guardarUsuarioYCliente(nuevoUsuario, nuevoCliente);

        if (guardado) {
            System.out.println("Registro exitoso. Redirigiendo a login.jsp...");
            response.sendRedirect("login.jsp");
        } else {
            System.out.println("Error en el registro. Volviendo a registro.jsp...");
            request.setAttribute("error", "Hubo un error al registrar el usuario y cliente.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
