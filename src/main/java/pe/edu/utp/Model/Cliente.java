package pe.edu.utp.Model;

import java.util.Date;

public class Cliente {

    private int clienteId;            // Identificador único del cliente
    private String nombre;            // Nombre del cliente
    private String apellido;          // Apellido del cliente
    private String nroIdentidad;      // Número de identidad (cédula, DNI, etc.)
    private String telefono;          // Número de teléfono
    private String email;             // Correo electrónico
    private Date fechaNacimiento;

    public Cliente(int clienteId, String nombre, String apellido, String nroIdentidad,
                   String telefono, String email, Date fechaNacimiento) {
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
