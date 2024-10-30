package pe.edu.utp.Servlet.Cajeros;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.DAO.UsuarioDAO;

import java.io.IOException;

@WebServlet("/EliminarCajero")
public class EliminarCajero extends HttpServlet {
    private UsuarioDAO userDAO;

    @Override
    public void init() {
        userDAO = new UsuarioController();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));

        try {
            userDAO.eliminarUsuarioCajero(userId);
            response.sendRedirect("/listarCajeros");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuariosCajeros.jsp?error=Error al eliminar el usuario");
        }
    }
}
