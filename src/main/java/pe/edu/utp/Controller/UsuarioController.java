package pe.edu.utp.Controller;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Reposity.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController implements UsuarioDAO {

    @Override
    public void agregarUsuario(Usuario usuario) {

    }

    @Override
    public void modificarUsuario(Usuario usuario) {

    }

    @Override
    public void eliminarUsuario(int userId) {

    }

    @Override
    public List<Usuario> listarUsuarios() {
        return List.of();
    }

    @Override
    public Usuario obtenerUsuario(String username, String password) {
        Usuario usuario = null;
        Connection conexion = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("user_id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdRol(rs.getInt("rol_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE user_id = ?";
        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("user_id"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("password")); // No se recomienda enviar la contraseña
                usuario.setIdRol(resultSet.getInt("idRol"));
                usuario.setIdCliente(resultSet.getInt("idCliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
