package Persistencia;
import Logica.Afectado;
import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AfectadoDAO {
    
    public int agregarAfectado(Afectado afectado) {
        // Corregido nombre de la tabla
        String sql = "INSERT INTO afectado (id_cliente, nombre, fecha_registro) VALUES (?, ?, ?)";
        int idAfectadoGenerado = -1;
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, afectado.getIdCliente());
            ps.setString(2, afectado.getNombre());
            // Corregido índice del parámetro
            ps.setDate(3, Date.valueOf(afectado.getFechaRegistro()));
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idAfectadoGenerado = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            // Corregido mensaje de error
            System.out.println("Error al agregar afectado: " + e.getMessage());
        }
        return idAfectadoGenerado;
    }
    
    public List<Afectado> obtenerAfectadoPorCliente(int idCliente) {
        List<Afectado> afectados = new ArrayList<>();
        String sql = "SELECT * FROM afectado WHERE id_cliente = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                // Usando el constructor correcto con todos los parámetros
                Afectado afectado = new Afectado(
                    rs.getInt("id_afectado"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getDate("fecha_registro").toLocalDate()
                );
                afectados.add(afectado);
            }
        } catch (SQLException e) {
            // Corregido mensaje de error
            System.out.println("Error al obtener afectados: " + e.getMessage());
        }
        return afectados;
    }
}