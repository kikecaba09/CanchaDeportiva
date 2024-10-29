package pe.edu.utp.Controller;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Rol;
import pe.edu.utp.Reposity.RolDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RolController implements RolDAO {

    @Override
    public void agregarRol(Rol rol) {

    }

    @Override
    public void modificarRol(Rol rol) {

    }

    @Override
    public void eliminarRol(int rolId) {

    }

    @Override
    public List<Rol> listarRoles() {
        return List.of();
    }

    @Override
    public Rol obtenerRolPorId(int idRol) {
        Rol rol = null;
        String query = "SELECT * FROM rol WHERE rol_id = ?";

        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idRol);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rol = new Rol();
                rol.setRolId(resultSet.getInt("rol_id"));
                rol.setRol(resultSet.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }
}
