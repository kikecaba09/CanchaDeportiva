package pe.edu.utp.Servlet.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Implement.ClienteDAOImp;
import pe.edu.utp.Implement.RolDAOImp;
import pe.edu.utp.Implement.UsuarioDAOImp;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Rol;
import pe.edu.utp.Reposity.UsuarioDAO;
import pe.edu.utp.Reposity.ClienteDAO;
import pe.edu.utp.Reposity.RolDAO;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/miPerfil")
public class PerfilServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Inicializa las implementaciones de los DAOs en cada solicitud
        UsuarioDAO usuarioDAO = new UsuarioDAOImp();
        ClienteDAO clienteDAO = new ClienteDAOImp();
        RolDAO rolDAO = new RolDAOImp();

        // Obtiene la sesión actual
        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("user_id"); // Asumiendo que guardaste el idUsuario en la sesión

        if (idUsuario != null) {
            // Busca el usuario en la base de datos
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
            if (usuario != null) {
                Cliente cliente = clienteDAO.obtenerClientePorId(usuario.getIdCliente());
                Rol rol = rolDAO.obtenerRolPorId(usuario.getIdRol());

                // Prepara la respuesta JSON
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.println("{");
                out.println("\"nombre\": \"" + (cliente != null ? cliente.getNombre() : "") + "\",");
                out.println("\"apellidoPaterno\": \"" + (cliente != null ? cliente.getApellidoPaterno() : "") + "\",");
                out.println("\"apellidoMaterno\": \"" + (cliente != null ? cliente.getApellidoMaterno() : "") + "\",");
                out.println("\"nroIdentidad\": \"" + (cliente != null ? cliente.getNroIdentidad() : "") + "\",");
                out.println("\"telefono\": \"" + (cliente != null ? cliente.getTelefono() : "") + "\",");
                out.println("\"email\": \"" + (cliente != null ? cliente.getEmail() : "") + "\",");
                out.println("\"fechaNacimiento\": \"" + (cliente != null ? cliente.getFechaNacimiento() : "") + "\"");
                out.println("\"rol\": \"" + (rol != null ? rol.getRol() : "") + "\",");
                out.println("}");
                out.println("}");
                out.println("}");
            } else {
                // Si no se encuentra el usuario, responde con un mensaje de error
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
            }
        } else {
            // Si no hay sesión activa, responde con un mensaje de error
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No se ha iniciado sesión");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        // Libera recursos, si es necesario
    }
}
