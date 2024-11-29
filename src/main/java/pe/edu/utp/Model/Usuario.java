package pe.edu.utp.Model;

public class Usuario {

    private int idUsuario;
    private String username;
    private String password;
    private int idRol;
    private int idCliente;
    private Cliente cliente;
    private Rol rol;

    public Usuario(int idUsuario, String username, String password, int idRol, int idCliente, Cliente cliente, Rol rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.rol = rol;
    }

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String password, int i, int idCliente, Cliente cliente) {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idRol=" + idRol +
                ", idCliente=" + idCliente +
                ", cliente=" + cliente +
                ", rol=" + rol +
                '}';
    }
}
