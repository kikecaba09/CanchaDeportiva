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
        // Obtener los parámetros del formulario
        int canchaId = Integer.parseInt(request.getParameter("canchaId"));
        int nroCancha = Integer.parseInt(request.getParameter("nroCancha"));
        double precioDia = Double.parseDouble(request.getParameter("precioDia"));
        double precioNoche = Double.parseDouble(request.getParameter("precioNoche"));
        String imagenCancha = request.getParameter("imagenCancha");
        String horaAbierto = request.getParameter("horaAbierto");
        String horaCerrado = request.getParameter("horaCerrado");
        String estado = request.getParameter("estado");  // Obtener el estado

        // Crear una instancia de la clase Cancha
        Cancha cancha = new Cancha();
        cancha.setCanchaId(canchaId);
        cancha.setNroCancha(nroCancha);
        cancha.setPrecioDia(precioDia);
        cancha.setPrecioNoche(precioNoche);
        cancha.setImagenCancha(imagenCancha);
        cancha.setHoraAbierto(horaAbierto);
        cancha.setHoraCerrado(horaCerrado);

        // Llamar al método para modificar la cancha
        CanchaDAO canchaDAO = new CanchaController();
        canchaDAO.modificarCancha(cancha);

        // Redirigir a una página de confirmación
        response.sendRedirect("cancha_modificada.jsp");
    }
}

