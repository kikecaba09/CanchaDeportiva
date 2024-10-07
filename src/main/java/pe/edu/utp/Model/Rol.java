package pe.edu.utp.Model;

public class Rol {

    private int rolId;
    private String rol;

    public Rol(int rolId, String rol) {
        this.rolId = rolId;
        this.rol = rol;
    }

    public Rol() {
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "rolId=" + rolId +
                ", rol='" + rol + '\'' +
                '}';
    }
}
