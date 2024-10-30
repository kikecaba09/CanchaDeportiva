package pe.edu.utp.Model;

import java.util.Date;

public class Cliente {

    private int clienteId;
    private String nombre;
    private String apellido;
    private String nroIdentidad;
    private String telefono;
    private String email;
    private String fechaNacimiento;

    public Cliente(int clienteId, String nombre, String apellido, String nroIdentidad, String telefono, String email, String fechaNacimiento) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroIdentidad = nroIdentidad;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente() {
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

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

    public String getNroIdentidad() {
        return nroIdentidad;
    }

    public void setNroIdentidad(String nroIdentidad) {
        this.nroIdentidad = nroIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nroIdentidad='" + nroIdentidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
