package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pe.edu.utp.Controller.CanchaController;
import pe.edu.utp.Model.Cancha;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/agregarCancha")
public class AgregarCanchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Validar y obtener los parámetros
            String nroCanchaStr = request.getParameter("nro_cancha");
            String precioDiaStr = request.getParameter("precio_dia");
            String precioNocheStr = request.getParameter("precio_noche");
            String horaAbierto = request.getParameter("hora_abierto");
            String horaCerrado = request.getParameter("hora_cerrado");

            // Validar si los valores son correctos
            if (nroCanchaStr == null || precioDiaStr == null || precioNocheStr == null ||
                    horaAbierto == null || horaCerrado == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios.");
                return;
            }

            int nroCancha = Integer.parseInt(nroCanchaStr);
            double precioDia = Double.parseDouble(precioDiaStr);
            double precioNoche = Double.parseDouble(precioNocheStr);

            // Obtener la imagen de la cancha
            Part imagenPart = request.getPart("imagen_cancha");
            String imagenCancha = null;
            if (imagenPart != null && imagenPart.getSize() > 0) {
                // Guardar el archivo de imagen en el servidor y obtener la ruta
                String fileName = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("img/");
                File file = new File(uploadDir, fileName);
                imagenPart.write(file.getAbsolutePath());
                imagenCancha = file.getName(); // Usar el nombre del archivo guardado
            }

            // Crear la cancha y llamamos al DAO
            Cancha cancha = new Cancha();
            cancha.setNroCancha(nroCancha);
            cancha.setPrecioDia(precioDia);
            cancha.setPrecioNoche(precioNoche);
            cancha.setImagenCancha(imagenCancha);
            cancha.setHoraAbierto(horaAbierto);
            cancha.setHoraCerrado(horaCerrado);

            // Agregar la cancha a través del controlador
            CanchaController canchaController = new CanchaController();
            canchaController.agregarCancha(cancha);

            // Redirigir al usuario al listado de canchas
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
