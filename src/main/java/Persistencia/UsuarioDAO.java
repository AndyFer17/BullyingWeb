package Persistencia;

import Logica.Cliente;
import Logica.Usuario;
import config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario buscarPorCorreo(String correo) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setAuthCode(rs.getString("auth_code"));
                    usuario.setAuthEnabled(rs.getBoolean("auth_enabled"));
                    System.out.println("Usuario encontrado: " + usuario.getNombre());
                } else {
                    System.out.println("No se encontró el usuario con el correo: " + correo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }

        return usuario;
    }

    public int guardarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idGenerado = -1;

        try {
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO usuarios(nombre, apellido, correo, contrasena, rol, auth_code, auth_enabled) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getRol());
            stmt.setString(6, usuario.getAuthCode());
            stmt.setBoolean(7, usuario.isAuthEnabled());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1); 
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idGenerado; 
    }

    public boolean guardarUsuarioYCliente(Usuario usuario, Cliente cliente) {
        boolean guardadoUsuario = false;
        boolean guardadoCliente = false;

        int idUsuarioGenerado = guardarUsuario(usuario); 

        if (idUsuarioGenerado > 0) { 
            cliente.setIdUsuario(idUsuarioGenerado); 
            guardadoCliente = new ClienteDAO().guardarCliente(cliente); 
        }

        return guardadoUsuario && guardadoCliente; 
    }
    public List<Usuario> obtenerTodosLosUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuarios"; 

    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setRol(rs.getString("rol"));
            usuario.setAuthCode(rs.getString("auth_code"));
            usuario.setAuthEnabled(rs.getBoolean("auth_enabled"));
            usuarios.add(usuario);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener usuarios: " + e.getMessage());
    }

    return usuarios;
}
   public boolean actualizarUsuario(Usuario usuario) {
    boolean actualizado = false;
    String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, correo = ?, contrasena = ?, rol = ?, auth_code = ?, auth_enabled = ? WHERE id_usuario = ?";

    try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, usuario.getNombre());
        pstmt.setString(2, usuario.getApellido());
        pstmt.setString(3, usuario.getCorreo());
        pstmt.setString(4, usuario.getContrasena());
        pstmt.setString(5, usuario.getRol());
                pstmt.setString(6, usuario.getAuthCode());
        pstmt.setBoolean(7, usuario.isAuthEnabled()); 

        pstmt.setInt(8, usuario.getIdUsuario()); 

        int filasAfectadas = pstmt.executeUpdate();
        actualizado = filasAfectadas > 0;
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return actualizado;
}
    private void closeResources(Connection conn, PreparedStatement ps) {
    if (ps != null) {
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public boolean eliminarUsuario(int idUsuario) {
    String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        int filasEliminadas = stmt.executeUpdate();
        return filasEliminadas > 0;

    } catch (SQLException e) {
        System.err.println("Error al eliminar usuario: " + e.getMessage());
        return false;
    }
}
    public Usuario buscarPorId(int idUsuario) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE id_usuario = ?"; 

    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setRol(rs.getString("rol"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return usuario;
}
}
