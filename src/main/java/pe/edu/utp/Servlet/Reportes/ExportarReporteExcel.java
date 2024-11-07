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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@WebServlet("/exportarExcel")
public class ExportarReporteExcel extends HttpServlet {
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

            // Establecer el tipo de contenido y nombre del archivo Excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=reservas_cliente_" + clienteId + ".xlsx");

            // Crear un libro de Excel y una hoja
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Reservas");

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(0);
            String[] encabezados = {"Fecha", "Hora de Inicio", "Hora de Fin", "Cancha", "Precio", "Estado", "Tiempo de Reserva"};

            for (int i = 0; i < encabezados.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(encabezados[i]);
            }

            // Llenar los datos de las reservas
            int rowNum = 1;
            for (Reserva reserva : reservas) {
                Row row = sheet.createRow(rowNum++);

                // Calcular el tiempo de reserva
                LocalTime horaInicio = LocalTime.parse(reserva.getHoraInicio());
                LocalTime horaFin = LocalTime.parse(reserva.getHoraFin());

                long horasReservadas = ChronoUnit.HOURS.between(horaInicio, horaFin);
                long minutosReservados = ChronoUnit.MINUTES.between(horaInicio, horaFin) % 60;  // Solo minutos adicionales

                // Rellenar las celdas con los datos de la reserva
                row.createCell(0).setCellValue(reserva.getFechaReserva());
                row.createCell(1).setCellValue(reserva.getHoraInicio());
                row.createCell(2).setCellValue(reserva.getHoraFin());
                row.createCell(3).setCellValue(reserva.getCanchaId());
                row.createCell(4).setCellValue(reserva.getPrecioReserva());
                row.createCell(5).setCellValue(reserva.getEstadoReserva());
                row.createCell(6).setCellValue(horasReservadas + "h " + minutosReservados + "m");
            }

            // Escribir el archivo Excel en la respuesta
            try (OutputStream out = response.getOutputStream()) {
                workbook.write(out);
            }

            // Cerrar el libro de trabajo
            workbook.close();

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'cliente_id' no es válido.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el archivo Excel.");
        }
    }
}
