
package Logica;


public class Cliente {
    private int idCliente;
    private int idUsuario;  
    private String direccion;
    private String telefono;

    // Constructor
    public Cliente() {
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;   
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
