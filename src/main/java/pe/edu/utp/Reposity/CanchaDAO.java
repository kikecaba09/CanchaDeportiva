package pe.edu.utp.Reposity;

import pe.edu.utp.Model.Cancha;
import java.util.List;

public interface CanchaDAO {

    void agregarCancha(Cancha cancha); // Cumple RF-01
    void modificarCancha(Cancha cancha); // Cumple RF-01
    void eliminarCancha(int canchaId); // Cumple RF-01
    List<Cancha> listarCanchas(); // Cumple RF-01
}
