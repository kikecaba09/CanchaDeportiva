package pe.edu.utp.Servlet.Login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Controller.ClienteController;
import pe.edu.utp.Controller.RolController;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Rol;
import pe.edu.utp.DAO.UsuarioDAO;
import pe.edu.utp.DAO.ClienteDAO;
import pe.edu.utp.DAO.RolDAO;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/miPerfil")
public class PerfilServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsuarioDAO usuarioDAO = new UsuarioController();
        ClienteDAO clienteDAO = new ClienteController();
        RolDAO rolDAO = new RolController();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("user_id"); // Asumiendo que guardaste el idUsuario en la sesión

        if (idUsuario != null) {
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
            if (usuario != null) {
                Cliente cliente = clienteDAO.obtenerClientePorId(usuario.getIdCliente());
                Rol rol = rolDAO.obtenerRolPorId(usuario.getIdRol());

                // Prepara la respuesta JSON
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.println("{");
                out.println("\"nombre\": \"" + (cliente != null ? cliente.getNombre() : "") + "\",");
                out.println("\"apellido\": \"" + (cliente != null ? cliente.getApellido() : "") + "\",");
                out.println("\"nro_identidad\": \"" + (cliente != null ? cliente.getNroIdentidad() : "") + "\",");
                out.println("\"telefono\": \"" + (cliente != null ? cliente.getTelefono() : "") + "\",");
                out.println("\"email\": \"" + (cliente != null ? cliente.getEmail() : "") + "\",");
                out.println("\"fecha_nacimiento\": \"" + (cliente != null ? cliente.getFechaNacimiento() : "") + "\",");
                out.println("\"rol\": \"" + (rol != null ? rol.getRol() : "") + "\"");
                out.println("}");
                out.flush(); // Asegúrate de vaciar el stream
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No se ha iniciado sesión");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        // Libera recursos, si es necesario
    }
}

