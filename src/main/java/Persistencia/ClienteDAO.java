/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Logica.Cliente;
import config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public Cliente buscarPorId(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setIdUsuario(rs.getInt("id_usuario"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getString("telefono"));
                    System.out.println("Cliente encontrado: " + cliente.getIdCliente());
                } else {
                    System.out.println("No se encontró el cliente con ID: " + idCliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }

        return cliente;
    }
    public boolean guardarCliente(Cliente cliente) {
        boolean guardado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO clientes(id_usuario, direccion, telefono) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdUsuario()); 
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());

            guardado = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
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

        return guardado;
    }
     public int obtenerIdClientePorUsuarioId(int idUsuario) {
        int idCliente = -1;
        String query = "SELECT id_cliente FROM clientes WHERE id_usuario = ?"; 

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                idCliente = rs.getInt("id_cliente"); 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        
        return idCliente;
    }
}