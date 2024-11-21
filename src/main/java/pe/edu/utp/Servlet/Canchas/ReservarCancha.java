package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.DAO.ReservaDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
            // Llamar al método del DAO para realizar la reserva
            String resultado = reservaDAO.reservarCancha(nombre, apellido, nroIdentidad, telefono, email, fechaNacimiento, canchaId, horaInicio, horaFin, metodoPago);

            // Generar el contenido del reporte en formato texto
            String reporte = "==========================================================\n";
            reporte += "                     REPORTE DE RESERVA                 \n";
            reporte += "==========================================================\n\n";
            reporte += "                Información del Cliente\n";
            reporte += "----------------------------------------------------------\n";
            reporte += "Nombre Completo: " + nombre + " " + apellido + "\n";
            reporte += "Número de Identidad: " + nroIdentidad + "\n";
            reporte += "Teléfono: " + telefono + "\n";
            reporte += "Email: " + email + "\n";
            reporte += "Fecha de Nacimiento: " + fechaNacimiento + "\n";
            reporte += "----------------------------------------------------------\n\n";

            reporte += "                Detalles de la Reserva\n";
            reporte += "----------------------------------------------------------\n";
            reporte += "Cancha ID: " + canchaId + "\n";
            reporte += "Hora de Inicio: " + horaInicio + "\n";
            reporte += "Hora de Fin: " + horaFin + "\n";
            reporte += "Método de Pago: " + metodoPago + "\n";
            reporte += "----------------------------------------------------------\n\n";

            reporte += "               Estado de la Reserva\n";
            reporte += "----------------------------------------------------------\n";
            reporte += "La reserva se ha realizado con éxito.\n";
            reporte += "----------------------------------------------------------\n\n";

            reporte += "                   ¡Gracias por reservar con nosotros!\n";
            reporte += "==========================================================\n";

            // Configurar la respuesta para descargar el archivo .txt
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=reporte_reserva.txt");

            // Enviar el contenido al cliente
            PrintWriter out = response.getWriter();
            out.print(reporte);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al realizar la reserva: " + e.getMessage());
        }
    }
}

