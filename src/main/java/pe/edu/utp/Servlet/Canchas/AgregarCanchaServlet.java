package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.CanchaController;
import pe.edu.utp.Model.Cancha;
import java.io.IOException;

@WebServlet("/agregarCancha")
public class AgregarCanchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            // Validar y obtener los parámetros
            String nroCanchaStr = request.getParameter("nro_cancha");
            String precioDiaStr = request.getParameter("precio_dia");
            String precioNocheStr = request.getParameter("precio_noche");
            String imagenCancha = request.getParameter("imagen_cancha");
            String horaAbierto = request.getParameter("hora_abierto");
            String horaCerrado = request.getParameter("hora_cerrado");


            int nroCancha = Integer.parseInt(nroCanchaStr);
            double precioDia = Double.parseDouble(precioDiaStr);
            double precioNoche = Double.parseDouble(precioNocheStr);

            // Crear la cancha y llamamos al DAO
            Cancha cancha = new Cancha();
            cancha.setNroCancha(nroCancha);
            cancha.setPrecioDia(precioDia);
            cancha.setPrecioNoche(precioNoche);
            cancha.setImagenCancha(imagenCancha);
            cancha.setHoraAbierto(horaAbierto);
            cancha.setHoraCerrado(horaCerrado);

            CanchaController canchaController = new CanchaController();
            canchaController.agregarCancha(cancha);

            // Redirigir al usuario
            response.sendRedirect("/administrarCancha");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: formato de número inválido.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al agregar la cancha: " + e.getMessage());
        }
    }
}
