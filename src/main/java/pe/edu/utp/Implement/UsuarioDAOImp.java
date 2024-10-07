package pe.edu.utp.Implement;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Reposity.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOImp implements UsuarioDAO {

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
}
