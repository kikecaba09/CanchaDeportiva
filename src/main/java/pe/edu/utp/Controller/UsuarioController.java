package pe.edu.utp.Controller;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.DAO.UsuarioDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController implements UsuarioDAO {

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

    @Override
    public boolean registrarCajero(Cliente cliente, Usuario user) {
        Connection conexion = null;
        CallableStatement callableStatement = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            String sql = "{CALL CrearUsuarioCajero(?, ?, ?, ?, ?, ?, ?, ?)}"; // Asegúrate de que el procedimiento almacenado coincida
            callableStatement = conexion.prepareCall(sql);

            // Establecer parámetros del procedimiento almacenado
            callableStatement.setString(1, cliente.getNombre());
            callableStatement.setString(2, cliente.getApellido());
            callableStatement.setString(3, cliente.getNroIdentidad());
            callableStatement.setString(4, cliente.getTelefono());
            callableStatement.setString(5, cliente.getEmail());
            callableStatement.setString(8,cliente.getFechaNacimiento());
            callableStatement.setString(6, user.getUsername());
            callableStatement.setString(7, user.getPassword());

            // Ejecutar el procedimiento almacenado
            int filasAfectadas = callableStatement.executeUpdate();
            return filasAfectadas > 0; // Retorna verdadero si se insertó al menos una fila

        } catch (SQLException e) {
            System.out.println("Error al registrar cajero: " + e.getMessage());
            return false; // Retorna falso si ocurre un error

        } finally {
            // Cerrar la conexión y el CallableStatement
            ConexionBD.cerrarConexion(conexion);
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Usuario> listarUsuariosCajeros() {
        List<Usuario> cajeros = new ArrayList<>();
        String sql = "CALL ListarUsuariosCajeros()"; // Llamar al procedimiento almacenado

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                Cliente cliente = new Cliente();

                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setNroIdentidad(rs.getString("nro_identidad"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));

                // Asignar el cliente al usuario
                usuario.setCliente(cliente);

                cajeros.add(usuario); // Agregar el usuario a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cajeros; // Retornar la lista de cajeros
    }

    @Override
    public void actualizarUsuarioCajero(Usuario usuario) {
        String sql = "CALL ActualizarUsuarioCajero(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getCliente().getNombre());
            ps.setString(3, usuario.getCliente().getApellido());
            ps.setString(4, usuario.getCliente().getNroIdentidad());
            ps.setString(5, usuario.getCliente().getTelefono());
            ps.setString(6, usuario.getCliente().getEmail());
            ps.setString(7, usuario.getCliente().getFechaNacimiento());
            ps.setString(8, usuario.getUsername());
            ps.setString(9, usuario.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuarioCajero(int userId) {
        String query = "CALL EliminarUsuarioCajero(?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones aquí
        }
    }
}
