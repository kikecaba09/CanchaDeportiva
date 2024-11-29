package pe.edu.utp.Servlet.Canchas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.CanchaController;
import pe.edu.utp.Model.Cancha;
import pe.edu.utp.DAO.CanchaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/administrarCancha")
public class AdministrarCanchasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        CanchaDAO canchaDAO = new CanchaController();
        List<Cancha> canchas = canchaDAO.listarCanchas();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>SHUMPI Soccer Club - Reservas</title>");
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>");
        out.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css'>");

        // Estilos adicionales para la barra lateral
        out.println("<style>");
        out.println("nav.sidebar {");
        out.println("    box-shadow: 2px 0 10px rgba(0,0,0,0.2);");
        out.println("    transition: background-color 0.3s, width 0.3s;");
        out.println("    width: 250px;");
        out.println("    background-color: #dff0d8;");
        out.println("    height: 100vh;"); // Altura completa para la barra lateral
        out.println("    position: fixed;"); // Fijar la posición de la barra lateral
        out.println("}");
        out.println("nav.sidebar header {");
        out.println("    background-color: #4CAF50;");
        out.println("    padding: 15px;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("nav.sidebar .logo {");
        out.println("    max-width: 60%;"); // Ajustar el tamaño del logo
        out.println("    height: auto;"); // Mantener la relación de aspecto
        out.println("}");
        out.println("nav.sidebar .nav-link {");
        out.println("    color: #333;");
        out.println("    padding: 15px 20px;");
        out.println("    border-radius: 5px;");
        out.println("    display: flex;"); // Alinear elementos horizontalmente
        out.println("    align-items: center;"); // Centrar iconos y texto
        out.println("    transition: background-color 0.3s, color 0.3s;");
        out.println("}");
        out.println("nav.sidebar .nav-link:hover {");
        out.println("    background-color: #c8e6c9;");
        out.println("    color: #007bff;");
        out.println("}");
        out.println("nav.sidebar .nav-item {");
        out.println("    margin-bottom: 1rem;");
        out.println("}");
        out.println(".submenu { display: none; padding-left: 20px; }");
        out.println(".submenu .nav-link {");
        out.println("    background-color: #f9f9f9;");
        out.println("    color: #555;");
        out.println("}");
        out.println(".submenu .nav-link:hover {");
        out.println("    background-color: #e0e0e0;");
        out.println("}");
        out.println("nav.sidebar .nav-link i {");
        out.println("    margin-right: 10px;");
        out.println("}");
        out.println(".card:hover {");
        out.println("    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);");
        out.println("    transform: translateY(-5px);");
        out.println("    transition: transform 0.2s, box-shadow 0.2s;");
        out.println("}");
        out.println(".card-img-top {");
        out.println("    height: 300px;");
        out.println("    object-fit: cover;");
        out.println("}");
        out.println(".btn-reservar {");
        out.println("    background-color: #28a745;");
        out.println("    color: #fff;");
        out.println("    font-size: 1rem;");
        out.println("    font-weight: bold;");
        out.println("    padding: 13px 40px;");
        out.println("    border: none;");
        out.println("    border-radius: 40px;");
        out.println("    display: inline-flex;");
        out.println("    align-items: center;");
        out.println("    transition: background-color 0.3s, transform 0.2s;");
        out.println("}");
        out.println(".btn-reservar:hover {");
        out.println("    background-color: #218838;");
        out.println("    transform: scale(1.05);");
        out.println("}");
        out.println(".btn-reservar i {");
        out.println("    margin-right: 8px;");
        out.println("    font-size: 1.2rem;");
        out.println("}");
        out.println("/* Diseño general del modal */");
        out.println(".modal-content {");
        out.println("    border-radius: 20px;");
        out.println("    border: none;");
        out.println("    box-shadow: 0px 10px 50px rgba(0, 0, 0, 0.3);");
        out.println("    overflow: hidden;");
        out.println("    background: linear-gradient(135deg, #ffffff, #f8f9fa);");
        out.println("}");

        out.println("/* Encabezado del modal */");
        out.println(".modal-header {");
        out.println("    background-color: #343a40; /* Gris oscuro */");
        out.println("    color: #f8f9fa;");
        out.println("    padding: 20px;");
        out.println("    border-bottom: 3px solid #007bff;");
        out.println("    text-align: center;");
        out.println("}");

        out.println(".modal-title {");
        out.println("    font-family: 'Arial', sans-serif;");
        out.println("    font-weight: bold;");
        out.println("    font-size: 1.8rem;");
        out.println("    margin: 0;");
        out.println("    letter-spacing: 1px;");
        out.println("}");

        out.println(".modal-header .close {");
        out.println("    color: #ffffff;");
        out.println("    font-size: 1.5rem;");
        out.println("    opacity: 0.8;");
        out.println("}");

        out.println(".modal-header .close:hover {");
        out.println("    opacity: 1;");
        out.println("    transform: scale(1.2);");
        out.println("}");

        out.println("/* Cuerpo del modal */");
        out.println(".modal-body {");
        out.println("    padding: 30px;");
        out.println("    background-color: #ffffff;");
        out.println("    color: #343a40;");
        out.println("    font-family: 'Verdana', sans-serif;");
        out.println("    font-size: 1rem;");
        out.println("    line-height: 1.5;");
        out.println("}");

        out.println(".modal-body label {");
        out.println("    font-weight: bold;");
        out.println("    color: #007bff;");
        out.println("    display: block;");
        out.println("    margin-bottom: 8px;");
        out.println("    font-size: 1rem;");
        out.println("}");

        out.println(".modal-body .form-control {");
        out.println("    border-radius: 12px;");
        out.println("    border: 1px solid #dddddd;");
        out.println("    padding: 12px 15px;");
        out.println("    font-size: 1rem;");
        out.println("    background-color: #f9f9f9;");
        out.println("    transition: all 0.3s ease;");
        out.println("}");

        out.println(".modal-body .form-control:focus {");
        out.println("    border-color: #007bff;");
        out.println("    background-color: #ffffff;");
        out.println("    box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);");
        out.println("}");

        out.println(".modal-body input[type='file'] {");
        out.println("    padding: 5px;");
        out.println("}");

        out.println(".modal-body .form-group {");
        out.println("    margin-bottom: 20px;");
        out.println("}");

        out.println("/* Pie del modal */");
        out.println(".modal-footer {");
        out.println("    background-color: #f1f1f1;");
        out.println("    padding: 20px;");
        out.println("    border-top: 2px solid #dddddd;");
        out.println("}");

        out.println(".modal-footer .btn {");
        out.println("    border-radius: 20px;");
        out.println("    padding: 12px 30px;");
        out.println("    font-size: 1rem;");
        out.println("    font-weight: bold;");
        out.println("    letter-spacing: 1px;");
        out.println("    transition: all 0.3s ease;");
        out.println("}");

        out.println(".modal-footer .btn-primary {");
        out.println("    background-color: #007bff;");
        out.println("    border: none;");
        out.println("    color: #ffffff;");
        out.println("}");

        out.println(".modal-footer .btn-primary:hover {");
        out.println("    background-color: #0056b3;");
        out.println("    transform: translateY(-2px);");
        out.println("}");

        out.println(".modal-footer .btn-secondary {");
        out.println("    background-color: #6c757d;");
        out.println("    border: none;");
        out.println("    color: #ffffff;");
        out.println("}");

        out.println(".modal-footer .btn-secondary:hover {");
        out.println("    background-color: #5a6268;");
        out.println("    transform: translateY(-2px);");
        out.println("}");

        out.println("/* Animación del modal */");
        out.println(".modal.fade .modal-dialog {");
        out.println("    transform: translate(0, -50px);");
        out.println("    opacity: 0;");
        out.println("    transition: all 0.4s ease-in-out;");
        out.println("}");

        out.println(".modal.show .modal-dialog {");
        out.println("    transform: translate(0, 0);");
        out.println("    opacity: 1;");
        out.println("}");
        out.println("</style>");

        // JavaScript para alternar la visibilidad del submenú
        out.println("<script>");
        out.println("$(document).ready(function() {");
        out.println("    $('.nav-link[data-toggle]').click(function(event) {");
        out.println("        event.preventDefault();");
        out.println("        var submenu = $($(this).data('toggle'));");
        out.println("        $('.submenu').not(submenu).hide();");
        out.println("        submenu.toggle();");
        out.println("    });");
        out.println("});");
        out.println("function cargarDatosCancha(canchaId, nroCancha, precioDia, precioNoche) {");
        out.println("  document.getElementById('editarCanchaId').value = cancha_id;");
        out.println("  document.getElementById('editarNroCancha').value = nro_cancha;");
        out.println("  document.getElementById('editarPrecioDia').value = precio_dia;");
        out.println("  document.getElementById('editarPrecioNoche').value = precio_noche;");
        out.println("  document.getElementById('editarHoraInicio').value = hora_abierto;");
        out.println("  document.getElementById('editarHoraFin').value = hora_cerrado;");
        out.println("}");
        out.println("</script>");

        out.println("</head>");
        out.println("<body>");

        // Contenedor principal
        out.println("<div class='container-fluid'>");
        out.println("<div class='row'>");

        // Menú de navegación a la izquierda
        out.println("<nav class='col-md-3 bg-light sidebar' style='height: 100vh; background-color: lightgreen; padding: 0; width: 30%;'>\n");
        out.println("<div class='sidebar-sticky'>");
        out.println("<header class='bg-success text-white p-3 d-flex align-items-center'>");
        out.println("<img src='img/importante/logo.png' alt='Logo' class='logo mr-3' style='max-width: 30%; height: auto;'>");
        out.println("<h1 class='m-0' style='font-size: 1.5rem;'>SHUMPI Soccer Club</h1>");
        out.println("</header>");
        out.println("<ul class='nav flex-column'>");
        out.println("<li class='nav-item'><a class='nav-link' data-toggle='#configuracionMenu' href='#'><i class='fas fa-cog'></i> Configuración</a></li>");
        out.println("<ul id='configuracionMenu' class='submenu'>");
        out.println("<a class='nav-link' href='HTML/miPerfil.html'><i class='fas fa-user'></i>Mi perfil</a>");
        out.println("</ul>");
        out.println("<li class='nav-item'><a class='nav-link' data-toggle='#adminMenu' href='#'><i class='fas fa-user-shield'></i> Administrador</a></li>");
        out.println("<ul id='adminMenu' class='submenu'>");
        out.println("<a class='nav-link' href='HTML/registrarCajero.html'><i class='fas fa-user-plus'></i> Registrar</a>");
        out.println("<a class='nav-link' href='/listarCajeros'><i class='fas fa-user-cog'></i> Administrar cajero</a>");
        out.println("<a class='nav-link' href='/administrarCancha'><i class='fas fa-football-ball'></i> Administrar canchas</a>");
        out.println("</ul>");
        out.println("""
                <li class='nav-item'>
                  <a class='nav-link' data-toggle='#reporteMenu' href='#'>
                    <i class='fas fa-file-alt'></i> Reportes
                  </a>
                </li>
                <ul id='reporteMenu' class='submenu'>
                  <a class='nav-link' href='/listarClientes'>
                    <i class='fas fa-ticket-alt'></i> Ticket de Ventas
                  </a>
                  <a class='nav-link' href='HTML/reporteGeneral.html'>
                    <i class='fas fa-chart-line'></i> Reportes Generales
                  </a>
                </ul>
                """);
        out.println("<li class='nav-item'><a class='nav-link' href='index.html'><i class='fas fa-sign-out-alt'></i> Cerrar sesión</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</nav>");

        // Contenido principal
        out.println("<main role='main' class='col-md-9 ml-sm-auto px-4'>");

        out.println("<div class='d-flex justify-content-between align-items-center mb-4 mt-4'>");
        out.println("<h1 class='m-0'>Administrar Canchas</h1>");
        // Botón para agregar cancha
        out.println("<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#agregarCanchaModal'>");
        out.println("<i class='fas fa-plus'></i> Agregar Cancha");
        out.println("</button>");
        out.println("</div>");

        // Mostrar las canchas desde la base de datos
        out.println("<div class='row'>");

        for (Cancha cancha : canchas) {
            out.println("<div class='col-md-6 mb-4'>");
            out.println("<div class='card text-center' style='border: none;'>");
            out.println("<h5 class='card-title'>Cancha " + cancha.getNroCancha() + "</h5>");

            // Ajustar el contenedor de la imagen
            out.println("<div style='height: 300px; overflow: hidden;'>");
            out.println("<img src='img/canchasDeportivas/" + cancha.getImagenCancha() + "' class='card-img-top img-fluid' alt='Cancha " + cancha.getNroCancha() + "'>");
            out.println("</div>");

            out.println("<div class='card-body'>");

            // Información adicional
            out.println("<div class='mb-3'>");
            out.println("<div class='d-flex justify-content-center'>");
            out.println("<p class='mr-4'>6 Jugadores</p>");
            out.println("<p>Grass Sintético</p>");
            out.println("</div>");
            out.println("<p class='text-center'>Iluminación Red</p>");
            out.println("</div>");

            // Precios
            out.println("<div class='d-flex justify-content-center mb-3'>");
            out.println("<p class='card-text mr-5'><strong>Precio Día:</strong> S/" + cancha.getPrecioDia() + "</p>");
            out.println("<p class='card-text'><strong>Precio Noche:</strong> S/" + cancha.getPrecioNoche() + "</p>");
            out.println("</div>");

            // Botones de acción: Editar y Eliminar
            out.println("<div class='d-flex justify-content-center mb-3'>");
            out.println("<button type='button' class='btn btn-warning mr-2' data-toggle='modal' data-target='#editarCanchaModal' onclick='cargarDatosCancha(" + cancha.getCanchaId() + ", " + cancha.getNroCancha() + ", " + cancha.getPrecioDia() + ", " + cancha.getPrecioNoche() + ")'>");
            out.println("<i class='fas fa-edit'></i> Editar");
            out.println("</button>");
            out.println("<a href='eliminarCancha?id=" + cancha.getCanchaId() + "' class='btn btn-danger'>");
            out.println("<i class='fas fa-trash'></i> Eliminar</a>");
            out.println("</div>");

            out.println("</div>"); // cerrar card-body
            out.println("</div>"); // cerrar card
            out.println("</div>"); // cerrar col-md-6
        }

        out.println("</div>"); // cerrar row
        out.println("</main>");
        out.println("</div>"); // cerrar row
        out.println("</div>"); // cerrar container-fluid
        out.println("</body>");
        out.println("""
                <!-- Modal para agregar cancha -->
                <div class="modal fade" id="agregarCanchaModal" tabindex="-1" role="dialog" aria-labelledby="agregarCanchaLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="agregarCanchaLabel">Agregar Cancha</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/agregarCancha" method="POST" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="nro_cancha">Número de Cancha</label>
                                        <input type="number" class="form-control" name="nro_cancha" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="precio_dia">Precio Día</label>
                                        <input type="number" class="form-control" name="precio_dia" step="0.01" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="precio_noche">Precio Noche</label>
                                        <input type="number" class="form-control" name="precio_noche" step="0.01" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="hora_abierto">Hora de Inicio</label>
                                        <input type="time" class="form-control" name="hora_abierto" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="hora_cerrado">Hora de Fin</label>
                                        <input type="time" class="form-control" name="hora_cerrado" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="imagen_cancha">Imagen de la Cancha</label>
                                        <input type="file" class="form-control-file" name="imagen_cancha" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
                """);
// Modal para editar cancha
        out.println("<div class='modal fade' id='editarCanchaModal' tabindex='-1' role='dialog' aria-labelledby='editarCanchaLabel' aria-hidden='true'>");
        out.println("  <div class='modal-dialog' role='document'>");
        out.println("    <div class='modal-content'>");
        out.println("      <div class='modal-header'>");
        out.println("        <h5 class='modal-title' id='editarCanchaLabel'>Editar Cancha</h5>");
        out.println("        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
        out.println("          <span aria-hidden='true'>&times;</span>");
        out.println("        </button>");
        out.println("      </div>");
        out.println("      <div class='modal-body'>");
        out.println("        <form action='/editarCancha' method='POST' enctype='multipart/form-data'>");
        out.println("          <input type='hidden' name='canchaId' id='editarCanchaId'>"); // Campo oculto para ID
        out.println("          <div class='form-group'>");
        out.println("            <label for='nro_cancha'>Número de Cancha</label>");
        out.println("            <input type='number' class='form-control' name='nro_cancha' required>");
        out.println("          </div>");
        out.println("          <div class='form-group'>");
        out.println("            <label for='precio_dia'>Precio Día</label>");
        out.println("            <input type='number' class='form-control' name='precio_dia' step='0.01' required>");
        out.println("          </div>");
        out.println("          <div class='form-group'>");
        out.println("            <label for='precio_noche'>Precio Noche</label>");
        out.println("            <input type='number' class='form-control' name='precio_noche' step='0.01' required>");
        out.println("          </div>");
        out.println("          <div class='form-group'>");
        out.println("            <label for='hora_abierto'>Hora de Inicio</label>");
        out.println("            <input type='time' class='form-control' name='hora_abierto' required>");
        out.println("          </div>");
        out.println("          <div class='form-group'>");
        out.println("            <label for='hora_cerrado'>Hora de Fin</label>");
        out.println("            <input type='time' class='form-control' name='hora_cerrado' required>");
        out.println("          </div>");
        out.println("          <button type='submit' class='btn btn-primary'>Guardar Cambios</button>");
        out.println("        </form>");
        out.println("      </div>");
        out.println("    </div>");
        out.println("  </div>");
        out.println("</div>");
        out.println("</html>");
    }
}
