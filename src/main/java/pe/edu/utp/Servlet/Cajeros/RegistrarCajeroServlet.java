package pe.edu.utp.Servlet.Cajeros;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Usuario;
import java.io.IOException;

@WebServlet("/RegistrarCajero")
public class RegistrarCajeroServlet extends HttpServlet {

    private UsuarioController cajeroController = new UsuarioController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Capturar datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nroIdentidad = request.getParameter("nro_identidad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fecha_nacimiento");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Crear objetos Cliente y Usuario
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setNroIdentidad(nroIdentidad);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setFechaNacimiento(fechaNacimiento);

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password); // Puedes encriptar la contraseña aquí si lo deseas

        // Registrar cajero
        boolean registrado = cajeroController.registrarCajero(cliente, usuario);

        // Respuesta al usuario
        if (registrado) {
            response.getWriter().println("Cajero registrado exitosamente.");
        } else {
            response.getWriter().println("Error al registrar el cajero.");
        }
    }
}
