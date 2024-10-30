package pe.edu.utp.DAO;

import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Usuario;
import java.util.List;

public interface UsuarioDAO {

    void agregarUsuario(Usuario usuario); // Cumple RF-02
    void modificarUsuario(Usuario usuario); // Cumple RF-02
    void eliminarUsuario(int userId); // Cumple RF-02
    List<Usuario> listarUsuarios(); // Cumple RF-02
    Usuario obtenerUsuario(String username, String password);
    Usuario obtenerUsuarioPorId(int idUsuario);
    boolean registrarCajero(Cliente cliente, Usuario user);
    List<Usuario> listarUsuariosCajeros();

}
