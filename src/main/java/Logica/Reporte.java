
package Logica;

import java.time.LocalDateTime;


public class Reporte {
    private int idReporte;
    private int idCliente;
    private int idAfectado;
    private LocalDateTime fechaHora;
    private String descripcion;
    private String estado;
    private String servicio;

    public Reporte() {
    }

public Reporte(int idCliente, int idAfectado, LocalDateTime fechaHora, String descripcion, String estado, String servicio) {
    this.idCliente = idCliente;
    this.idAfectado = idAfectado;
    this.fechaHora = fechaHora;
    this.descripcion = descripcion;
    this.estado = estado;
    this.servicio = servicio;
}


    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
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

