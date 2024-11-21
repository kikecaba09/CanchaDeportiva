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
        int canchaId = Integer.parseInt(request.getParameter("cancha_id"));
        String horaInicio = request.getParameter("hora_inicio");
        String horaFin = request.getParameter("hora_fin");
        String metodoPago = request.getParameter("metodo_pago");

        try {
            // Llamar al método del DAO
            String resultado = reservaDAO.reservarCancha(nombre, apellido, nroIdentidad, telefono, email, fechaNacimiento, canchaId, horaInicio, horaFin, metodoPago);

            // Enviar resultado al cliente
            response.getWriter().println("<p>" + resultado + "</p>");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error al realizar la reserva: " + e.getMessage() + "</p>");
        }
    }
}
