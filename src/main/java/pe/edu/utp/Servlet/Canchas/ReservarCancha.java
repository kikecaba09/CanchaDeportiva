package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.DAO.ReservaDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reservarCancha")
public class ReservarCancha extends HttpServlet {

    private ReservaDAO reservaDAO = new ReservaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nroIdentidad = request.getParameter("nro_identidad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fecha_nacimiento");
        String canchaIdStr = request.getParameter("cancha_id");

        // Validar que cancha_id no esté vacío o nulo
        if (canchaIdStr == null || canchaIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID de la cancha es necesario.");
            return;
        }

        try {
            int canchaId = Integer.parseInt(canchaIdStr); // Intentar convertir a entero
            String horaInicio = request.getParameter("hora_inicio");
            String horaFin = request.getParameter("hora_fin");

            try {
                // Llamar al método del DAO
                String resultado = reservaDAO.reservarCancha(nombre, apellido, nroIdentidad, telefono, email, fechaNacimiento, canchaId, horaInicio, horaFin);

                // Enviar resultado al cliente
                response.getWriter().println("<p>" + resultado + "</p>");
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("<p>Error al realizar la reserva: " + e.getMessage() + "</p>");
            }
        } catch (NumberFormatException e) {
            // Si la conversión de cancha_id falla, enviar un error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de cancha inválido.");
        }
    }
}
