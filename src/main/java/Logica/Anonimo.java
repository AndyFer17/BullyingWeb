package Logica;

public class Anonimo {
    private String nombre;
    private String apellido;
    private String email;
    private String nombreAcusado;
    private String comentario;
    private String imagenRuta; // Ruta relativa de la imagen

    public Anonimo(String nombre, String apellido, String email, String nombreAcusado, String comentario, String imagenRuta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nombreAcusado = nombreAcusado;
        this.comentario = comentario;
        this.imagenRuta = imagenRuta;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreAcusado() {
        return nombreAcusado;
    }

    public void setNombreAcusado(String nombreAcusado) {
        this.nombreAcusado = nombreAcusado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }
}
