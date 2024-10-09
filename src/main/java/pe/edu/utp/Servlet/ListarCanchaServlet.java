package pe.edu.utp.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.CanchaDAOImp;
import pe.edu.utp.Model.Cancha;
import pe.edu.utp.Reposity.CanchaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarCanchas")
public class ListarCanchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Crear instancia de CanchaDAO
        CanchaDAO canchaDAO = new CanchaDAOImp();
        List<Cancha> canchas = canchaDAO.listarCanchas();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>SHUMPI Soccer Club - Reservas</title>");

        // Incluir Bootstrap, CSS personalizado y JS
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("<link rel='stylesheet' href='css/styles.css'>");
        out.println("<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>");
        out.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");
        out.println("</head>");
        out.println("<body>");

        // Header con logo y título
        out.println("<header class='bg-success text-white p-3 d-flex align-items-center'>");
        out.println("<img src='img/logo.png' alt='Logo' class='logo mr-3'>");
        out.println("<h1 class='m-0' style='font-size: 1.8rem;'>SHUMPI Soccer Club</h1>");
        out.println("<button class='navbar-toggler ml-auto' type='button' data-toggle='collapse' data-target='#navbarNav'>");
        out.println("<span class='navbar-toggler-icon'></span>");
        out.println("</button>");
        out.println("</header>");

        // Contenedor principal
        out.println("<div class='container-fluid'>");
        out.println("<div class='row'>");

        // Menú de navegación en la izquierda
        out.println("<nav class='col-md-2 bg-light sidebar'>");
        out.println("<div class='sidebar-sticky'>");
        out.println("<ul class='nav flex-column'>");
        out.println("<li class='nav-item'><a class='nav-link' href='#'>Reservar</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='#'>Canchas disponibles</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='#'>Calendario disponibilidad</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='#'>Configuración</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='#'>Reportes</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='#'>Cerrar sesión</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</nav>");

        // Contenido principal
        out.println("<main role='main' class='col-md-10 ml-sm-auto px-4'>");
        out.println("<h2>Reservas</h2>");

        // Mostrar las canchas desde la base de datos
        out.println("<div class='row'>");

        for (Cancha cancha : canchas) {
            out.println("<div class='col-md-6 mb-4'>");
            out.println("<div class='card'>");
            out.println("<h5 class='card-title'>Cancha" + cancha.getNroCancha() + "</h5>");
            out.println("<img src='img/cancha" + cancha.getImagenCancha() + "class='card-img-top' alt='Cancha " + cancha.getNroCancha() + "'>");
            out.println("<div class='card-body'>");
            out.println("<p class='card-text'><strong>Precio Día:</strong> S/" + cancha.getPrecioDia() + "<br><strong>Precio Noche:</strong> S/" + cancha.getPrecioNoche() + "</p>");
            out.println("<a href='#' class='btn btn-success'>Reservar</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
        }

        out.println("</div>");
        out.println("</main>");
        out.println("</div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
