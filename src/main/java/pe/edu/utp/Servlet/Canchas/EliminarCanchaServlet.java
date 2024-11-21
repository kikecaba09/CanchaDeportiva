package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.CanchaController;
import java.io.IOException;

@WebServlet("/eliminarCancha")
public class EliminarCanchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idCancha = request.getParameter("id");

        CanchaController canchaController = new CanchaController();
        boolean success = canchaController.eliminarCancha(Integer.parseInt(idCancha)); // Llamar al método de eliminar en el controller

        if (success) {
            response.sendRedirect("/administrarCancha"); // Redirigir a la página de administración de canchas
        } else {
            response.sendRedirect("/administrarCancha"); // Si hubo algún error, redirigir a una página de error
        }
    }
}
