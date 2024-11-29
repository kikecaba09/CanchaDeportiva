package pe.edu.utp.Servlet.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.DAO.UsuarioDAO;
import pe.edu.utp.Model.Usuario;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Instanciar el controlador y obtener el usuario
        UsuarioDAO usuarioDAO = new UsuarioController();
        Usuario usuario = usuarioDAO.obtenerUsuario(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);

            // Obtener el rol del usuario
            String rol = usuario.getRol().getRol();

            // Redirigir según el rol e incluir el username y el rol en la URL, con el username primero
            if (rol.equals("administrador")) {
                response.sendRedirect("/dashboardAdmin?username=" + username + "&rol=" + rol);  // username primero
            } else if (rol.equals("cajero")) {
                response.sendRedirect("/dashboardCajero?username=" + username + "&rol=" + rol);  // username primero
            } else {
                // Si es Cliente, no se le permite acceso, redirigir al login nuevamente o a una página de error
                request.setAttribute("error", "Acceso no permitido.");
                request.getRequestDispatcher("index.html").forward(request, response);
            }

        } else {
            // Si las credenciales son incorrectas, se muestra un mensaje de error
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Verificar si el usuario ya está logueado
        if (session != null && session.getAttribute("usuario") != null) {
            // Si ya está logueado, se redirige a la página correspondiente según el rol
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String rol = usuario.getRol().getRol();
            String username = usuario.getUsername();  // Obtener el nombre de usuario

            // Redirigir a la página correspondiente con el username primero, seguido del rol en la URL
            if (rol.equals("Administrador")) {
                response.sendRedirect("/dashboardAdmin?username=" + username + "&rol=" + rol);
            } else if (rol.equals("Cajero")) {
                response.sendRedirect("/dashboardCajero?username=" + username + "&rol=" + rol);
            }
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}

