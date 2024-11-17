package Logica;

import Persistencia.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;

public class CrearAdmin {

    public static void main(String[] args) {
       
        String nombre = "Admin";
        String apellido = "Admin";
        String correo = "admin@gmail.com";
        String contrasena = "admin1234"; 

        
        String contrasenaHash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

     
        Usuario adminUsuario = new Usuario();
        adminUsuario.setNombre(nombre);
        adminUsuario.setApellido(apellido);
        adminUsuario.setCorreo(correo);
        adminUsuario.setContrasena(contrasenaHash);
        adminUsuario.setRol("admin"); 
        adminUsuario.setAuthCode(null);
        adminUsuario.setAuthEnabled(false);

        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int idGenerado = usuarioDAO.guardarUsuario(adminUsuario);

        if (idGenerado > 0) {
            System.out.println("Administrador creado exitosamente con ID: " + idGenerado);
        } else {
            System.out.println("Error al crear el usuario administrador.");
        }
    }
}