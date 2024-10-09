package pe.edu.utp.Servlet.Canchas;

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
            throws IOException {

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

        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("<link rel='stylesheet' href='css/styles.css'>");
        out.println("<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>");
        out.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");

        // Include Font Awesome for icons
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css'>");

        out.println("<style>");
        out.println(".card-img-top {");
        out.println("    width: 100%;");
        out.println("    height: 250px;");
        out.println("    object-fit: cover;");
        out.println("}");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        // Header con logo y título
        out.println("<header class='bg-success text-white p-3 d-flex align-items-center'>");
        out.println("<img src='img/importante/logo.png' alt='Logo' class='logo mr-3'>");
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

        // Flex container for title and button alignment with added margin-top
        out.println("<div class='d-flex justify-content-between align-items-center mb-4 mt-4'>"); // Add 'mt-4' for top margin
        out.println("<h1 class='m-0'>Reservas</h1>"); // Remove default margin to align properly

        // Agregar botón para añadir canchas en la esquina derecha
        out.println("<a href='agregarCancha.html' class='btn btn-primary'>");
        out.println("<i class='fas fa-plus'></i> Agregar Cancha</a>");
        out.println("</div>");

        // Mostrar las canchas desde la base de datos
        out.println("<div class='row'>");

        for (Cancha cancha : canchas) {
            out.println("<div class='col-md-6 mb-4'>");
            out.println("<div class='card text-center'>");
            out.println("<h5 class='card-title'>Cancha " + cancha.getNroCancha() + "</h5>");
            out.println("<img src='img/canchasDeportivas/" + cancha.getImagenCancha() + "' class='card-img-top' alt='Cancha " + cancha.getNroCancha() + "'>");
            out.println("<div class='card-body'>");

            // Información adicional
            out.println("<div class='mb-3'>");
            out.println("<div class='d-flex justify-content-center'>");
            out.println("<p class='mr-4'>6 Jugadores</p>");
            out.println("<p>Grass Sintético</p>");
            out.println("</div>");
            out.println("<p class='text-center'>Iluminación Red</p>");
            out.println("</div>");

            // Flex container for prices
            out.println("<div class='d-flex justify-content-center mb-3'>");
            out.println("<p class='card-text mr-5'><strong>Precio Día:</strong> S/" + cancha.getPrecioDia() + "</p>");
            out.println("<p class='card-text'><strong>Precio Noche:</strong> S/" + cancha.getPrecioNoche() + "</p>");
            out.println("</div>");

            // Botón de actualizar
            out.println("<a href='actualizarCancha?id=" + cancha.getCanchaId() + "' class='btn btn-warning'>");
            out.println("<i class='fas fa-edit'></i> Actualizar</a>"); // Font Awesome edit icon

            // Botón de eliminar
            out.println("<form action='eliminarCancha?id=" + cancha.getCanchaId() + "' method='post' class='d-inline'>");
            out.println("<button type='submit' class='btn btn-danger ml-2'>");
            out.println("<i class='fas fa-trash'></i> Eliminar</button>"); // Font Awesome trash icon
            out.println("</form>");

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
