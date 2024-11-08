package pe.edu.utp.Servlet.Reportes;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@WebServlet("/exportarPdf")
public class ExportarReportePDF extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservaController reservaDAO;
    private ClienteController clienteController;
    private CanchaController canchaController;

    public void init() {
        // Inicializar los controladores
        reservaDAO = new ReservaController();
        clienteController = new ClienteController();
        canchaController = new CanchaController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Obtener el ID del cliente de la URL
        String clienteIdStr = request.getParameter("cliente_id");

        if (clienteIdStr == null || clienteIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'cliente_id' es obligatorio.");
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

            if (reservas.isEmpty()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No hay reservas para este cliente.");
                return;
            }

            // Establecer el tipo de contenido para PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ticket_cliente_" + clienteId + ".pdf");

            // Crear un documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            // Abrir el documento
            document.open();

            // Título del documento
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Reservas del Cliente", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Espaciado
            document.add(Chunk.NEWLINE);

            // Información del cliente
            Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            document.add(new Paragraph("Cliente: " + cliente.getNombre() + " " + cliente.getApellido(), subtitleFont));
            document.add(new Paragraph("DNI: " + cliente.getNroIdentidad()));
            document.add(new Paragraph("Fecha de creación del reporte: " + java.time.LocalDate.now()));

            // Espaciado
            document.add(Chunk.NEWLINE);

            // Tabla de reservas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            table.addCell("Hora de Inicio");
            table.addCell("Hora de Fin");
            table.addCell("Cancha");
            table.addCell("Precio");
            table.addCell("Estado");

            double totalPrecio = 0.0;
            long totalHoras = 0;
            long totalMinutos = 0;

            // Agregar las filas de reservas a la tabla
            for (Reserva reserva : reservas) {
                LocalTime horaInicio = LocalTime.parse(reserva.getHoraInicio());
                LocalTime horaFin = LocalTime.parse(reserva.getHoraFin());

                // Calcular las horas y minutos de la reserva
                long horasReservadas = ChronoUnit.HOURS.between(horaInicio, horaFin);
                long minutosReservados = ChronoUnit.MINUTES.between(horaInicio, horaFin) % 60;

                // Añadir los datos de cada reserva a la tabla
                table.addCell(reserva.getHoraInicio());
                table.addCell(reserva.getHoraFin());
                table.addCell(String.valueOf(reserva.getCanchaId()));
                table.addCell(String.valueOf(reserva.getPrecioReserva()));
                table.addCell(reserva.getEstadoReserva());

                // Sumar los totales
                if ("Pagado".equalsIgnoreCase(reserva.getEstadoReserva())) {
                    totalPrecio += reserva.getPrecioReserva();
                }

                totalHoras += horasReservadas;
                totalMinutos += minutosReservados;

                // Ajustar si los minutos superan 60
                if (totalMinutos >= 60) {
                    totalHoras += totalMinutos / 60;
                    totalMinutos = totalMinutos % 60;
                }
            }

            // Agregar la tabla al documento
            document.add(table);

            // Espaciado
            document.add(Chunk.NEWLINE);

            // Totales al final del documento
            document.add(new Paragraph("Total de Horas Reservadas: " + totalHoras + " horas y " + totalMinutos + " minutos"));
            document.add(new Paragraph("IMPORTE TOTAL: s/ " + totalPrecio));

            // Cerrar el documento
            document.close();

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'cliente_id' no es válido.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el archivo PDF.");
        }
    }
}