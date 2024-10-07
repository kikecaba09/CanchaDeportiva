package pe.edu.utp.Reposity;

import pe.edu.utp.Model.Pago;
import java.util.List;

public interface PagoDAO {

    void registrarPago(Pago pago); // Cumple RF-08
    List<Pago> listarPagos(); // Para generación de reportes
}
