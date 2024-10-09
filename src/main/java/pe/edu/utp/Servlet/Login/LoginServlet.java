package pe.edu.utp.Servlet.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Implement.UsuarioDAOImp;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Reposity.UsuarioDAO;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsuarioDAO usuarioDAO = new UsuarioDAOImp();
        Usuario usuario = usuarioDAO.obtenerUsuario(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            response.sendRedirect("/listarCanchas");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check if the user is already logged in
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect("/listarCanchas"); // Redirect to the main page
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}
