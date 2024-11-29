package pe.edu.utp.DAO;

import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Reserva;
import java.sql.SQLException;
import java.util.List;

public interface ReservaDAO {

    void realizarReserva(Reserva reserva); // Cumple RF-04
    void registrarHoraDuracion(int reservaId, String horaInicio, int duracion); // Cumple RF-05
    List<Reserva> listarReservas(); // Cumple RF-06
    void validarDisponibilidad(int canchaId, String fecha, String horaInicio, int duracion); // Cumple RF-07

    String reservarCancha(String nombre, String apellido, String nroIdentidad, String telefono, String email,
                          String fechaNacimiento, int canchaId, String horaInicio, String horaFin, String metodoPago,
                          String fechaReserva) throws SQLException;

    List<Reserva> obtenerTodasLasReservas();
    List<Reserva> listarReservasPorCliente(Cliente cliente);

    List<Reserva> obtenerReservasPorRango(String fechaInicio, String fechaFin);
}
