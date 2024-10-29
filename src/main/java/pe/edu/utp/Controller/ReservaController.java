package pe.edu.utp.Controller;

import pe.edu.utp.Model.Reserva;
import pe.edu.utp.Reposity.ReservaDAO;
import java.util.List;

public class ReservaController implements ReservaDAO {

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
    public void validarDisponibilidad(int canchaId, String fecha, String horaInicio, int duracion) {

    }
}
