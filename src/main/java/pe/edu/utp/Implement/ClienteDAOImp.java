package pe.edu.utp.Implement;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Reposity.ClienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAOImp implements ClienteDAO {

    @Override
    public void agregarCliente(Cliente cliente) {

    }

    @Override
    public void modificarCliente(Cliente cliente) {

    }

    @Override
    public void eliminarCliente(int clienteId) {

    }

    @Override
    public List<Cliente> listarClientes() {
        return List.of();
    }

    @Override
    public Cliente obtenerClientePorUserId(int userId) {
        Cliente cliente = null;
        Connection conexion = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            String sql = "SELECT c.nombre, c.apellidoPaterno, c.apellidoMaterno, c.nro_identidad, c.telefono, c.email, c.fecha_nacimiento " +
                    "FROM User u " +
                    "JOIN Cliente c ON u.cliente_id = c.cliente_id " +
                    "WHERE u.user_id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
                cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
                cliente.setNroIdentidad(rs.getString("nro_identidad"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }
        return cliente;
    }

    @Override
    public Cliente obtenerClientePorId(int idCliente) {
        Cliente cliente = null;
        String query = "SELECT * FROM cliente WHERE clienteId = ?";

        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setClienteId(resultSet.getInt("clienteId"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                cliente.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
                cliente.setNroIdentidad(resultSet.getString("nroIdentidad"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
