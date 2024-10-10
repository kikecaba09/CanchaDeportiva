package pe.edu.utp.Servlet.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Implement.ClienteDAOImp;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Reposity.ClienteDAO;

import java.io.IOException;

@WebServlet("/miPerfil")
public class PerfilServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        // Instancia del DAOImpl
        clienteDAO = new ClienteDAOImp();  // Usando el ClienteDAOImpl
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession session = request.getSession(false);

        if (session == null) {
            // Si no hay sesión activa, devolver un error
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No se ha iniciado sesión.");
            return;
        }

        // Obtener el ID del usuario de la sesión
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId == null) {
            // Si el ID del usuario es nulo, devolver un error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del usuario es nulo.");
            return;
        }

        try {
            // Obtener los datos del cliente a partir del userId usando el DAO
            Cliente cliente = clienteDAO.obtenerClientePorUserId(userId);

            if (cliente != null) {
                // Pasar los datos del cliente a la JSP
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("perfil.jsp").forward(request, response);
            } else {
                // Si no se encuentra el cliente, devolver un error
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los datos del cliente.");
        }
    }
}
