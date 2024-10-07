package pe.edu.utp.Reposity;

import pe.edu.utp.Model.Cancha;
import pe.edu.utp.Model.Reserva;
import java.util.List;

public interface ReservaDAO {

    void realizarReserva(Reserva reserva);  // Cumple RF-04
    void registrarHoraDuracion(int reservaId, String horaInicio, int duracion);  // Cumple RF-05
    List<Reserva> listarReservas();  // Cumple RF-06
    void registrarMetodoPago(int reservaId, String metodoPago);  // Cumple RF-08
    List<Reserva> generarReporteReservas(String fechaInicio, String fechaFin);  // Cumple RF-03 (Generación de reportes de reservas)
    double calcularIngresos(String fechaInicio, String fechaFin);  // Cumple RF-03 (Generación de reportes de ingresos)
    List<Cancha> obtenerEstadisticasUso(String fechaInicio, String fechaFin);  // Cumple RF-03 (Estadísticas de uso de canchas)
}
