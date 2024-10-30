package pe.edu.utp.Servlet.Cajeros;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.DAO.UsuarioDAO;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Model.Cliente;
import java.io.IOException;

@WebServlet("/ActualizarCajero")
public class ActualizarCajero extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUsuario = Integer.parseInt(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int idCliente = Integer.parseInt(request.getParameter("cliente_id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nroIdentidad = request.getParameter("nro_identidad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fecha_nacimiento");

        Cliente cliente = new Cliente(idCliente, nombre, apellido, nroIdentidad, telefono, email, fechaNacimiento);
        Usuario usuario = new Usuario(idUsuario, username, password, 0, idCliente, cliente); // idRol asume un valor temporal (0 o según tu lógica)

        UsuarioDAO usuarioDAO = new UsuarioController();
        usuarioDAO.actualizarUsuarioCajero(usuario);

        response.sendRedirect("listarCajeros"); // Redirecciona a la página donde se listan los cajeros
    }
}
