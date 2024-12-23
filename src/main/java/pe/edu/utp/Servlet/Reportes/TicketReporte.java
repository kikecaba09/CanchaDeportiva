package pe.edu.utp.Servlet.Reportes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.CanchaController;
import pe.edu.utp.Controller.ClienteController;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.Model.Reserva;
import pe.edu.utp.Model.Cliente;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@WebServlet("/ticket")
public class TicketReporte extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservaController reservaDAO;
    private ClienteController clienteController;
    private CanchaController canchaController;

    public void init() {
        // Inicializar el DAO
        reservaDAO = new ReservaController();
        clienteController = new ClienteController();
        canchaController = new CanchaController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Obtener el ID del cliente de la URL (pasado como parámetro)
        String clienteIdStr = request.getParameter("cliente_id");


        if (clienteIdStr == null || clienteIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'clienteId' es obligatorio.");
            return;
        }

        try {
            // Convertir clienteId a un entero
            int clienteId = Integer.parseInt(clienteIdStr);

            // Obtener el cliente desde el ClienteController
            Cliente cliente = clienteController.obtenerClientePorId(clienteId);

            if (cliente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado.");
                return;
            }

            // Obtener las reservas del cliente desde el DAO
            List<Reserva> reservas = reservaDAO.listarReservasPorCliente(cliente);

            // Generar un número de ticket aleatorio
            String numeroTicket = generarNumeroTicket();

            // Generar un RUC aleatorio
            String numeroRUC = generarNumeroRUC();

            // Variables para calcular los totales
            double totalPrecio = 0.0;
            double totalPagado = 0.0;
            double deuda = 0.0;
            long totalHoras = 0;
            long totalMinutos=0;

            // Establecer el tipo de contenido de la respuesta (HTML)
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Generar el contenido HTML del reporte
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Reporte de Reservas - Ticket</title>");
            out.println("""
                        <style>
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background-color: #ecf0f1;
                            color: #333;
                            padding: 30px;
                            margin: 0;
                            box-sizing: border-box;
                        }
                        .ticket {
                            background-color: #fff;
                            padding: 30px;
                            border-radius: 8px;
                            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
                            max-width: 850px;
                            margin: 0 auto;
                            border: 2px solid #3498db;
                        }
                        .ticket h2 {
                            text-align: center;
                            color: #2c3e50;
                            font-size: 26px;
                            margin-bottom: 20px;
                        }
                        .ticket .info {
                            text-align: center;
                            font-size: 18px;
                            margin-bottom: 20px;
                        }
                        .ticket p {
                            font-size: 18px;
                            margin-bottom: 10px;
                            line-height: 1.6;
                        }
                        .ticket .footer {
                            text-align: center;
                            margin-top: 30px;
                            font-size: 14px;
                            color: #34495e;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: left;
                            border-bottom: 1px solid #ddd;
                        }
                        th {
                            background-color: #3498db;
                            color: white;
                        }
                        tr:hover {
                            background-color: #f2f2f2;
                        }
                        .totals {
                                background-color: #ecf0f1;
                                padding: 20px;
                                margin-top: 20px;
                                border-radius: 8px;
                                border: 1px solid #ddd;
                                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                                font-size: 18px;
                            }
                    
                            .totals-row {
                                display: flex;
                                justify-content: space-between; /* Alinea los elementos a los extremos */
                                margin-bottom: 10px;
                            }
                    
                            .totals-row p {
                                margin: 0;
                                font-weight: normal;
                                color: #333;
                                line-height: 1.6;
                            }
                    
                            .totals-row strong {
                                color: #2c3e50;
                            }
                    
                            .total-amount {
                                color: #e74c3c;
                                font-weight: bold;
                                text-align: right;
                                margin-left: auto;
                            }
                    
                            .totals p:last-child {
                                font-size: 20px;
                                font-weight: bold;
                                color: #3498db;
                            }
                            .export-links {
                                        display: flex; /* Esto coloca los elementos uno al lado del otro */
                                        gap: 10px; /* Espacio entre los enlaces */
                                        font-family: Arial, sans-serif; /* Fuente para que se vea bien */
                                    }
                                    .export-links a {
                                        text-decoration: none; /* Quita el subrayado */
                                        padding: 10px 20px; /* Agrega algo de padding */
                                        border-radius: 5px; /* Bordes redondeados */
                                        color: white; /* Color del texto */
                                        background-color: #007bff; /* Fondo azul */
                                        transition: background-color 0.3s; /* Efecto de transición */
                                    }
                                    .export-links a:hover {
                                        background-color: #0056b3; /* Cambio de color al pasar el ratón */
                                    }
                    </style>""");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Reporte de Reservas de Canchas </h1>");
            out.println("<div class='ticket'>");

            // Mostrar el título
            out.println("<h2>TICKET DE VENTA - SHUMPI</h2>");

            // Mostrar el número de ticket y RUC, centrados debajo del título
            out.println("<div class='info'>");
            out.println("<p><strong>T.E N°:</strong> " + numeroTicket + "</p>");
            out.println("<p><strong>RUC:</strong> " + numeroRUC + "</p>");
            out.println("</div>");

            // Mostrar los datos del cliente
            out.println("<p><strong>Nombre:</strong> " + cliente.getNombre() + " " + cliente.getApellido() + "</p>");
            out.println("<p><strong>DNI:</strong> " + cliente.getNroIdentidad() + "</p>");

            // Mostrar la fecha actual como la fecha de la reserva
            out.println("<p><strong>Fecha de creacion del Reporte:</strong> " + LocalDate.now() + "</p>");

            // Mostrar los detalles de las reservas
            if (reservas.isEmpty()) {
                out.println("<p>No hay reservas para este cliente.</p>");
            } else {
                out.println("<h3>Detalles de las Reservas</h3>");
                out.println("<table border='1' width='100%'>");
                out.println("<tr><th>Fecha</th><th>Hora</th><th>Cancha</th><th>Precio</th><th>Tiempo de Reserva</th><th>Estado de la reserva</th></tr>");
                // Crear un formateador de hora en formato de 12 horas con am/pm
                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("h:mm a");

                for (Reserva reserva : reservas) {
                    LocalTime horaInicio = LocalTime.parse(reserva.getHoraInicio());
                    LocalTime horaFin = LocalTime.parse(reserva.getHoraFin());

                    // Formatear las horas de inicio y fin
                    String horaInicioFormateada = horaInicio.format(formatoHora);
                    String horaFinFormateada = horaFin.format(formatoHora);

                    // Calcular las horas y minutos reservados
                    long horasReservadas = ChronoUnit.HOURS.between(horaInicio, horaFin);
                    long minutosReservados = ChronoUnit.MINUTES.between(horaInicio, horaFin) % 60;  // Solo minutos adicionales

                    // Verificar si el estado de la reserva no es "Cancelado"
                    if (!"Cancelado".equalsIgnoreCase(reserva.getEstadoReserva())) {
                        totalPrecio += reserva.getPrecioReserva();  // Sumar al importe total
                    }

                    // Verificar si el estado de la reserva es "Pagado"
                    if ("Pagado".equalsIgnoreCase(reserva.getEstadoReserva())) {
                        totalPagado += reserva.getPrecioReserva();  // Sumar al total pagado
                    }

                    // Sumar al total de horas y minutos
                    totalHoras += horasReservadas;
                    totalMinutos += minutosReservados;

                    // Ajustar si los minutos superan 60
                    if (totalMinutos >= 60) {
                        totalHoras += totalMinutos / 60;  // Agregar las horas completas
                        totalMinutos = totalMinutos % 60; // Dejar solo los minutos restantes
                    }

                    out.println("<tr>");
                    out.println("<td>" + reserva.getFechaReserva() + "</td>");
                    out.println("<td>" + horaInicioFormateada + " - " + horaFinFormateada + "</td>");
                    out.println("<td>" + reserva.getCanchaId() + "</td>");
                    out.println("<td>" + reserva.getPrecioReserva() + "</td>");
                    out.println("<td>" + horasReservadas + "h " + minutosReservados + "m</td>");
                    out.println("<td>" + reserva.getEstadoReserva() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }

            // Calcular la deuda
            deuda = totalPrecio - totalPagado;

            // Mostrar los totales
            out.println("<div class='totals'>");
            out.println("<div class='totals-row'>");
            out.println("<p><strong>IMPORTE TOTAL</strong></p>");
            out.println("<p><span class='total-amount'>" + totalPrecio + "</span></p>");
            out.println("</div>");
            out.println("<div class='totals-row'>");
            out.println("<p><strong>Total Pagado</strong></p>");
            out.println("<p><span class='total-amount'>" + totalPagado + "</span></p>");
            out.println("</div>");
            out.println("<div class='totals-row'>");
            out.println("<p><strong>Deuda a Pagar</strong></p>");
            out.println("<p><span class='total-amount'>" + deuda + "</span></p>");
            out.println("</div>");
            out.println("<div class='totals-row'>");
            out.println("<p><strong>Total de Horas Reservadas</strong></p>");
            out.println("<p><span class='total-amount'>" + totalHoras + " horas y " + totalMinutos + " minutos</span></p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='export-links'>");
            out.println("<a href='/exportarExcel?cliente_id=" + clienteId + "'>Exportar a Excel</a>");
            out.println("<a href='/exportarPdf?cliente_id=" + clienteId + "'>Exportar a PDF</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'clienteId' no es válido.");
        }
    }

    // Método para generar un número de ticket aleatorio
    private String generarNumeroTicket() {
        Random random = new Random();
        int ticketNumber = random.nextInt(100000, 999999);  // Genera un número aleatorio de 6 dígitos
        return String.valueOf(ticketNumber);
    }

    // Método para generar un RUC aleatorio de 11 dígitos
    private String generarNumeroRUC() {
        Random random = new Random();
        String tipoContribuyente = "10";  // Puede ser otro código si deseas generar diferentes tipos de RUC
        int digitosAleatorios = random.nextInt(100000000, 999999999);  // Números aleatorios de 9 dígitos
        return tipoContribuyente + digitosAleatorios;
    }
}


