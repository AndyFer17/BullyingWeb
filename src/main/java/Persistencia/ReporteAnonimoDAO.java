package Persistencia;

import Logica.Anonimo;
import config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReporteAnonimoDAO {
    public boolean guardarAnonimo(Anonimo anonimo) {
        String sql = "INSERT INTO reporteAnonimo (nombre, apellido, email, nombreAcusado, comentario, imagen_ruta) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, anonimo.getNombre());
            stmt.setString(2, anonimo.getApellido());
            stmt.setString(3, anonimo.getEmail());
            stmt.setString(4, anonimo.getNombreAcusado());
            stmt.setString(5, anonimo.getComentario());
            stmt.setString(6, anonimo.getImagenRuta()); // Ruta relativa

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el reporte anónimo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
