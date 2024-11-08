package pe.edu.utp.Servlet.Reservar;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.DAO.ReservaDAO;
import pe.edu.utp.Model.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/calendario")
public class CalendarioReserva extends HttpServlet {
    private ReservaDAO reservaDAO = new ReservaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Reserva> reservas = reservaDAO.obtenerTodasLasReservas();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Convertir la lista de reservas a formato JSON
        out.print("[");

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            String color = "#007bff"; // Azul para pendiente por defecto

            // Cambiar el color según el estado de la reserva
            if ("confirmada".equalsIgnoreCase(reserva.getEstadoReserva())) {
                color = "#28a745";  // Verde para confirmada
            } else if ("cancelada".equalsIgnoreCase(reserva.getEstadoReserva())) {
                color = "#dc3545";  // Rojo para cancelada
            } else if ("pendiente".equalsIgnoreCase(reserva.getEstadoReserva())) {
                color = "#007bff";  // Azul para pendiente
            }

            // Enviar los datos en formato adecuado para FullCalendar
            out.print("{");
            out.print("\"title\":\"Reserva\",");

            // Se ajusta el formato de las fechas
            out.print("\"start\":\"" + reserva.getFechaReserva().toString() + "T" + reserva.getHoraInicio().toString() + "\"," );
            out.print("\"end\":\"" + reserva.getFechaReserva().toString() + "T" + reserva.getHoraFin().toString() + "\",");

            // Agregar el color dinámico
            out.print("\"backgroundColor\":\"" + color + "\",");
            // Agregar el estado de la reserva al JSON
            out.print("\"status\":\"" + reserva.getEstadoReserva() + "\",");
            out.print("\"borderColor\":\"" + color + "\"");
            out.print("}");

            if (i < reservas.size() - 1) {
                out.print(",");
            }
        }

        out.print("]");
        out.flush();
    }
}
