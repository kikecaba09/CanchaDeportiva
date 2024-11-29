package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.CanchaController;
import pe.edu.utp.DAO.CanchaDAO;
import pe.edu.utp.Model.Cancha;
import java.io.IOException;

@WebServlet("/modificarCancha")
public class ActualizarCanchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Obtener los parámetros del formulario como cadenas de texto
        String canchaIdStr = request.getParameter("cancha_id");
        String nroCanchaStr = request.getParameter("nro_cancha");
        String precioDiaStr = request.getParameter("precio_dia");
        String precioNocheStr = request.getParameter("precio_noche");
        String horaAbierto = request.getParameter("hora_abierto");
        String horaCerrado = request.getParameter("hora_cerrado");

        // Verificar que los valores no sean null y sean válidos
        if (canchaIdStr != null && nroCanchaStr != null && precioDiaStr != null && precioNocheStr != null) {
            // Convertir los valores a los tipos adecuados solo si es necesario
            try {
                int canchaId = Integer.parseInt(canchaIdStr); // Si es necesario, convertir a entero
                int nroCancha = Integer.parseInt(nroCanchaStr); // Si es necesario, convertir a entero
                double precioDia = Double.parseDouble(precioDiaStr); // Si es necesario, convertir a doble
                double precioNoche = Double.parseDouble(precioNocheStr); // Si es necesario, convertir a doble

                // Crear una instancia de la clase Cancha
                Cancha cancha = new Cancha();
                cancha.setCanchaId(canchaId);
                cancha.setNroCancha(nroCancha);
                cancha.setPrecioDia(precioDia);
                cancha.setPrecioNoche(precioNoche);
                cancha.setHoraAbierto(horaAbierto);
                cancha.setHoraCerrado(horaCerrado);

                // Llamar al método para modificar la cancha
                CanchaDAO canchaDAO = new CanchaController();
                canchaDAO.modificarCancha(cancha);

                // Redirigir a una página de confirmación
                response.sendRedirect("/administrarCancha");
            } catch (NumberFormatException e) {
                // Manejar el error si la conversión a número falla
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos para los precios o número de cancha.");
            }
        } else {
            // Manejar el caso si algún parámetro es nulo
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios.");
        }
    }
}


