package pe.edu.utp.Model;

public class Usuario {

    private int idUsuario;
    private String username;
    private String password;
    private int idRol;
    private int idCliente;

    public Usuario(int idUsuario, String username, String password, int idRol, int idCliente) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
        this.idCliente = idCliente;
    }

    public Usuario() {
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

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idRol=" + idRol +
                ", idCliente=" + idCliente +
                '}';
    }
}
