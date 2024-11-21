package pe.edu.utp.DAO;

import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Usuario;
import java.util.List;

public interface UsuarioDAO {

    Usuario obtenerUsuario(String username, String password);
    Usuario obtenerUsuarioPorId(int idUsuario);
    boolean registrarCajero(Cliente cliente, Usuario user);
    List<Usuario> listarUsuariosCajeros();
    void actualizarUsuarioCajero(Usuario usuario);
    boolean eliminarUsuarioCajero(int idUsuario);
}
