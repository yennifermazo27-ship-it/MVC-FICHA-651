package modelo.dto;

public class clienteDTO {

    private String nombre;
    private int edad;
    private String telefono;
    private String tipo;

    public clienteDTO(String nombre, int edad, String telefono, String tipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}