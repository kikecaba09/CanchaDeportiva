package pe.edu.utp.Reposity;

import pe.edu.utp.Model.Rol;
import java.util.List;

public interface RolDAO {

    void agregarRol(Rol rol); // Cumple RF-02
    void modificarRol(Rol rol); // Cumple RF-02
    void eliminarRol(int rolId); // Cumple RF-02
    List<Rol> listarRoles(); // Cumple RF-02
    Rol obtenerRolPorId(int idRol);
}
