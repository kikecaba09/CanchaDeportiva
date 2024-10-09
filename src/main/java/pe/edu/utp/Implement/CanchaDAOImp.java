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
        return List.of();
    }

}
