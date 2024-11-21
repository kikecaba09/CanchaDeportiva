package pe.edu.utp.Controller;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Cancha;
import pe.edu.utp.DAO.CanchaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CanchaController implements CanchaDAO {
    @Override
    public void agregarCancha(Cancha cancha) {
        String sql = "INSERT INTO Cancha (nro_cancha, precio_dia, precio_noche, imagen_cancha, hora_abierto, hora_cerrado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, cancha.getNroCancha());
            preparedStatement.setDouble(2, cancha.getPrecioDia());
            preparedStatement.setDouble(3, cancha.getPrecioNoche());
            preparedStatement.setString(4, cancha.getImagenCancha());
            preparedStatement.setString(5, cancha.getHoraAbierto());
            preparedStatement.setString(6, cancha.getHoraCerrado());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cancha agregada exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar la cancha: " + e.getMessage());
        }
    }

    @Override
    public void modificarCancha(Cancha cancha) {
        String sql = "UPDATE Cancha SET nro_cancha = ?, precio_dia = ?, precio_noche = ?, imagen_cancha = ?, hora_abierto = ?, hora_cerrado = ? WHERE cancha_id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, cancha.getNroCancha());
            preparedStatement.setDouble(2, cancha.getPrecioDia());
            preparedStatement.setDouble(3, cancha.getPrecioNoche());
            preparedStatement.setString(4, cancha.getImagenCancha());
            preparedStatement.setString(5, cancha.getHoraAbierto());
            preparedStatement.setString(6, cancha.getHoraCerrado());
            preparedStatement.setInt(7, cancha.getCanchaId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cancha modificada exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar la cancha: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarCancha(int canchaId) {
        String sql = "DELETE FROM Cancha WHERE cancha_id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, canchaId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cancha eliminada exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar la cancha: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Cancha> listarCanchas() {
        List<Cancha> canchas = new ArrayList<>();
        String sql = "SELECT cancha_id, nro_cancha, precio_dia, precio_noche, imagen_cancha, hora_abierto, hora_cerrado FROM Cancha";

        Connection conexion = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cancha cancha = new Cancha();
                cancha.setCanchaId(resultSet.getInt("cancha_id"));
                cancha.setNroCancha(resultSet.getInt("nro_cancha"));
                cancha.setPrecioDia(resultSet.getDouble("precio_dia"));
                cancha.setPrecioNoche(resultSet.getDouble("precio_noche"));
                cancha.setImagenCancha(resultSet.getString("imagen_cancha"));
                cancha.setHoraAbierto(resultSet.getString("hora_abierto"));
                cancha.setHoraCerrado(resultSet.getString("hora_cerrado"));
                canchas.add(cancha);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las canchas: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion(conexion); // Cerrar conexión
        }

        return canchas;
    }

    @Override
    public Cancha obtenerCanchaPorId(int idCancha) {
        Cancha cancha = null;
        String query = "SELECT * FROM Cancha WHERE cancha_id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            preparedStatement.setInt(1, idCancha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cancha = new Cancha();
                cancha.setCanchaId(resultSet.getInt("cancha_id"));
                cancha.setNroCancha(resultSet.getInt("nro_cancha"));
                cancha.setPrecioDia(resultSet.getDouble("precio_dia"));
                cancha.setPrecioNoche(resultSet.getDouble("precio_noche"));
                cancha.setImagenCancha(resultSet.getString("imagen_cancha"));
                cancha.setHoraAbierto(resultSet.getString("hora_abierto"));
                cancha.setHoraCerrado(resultSet.getString("hora_cerrado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cancha;
    }
}
