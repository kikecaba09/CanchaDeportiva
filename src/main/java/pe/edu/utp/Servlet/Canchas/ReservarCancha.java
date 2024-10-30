package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.DAO.ReservaDAO;
import pe.edu.utp.Model.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ReservarCancha")
public class ReservarCancha extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservaDAO reservarCanchaDAO;

    @Override
    public void init() {
        reservarCanchaDAO = new ReservaController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Generar el formulario HTML dinámicamente
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtener los parámetros de la URL
        String userIdParam = request.getParameter("user_id");
        String canchaIdParam = request.getParameter("cancha_id");

        out.println("<html>");
        out.println("<head><title>Reservar Cancha</title></head>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #f4f4f4;");
        out.println("    margin: 0;");
        out.println("    padding: 20px;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    height: 100vh;");
        out.println("}");
        out.println("h1 {");
        out.println("    color: #333;");
        out.println("    margin-bottom: 20px;");
        out.println("    text-align: center;"); // Título centrado
        out.println("}");
        out.println("form {");
        out.println("    background: white;");
        out.println("    padding: 20px;");
        out.println("    border-radius: 8px;");
        out.println("    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);");
        out.println("    display: flex;");
        out.println("    flex-direction: column;");
        out.println("    width: 400px;"); // Aumentando el ancho del formulario
        out.println("}");
        out.println("label {");
        out.println("    margin: 10px 0 5px;");
        out.println("}");
        out.println("input[type='text'], input[type='date'], input[type='time'], input[type='number'] {");
        out.println("    padding: 10px;");
        out.println("    border: 1px solid #ccc;");
        out.println("    border-radius: 4px;");
        out.println("    margin-bottom: 15px;");
        out.println("    transition: border 0.3s;");
        out.println("}");
        out.println("input[type='text']:focus, input[type='date']:focus, input[type='time']:focus, input[type='number']:focus {");
        out.println("    border-color: #007BFF;");
        out.println("    outline: none;");
        out.println("}");
        out.println("input[type='submit'] {");
        out.println("    background-color: #007BFF;");
        out.println("    color: white;");
        out.println("    border: none;");
        out.println("    border-radius: 4px;");
        out.println("    padding: 10px;");
        out.println("    cursor: pointer;");
        out.println("    font-size: 16px;");
        out.println("    transition: background-color 0.3s;");
        out.println("}");
        out.println("input[type='submit']:hover {");
        out.println("    background-color: #0056b3;");
        out.println("}");
        out.println("p {");
        out.println("    margin: 10px 0;");
        out.println("}");
        out.println("</style>");

        out.println("<body>");
        out.println("<form action='/ReservarCancha' method='post'>");

        // Usar los parámetros obtenidos en campos ocultos
        out.println("<input type='hidden' name='user_id' value='" + userIdParam + "' />");
        out.println("<input type='hidden' name='cancha_id' value='" + canchaIdParam + "' />");

        out.println("<label for='precio_reserva'>Precio:</label>");
        out.println("<input type='text' name='precio_reserva' required /><br/>");
        out.println("<label for='fecha_reserva'>Fecha (YYYY-MM-DD):</label>");
        out.println("<input type='date' name='fecha_reserva' required /><br/>");
        out.println("<label for='hora_inicio'>Hora Inicio:</label>");
        out.println("<input type='time' name='hora_inicio' required /><br/>");
        out.println("<label for='hora_fin'>Hora Fin:</label>");
        out.println("<input type='time' name='hora_fin' required /><br/>");
        out.println("<label for='estado_reserva'>Estado:</label>");
        out.println("<input type='text' name='estado_reserva' required /><br/>");
        out.println("<input type='submit' value='Reservar Cancha' />");
        out.println("</form>");

        // Mostrar mensajes de error si existen
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            out.println("<p style='color:red;'>" + errorMessage + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int canchaId = Integer.parseInt(request.getParameter("cancha_id"));
        double precioReserva = Double.parseDouble(request.getParameter("precio_reserva"));
        String fechaReserva = request.getParameter("fecha_reserva");
        String horaInicio = request.getParameter("hora_inicio");
        String horaFin = request.getParameter("hora_fin");
        String estadoReserva = request.getParameter("estado_reserva");

        // Crear un objeto Reserva
        Reserva reserva = new Reserva();
        reserva.setUserId(userId);
        reserva.setCanchaId(canchaId);
        reserva.setPrecioReserva(precioReserva);
        reserva.setFechaReserva(java.sql.Date.valueOf(fechaReserva));
        reserva.setHoraInicio(horaInicio);
        reserva.setHoraFin(horaFin);
        reserva.setEstadoReserva(estadoReserva);

        try {
            reservarCanchaDAO.reservarCancha(reserva);
            // Redirigir a una página de éxito o mostrar un mensaje
            response.sendRedirect("reservasExitosas.jsp");
        } catch (SQLException e) {
            // Manejar el error de forma adecuada
            request.setAttribute("errorMessage", e.getMessage());
            doGet(request, response); // Volver a mostrar el formulario con el mensaje de error
        }
    }
}
