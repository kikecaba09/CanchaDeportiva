package pe.edu.utp.Reposity;

import pe.edu.utp.Model.Cliente;
import java.util.List;

public interface ClienteDAO {

    void agregarCliente(Cliente cliente); // Cumple RF-04
    void modificarCliente(Cliente cliente); // Cumple RF-04
    void eliminarCliente(int clienteId); // Cumple RF-04
    List<Cliente> listarClientes();// Cumple RF-04
    Cliente obtenerClientePorUserId(int userId);
    Cliente obtenerClientePorId(int idCliente);
}
