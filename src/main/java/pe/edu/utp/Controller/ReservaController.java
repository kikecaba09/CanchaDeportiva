package pe.edu.utp.Controller;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Cancha;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Reserva;
import pe.edu.utp.DAO.ReservaDAO;
import java.sql.*;
import java.util.ArrayList;
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

    @Override
    public void reservarCancha(Reserva reserva) throws SQLException {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = ConexionBD.obtenerConexion();
            cstmt = conn.prepareCall("{CALL ReservarCancha(?, ?, ?, ?, ?, ?, ?)}");
            cstmt.setInt(1, reserva.getUserId());
            cstmt.setInt(2, reserva.getCanchaId());
            cstmt.setDouble(3, reserva.getPrecioReserva());
            cstmt.setDate(4, new java.sql.Date(reserva.getFechaReserva().getTime()));
            cstmt.setTime(5, java.sql.Time.valueOf(reserva.getHoraInicio()));
            cstmt.setTime(6, java.sql.Time.valueOf(reserva.getHoraFin()));
            cstmt.setString(7, reserva.getEstadoReserva());
            cstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al reservar la cancha: " + e.getMessage(), e);
        } finally {
            if (cstmt != null) cstmt.close();
            if (conn != null) conn.close();
        }
    }

    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT c.nombre, c.apellido, can.nro_cancha, r.reserva_id, r.precio_reserva, r.fecha_reserva, r.hora_inicio, r.hora_fin, r.estado_reserva " +
                "FROM Reserva r " +
                "JOIN Cliente c ON r.user_id = c.cliente_id " +
                "JOIN Cancha can ON r.cancha_id = can.cancha_id";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                Cancha cancha = new Cancha();
                cancha.setNroCancha(rs.getInt("nro_cancha"));
                Reserva reserva = new Reserva();
                reserva.setReservaId(rs.getInt("reserva_id"));
                reserva.setPrecioReserva(rs.getDouble("precio_reserva"));
                reserva.setFechaReserva(rs.getDate("fecha_reserva"));
                reserva.setHoraInicio(rs.getString("hora_inicio"));
                reserva.setHoraFin(rs.getString("hora_fin"));
                reserva.setEstadoReserva(rs.getString("estado_reserva"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}
