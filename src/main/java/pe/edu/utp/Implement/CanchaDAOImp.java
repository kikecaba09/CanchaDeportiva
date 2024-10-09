package pe.edu.utp.Implement;

import pe.edu.utp.Ejecucion.ConexionBD;
import pe.edu.utp.Model.Cancha;
import pe.edu.utp.Reposity.CanchaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CanchaDAOImp implements CanchaDAO {
    @Override
    public void agregarCancha(Cancha cancha) {

    }

    @Override
    public void modificarCancha(Cancha cancha) {

    }

    @Override
    public void eliminarCancha(int canchaId) {

    }

    @Override
    public List<Cancha> listarCanchas() {
        List<Cancha> canchas = new ArrayList<>();
        String sql = "SELECT cancha_id, nro_cancha, precio_dia, precio_noche, imagen_cancha FROM Cancha"; // Ajusta la consulta según sea necesario

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
                canchas.add(cancha);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las canchas: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion(conexion); // Cerrar conexión
        }

        return canchas;
    }
}
