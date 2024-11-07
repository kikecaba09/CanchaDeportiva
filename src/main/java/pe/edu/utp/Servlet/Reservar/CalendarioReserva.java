package pe.edu.utp.Servlet.Reservar;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.DAO.ReservaDAO;
import pe.edu.utp.Model.Cancha;
import pe.edu.utp.Model.Cliente;
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

        out.print("[");

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            Cliente cliente = new Cliente();  // Obteniendo el cliente relacionado
            cliente.getNombre();
            cliente.getApellido();
            Cancha cancha = new Cancha();    // Obteniendo la cancha relacionada
            cancha.getNroCancha();
            String color = "#007bff";

            if ("confirmada".equalsIgnoreCase(reserva.getEstadoReserva())) {
                color = "#28a745";  // Verde para confirmada
            } else if ("cancelada".equalsIgnoreCase(reserva.getEstadoReserva())) {
                color = "#dc3545";  // Rojo para cancelada
            } else if ("pendiente".equalsIgnoreCase(reserva.getEstadoReserva())) {
                color = "#007bff";  // Azul para pendiente
            }

            out.print("{");
            out.print("\"title\":\"Reserva\",");

            out.print("\"start\":\"" + reserva.getFechaReserva().toString() + "T" + reserva.getHoraInicio().toString() + "\",");
            out.print("\"end\":\"" + reserva.getFechaReserva().toString() + "T" + reserva.getHoraFin().toString() + "\",");

            out.print("\"backgroundColor\":\"" + color + "\",");
            out.print("\"borderColor\":\"" + color + "\",");

            // Incluyendo los datos adicionales
            out.print("\"nombre\":\"" + cliente.getNombre() + "\",");
            out.print("\"apellido\":\"" + cliente.getApellido() + "\",");
            out.print("\"nro_cancha\":\"" + cancha.getNroCancha() + "\",");
            out.print("\"horaInicio\":\"" + reserva.getHoraInicio() + "\",");
            out.print("\"horaFin\":\"" + reserva.getHoraFin() + "\",");
            out.print("\"estadoReserva\":\"" + reserva.getEstadoReserva() + "\"");

            out.print("}");

            if (i < reservas.size() - 1) {
                out.print(",");
            }
        }

        out.print("]");
        out.flush();
    }
}
