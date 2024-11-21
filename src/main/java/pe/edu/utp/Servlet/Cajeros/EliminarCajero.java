package pe.edu.utp.Servlet.Cajeros;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.UsuarioController;

import java.io.IOException;

@WebServlet("/EliminarCajero")
public class EliminarCajero extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idCajero = request.getParameter("id");

        UsuarioController usuarioController = new UsuarioController();
        boolean success = usuarioController.eliminarUsuarioCajero(Integer.parseInt(idCajero)); // Llamar al método de eliminar en el controller

        if (success) {
            response.sendRedirect("/listarCajeros"); // Redirigir a la página de administración de canchas
        } else {
            response.sendRedirect("/listarCajeros"); // Si hubo algún error, redirigir a una página de error
        }
    }
}
