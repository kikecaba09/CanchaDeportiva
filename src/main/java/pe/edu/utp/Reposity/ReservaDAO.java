package pe.edu.utp.Reposity;

import pe.edu.utp.Model.Reserva;
import java.util.List;

public interface ReservaDAO {

    void realizarReserva(Reserva reserva); // Cumple RF-04
    void registrarHoraDuracion(int reservaId, String horaInicio, int duracion); // Cumple RF-05
    List<Reserva> listarReservas(); // Cumple RF-06
    void validarDisponibilidad(int canchaId, String fecha, String horaInicio, int duracion); // Cumple RF-07
}
