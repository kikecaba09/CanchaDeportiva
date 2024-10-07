package pe.edu.utp.Implement;

import pe.edu.utp.Model.Cancha;
import pe.edu.utp.Model.Reserva;
import pe.edu.utp.Reposity.ReservaDAO;
import java.util.List;

public class ReservaDAOImp implements ReservaDAO {

    @Override
    public void realizarReserva(Reserva reserva) {

    }

    @Override
    public void registrarHoraDuracion(int reservaId, String horaInicio, int duracion) {

    }

    @Override
    public List<Reserva> listarReservas() {
        return List.of();
    }

    @Override
    public void registrarMetodoPago(int reservaId, String metodoPago) {

    }

    @Override
    public List<Reserva> generarReporteReservas(String fechaInicio, String fechaFin) {
        return List.of();
    }

    @Override
    public double calcularIngresos(String fechaInicio, String fechaFin) {
        return 0;
    }

    @Override
    public List<Cancha> obtenerEstadisticasUso(String fechaInicio, String fechaFin) {
        return List.of();
    }
}
