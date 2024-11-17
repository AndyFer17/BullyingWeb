/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Actualiza la URL para soportar UTF-8
    private static final String URL = "jdbc:mysql://localhost:3306/bullyingWeb?useSSL=false&useUnicode=true&characterEncoding=UTF-8"; 
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(DRIVER); 
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; 
        }
        return conn;
    }
}
