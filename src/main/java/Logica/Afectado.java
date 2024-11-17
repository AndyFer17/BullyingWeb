package Logica;

import java.time.LocalDate;

public class Afectado {
    private int idAfectado;
    private int idCliente;
    private String nombre;
    private LocalDate fechaRegistro;

    // Constructor vacío
    public Afectado() {
    }

    // Constructor con todos los parámetros
    public Afectado(int idAfectado, int idCliente, String nombre, LocalDate fechaRegistro) {
        this.idAfectado = idAfectado;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }

    // Constructor usado en agregarAfectado
    public Afectado(int idCliente, String nombre, LocalDate fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }

    public Afectado(int idCliente, String nombreAfectado, String agresion, LocalDate now) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters y setters
    public int getIdAfectado() {
        return idAfectado;
    }

    public void setIdAfectado(int idAfectado) {
        this.idAfectado = idAfectado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
