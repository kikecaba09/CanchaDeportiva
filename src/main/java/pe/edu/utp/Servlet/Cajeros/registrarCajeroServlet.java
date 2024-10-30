package pe.edu.utp.Servlet.Cajeros;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.DAO.UsuarioDAO;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Rol;
import pe.edu.utp.Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registrarCajero")
public class registrarCajeroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();

        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documento = request.getParameter("nro_identidad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String fechaNacimiento = request.getParameter("fecha_nacimiento");// Asegúrate de que se pasa el parámetro

        // Validaciones
        if (nombre == null || apellido == null || documento == null || telefono == null ||
                email == null || password == null || username == null ||
                nombre.isEmpty() || apellido.isEmpty() || documento.isEmpty() || telefono.isEmpty() ||
                email.isEmpty() || password.isEmpty() || username.isEmpty() || fechaNacimiento.isEmpty()) {
            out.println("Todos los campos son obligatorios.");
            return;
        }

        if (telefono.length() != 9 || !telefono.startsWith("9")) {
            out.println("El teléfono debe tener 9 dígitos y comenzar con '9'.");
            return;
        }

        if (!email.endsWith("@gmail.com")) {
            out.println("El email debe terminar con '@gmail.com'.");
            return;
        }

        if (documento.length() != 8) {
            out.println("El documento debe tener exactamente 8 dígitos.");
            return;
        }

        // Crear objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setNroIdentidad(documento);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setFechaNacimiento(fechaNacimiento);

        // Crear objeto User
        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(password);

        Rol rol = new Rol();
        rol.setRol("cajero");// Asegúrate de definir el perfil de "cajero" correctamente

        // Registrar cajero
        UsuarioDAO userDAO = new UsuarioController();
        boolean registrado = userDAO.registrarCajero(cliente, user);

        if (registrado) {
            response.sendRedirect(request.getContextPath() + "/registrarCajero");
        } else {
            out.println("Error al registrar el cajero.");
        }
    }
}
