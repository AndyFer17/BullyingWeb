
package Logica;

import java.time.LocalDateTime;


public class Reporte {
    private int idreporte;
    private int idCliente;
    private int idAfectado;
    private LocalDateTime fechaHora;
    private String descripcion;
    private String estado;
    private String servicio;

    public Reporte() {
    }

    public Reporte(int idreporte, int idCliente, int idAfectado, LocalDateTime fechaHora, String descripcion, String estado, String servicio) {
        this.idreporte = idreporte;
        this.idCliente = idCliente;
        this.idAfectado = idAfectado;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.estado = estado;
        this.servicio = servicio;
    }

    public Reporte(int idCliente, int idAfectado, LocalDateTime fechaHora, String descripcion, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Reporte(int idCliente, int idAfectado, LocalDateTime fechaHora, String descripcion, String estado, String servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(int idreporte) {
        this.idreporte = idreporte;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAfectado() {
        return idAfectado;
    }

    public void setIdAfectado(int idAfectado) {
        this.idAfectado = idAfectado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    
    
    
}
