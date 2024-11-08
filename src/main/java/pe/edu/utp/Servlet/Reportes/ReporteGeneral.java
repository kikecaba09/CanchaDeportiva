package pe.edu.utp.Servlet.Reportes;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ReservaController;
import pe.edu.utp.DAO.ReservaDAO;
import pe.edu.utp.Model.Reserva;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/reporteGeneral")
public class ReporteGeneral extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener las fechas del formulario (solo como valores de rango)
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        ReservaDAO reservaDAO = new ReservaController();
        // Filtrar las reservas basadas en el rango de fechas
        List<Reserva> reservas = reservaDAO.obtenerReservasPorRango(fechaInicio, fechaFin);

        // Generar los datos para los gráficos
        Map<String, Object> graficoData = generarGrafico(reservas);

        // Enviar la respuesta en formato JSON
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(graficoData));
    }

    // Generar datos para los gráficos
    private Map<String, Object> generarGrafico(List<Reserva> reservas) {
        // Listas para los datos de los gráficos
        List<String> labelsMeses = List.of("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        List<Integer> barrasData = new ArrayList<>(Collections.nCopies(12, 0));
        List<String> labelsDiasSemana = List.of("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
        List<Integer> linealData = new ArrayList<>(Collections.nCopies(7, 0));

        // Procesar las reservas
        Calendar calendario = Calendar.getInstance();

        for (Reserva reserva : reservas) {
            calendario.setTime(reserva.getFechaReserva());

            // Contar las reservas por mes
            int mes = calendario.get(Calendar.MONTH); // Enero es 0, Diciembre es 11
            barrasData.set(mes, barrasData.get(mes) + 1);

            // Contar las reservas por día de la semana
            int diaSemana = calendario.get(Calendar.DAY_OF_WEEK); // Domingo es 1, Sábado es 7
            int indiceDia = (diaSemana == Calendar.SUNDAY) ? 6 : (diaSemana - 2); // Ajustar el índice: Lunes es 0, Domingo es 6
            linealData.set(indiceDia, linealData.get(indiceDia) + 1);
        }

        // Preparar los datos para Chart.js
        Map<String, Object> graficoData = new HashMap<>();

        // Gráfico de barras (reservas por mes)
        Map<String, Object> barras = new HashMap<>();
        barras.put("labels", labelsMeses);
        barras.put("data", barrasData);

        // Gráfico lineal (reservas por día de la semana)
        Map<String, Object> lineal = new HashMap<>();
        lineal.put("labels", labelsDiasSemana);
        lineal.put("data", linealData);

        graficoData.put("barras", barras);
        graficoData.put("lineal", lineal);

        return graficoData;
    }
}
