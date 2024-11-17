
package Persistencia;


import Logica.Reporte;
import config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {
    public boolean generarReporte(Reporte reporte) {
        String sql = "INSERT INTO reporte (id_cliente, id_afectado, fecha_hora, descripcion, estado, servicio) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, reporte.getIdCliente());
            ps.setInt(2, reporte.getIdAfectado());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(reporte.getFechaHora()));
            ps.setString(4, reporte.getDescripcion());
            ps.setString(5, reporte.getEstado());
            ps.setString(6, reporte.getServicio());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agendar al generar reporte: " + e.getMessage());
            return false;
        }
    }
    
   public List<Reporte> obtenerReportePorCliente(int idCliente) {
    List<Reporte> reportes = new ArrayList<>();
    String sql = "SELECT * FROM reporte WHERE id_cliente = ?";

    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCliente);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Reporte reporte = new Reporte();
            reporte.setIdreporte(rs.getInt("id_reporte"));
            
            // Obtén el LocalDateTime de la base de datos
            LocalDateTime fechaHora = rs.getObject("fecha_hora", LocalDateTime.class);
            reporte.setFechaHora(fechaHora);
            
            reporte.setDescripcion(rs.getString("descripcion"));
            reportes.add(reporte);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener reporte: " + e.getMessage());
    }

    return reportes;
}
}
