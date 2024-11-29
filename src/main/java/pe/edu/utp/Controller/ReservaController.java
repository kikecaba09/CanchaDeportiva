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
    public String reservarCancha(String nombre, String apellido, String nroIdentidad, String telefono, String email,
                                 String fechaNacimiento, int canchaId, String horaInicio, String horaFin, String metodoPago,
                                 String fechaReserva) throws SQLException {

        Connection conexion = null;
        CallableStatement stmt = null;
        String resultado = "";

        try {
            // Obtener la conexión a la base de datos
            conexion = ConexionBD.obtenerConexion();

            // Llamar al procedimiento almacenado
            String sql = "{CALL ReservarCancha(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            stmt = conexion.prepareCall(sql);

            // Configurar los parámetros de entrada
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, nroIdentidad);
            stmt.setString(4, telefono);
            stmt.setString(5, email);
            stmt.setString(6, fechaNacimiento);
            stmt.setInt(7, canchaId);
            stmt.setString(8, horaInicio);
            stmt.setString(9, horaFin);
            stmt.setString(10, metodoPago);
            stmt.setString(11, fechaReserva);

            // Configurar el parámetro de salida
            stmt.registerOutParameter(12, java.sql.Types.VARCHAR);

            // Ejecutar el procedimiento
            stmt.execute();

            // Obtener el resultado del procedimiento
            resultado = stmt.getString(12);
        } finally {
            if (stmt != null) stmt.close();
            if (conexion != null) ConexionBD.cerrarConexion(conexion);
        }

        return resultado;
    }


    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT c.nombre, c.apellido, can.nro_cancha, r.reserva_id, r.precio_reserva, r.fecha_reserva, r.hora_inicio, r.hora_fin, r.estado_reserva " +
                "FROM Reserva r " +
                "JOIN Cliente c ON c.cliente_id = c.cliente_id " +
                "JOIN Cancha can ON r.cancha_id = can.cancha_id";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
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

    @Override
    public List<Reserva> listarReservasPorCliente(Cliente cliente) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT c.nombre, c.apellido, can.nro_cancha, r.reserva_id, r.precio_reserva, r.fecha_reserva, r.hora_inicio, r.hora_fin, r.estado_reserva " +
                "FROM Reserva r " +
                "JOIN Cliente c ON r.cliente_id = c.cliente_id " +
                "JOIN Cancha can ON r.cancha_id = can.cancha_id " +
                "WHERE c.cliente_id = ?";  // Filtra por cliente_id

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cliente.getClienteId()); // Suponiendo que el ID del cliente es un entero

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    @Override
    public List<Reserva> obtenerReservasPorRango(String fechaInicio, String fechaFin) {
        List<Reserva> reservas = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión a la base de datos utilizando ConexionBD
            conexion = ConexionBD.obtenerConexion();

            // Consulta SQL para obtener las reservas en el rango de fechas
            String query = "SELECT * FROM Reserva WHERE fecha_reserva BETWEEN ? AND ?";
            stmt = conexion.prepareStatement(query);
            stmt.setString(1, fechaInicio);
            stmt.setString(2, fechaFin);

            rs = stmt.executeQuery();
            while (rs.next()) {
                // Crear el objeto Reserva con los datos obtenidos de la consulta
                Reserva reserva = new Reserva(
                        rs.getInt("reserva_id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("cancha_id"),
                        rs.getDouble("precio_reserva"),
                        rs.getDate("fecha_reserva"),
                        rs.getString("hora_inicio"),
                        rs.getString("hora_fin"),
                        rs.getString("estado_reserva")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y otros recursos
            ConexionBD.cerrarConexion(conexion);
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reservas;
    }
}
