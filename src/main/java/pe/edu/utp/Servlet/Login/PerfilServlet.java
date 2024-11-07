package pe.edu.utp.Servlet.Login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Controller.ClienteController;
import pe.edu.utp.Controller.RolController;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.Model.Usuario;
import pe.edu.utp.Model.Cliente;
import pe.edu.utp.Model.Rol;
import pe.edu.utp.DAO.UsuarioDAO;
import pe.edu.utp.DAO.ClienteDAO;
import pe.edu.utp.DAO.RolDAO;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/miPerfil")
public class PerfilServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsuarioDAO usuarioDAO = new UsuarioController();
        ClienteDAO clienteDAO = new ClienteController();
        RolDAO rolDAO = new RolController();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("user_id");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (idUsuario != null) {
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
            if (usuario != null) {
                Cliente cliente = clienteDAO.obtenerClientePorId(usuario.getIdCliente());
                Rol rol = rolDAO.obtenerRolPorId(usuario.getIdRol());

                // Genera el HTML con datos dinámicos y CSS embebido
                out.println("<!DOCTYPE html>");
                out.println("<html lang='es'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Mi Perfil</title>");
                out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'>");
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
                out.println("* { box-sizing: border-box; margin: 0; padding: 0; }");
                out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; display: flex; justify-content: center; align-items: center; height: 100vh; flex-direction: column; }");
                out.println("header { width: 100%; text-align: center; background-color: #00c900; padding: 15px 0; color: white; }");
                out.println("h1 { font-size: 24px; margin: 0; }");
                out.println("#perfil-container { width: 600px; background-color: #f5f5f5; padding: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); border-radius: 8px; margin-top: 20px; }");
                out.println("#perfil-header { background-color: #00c900; color: white; padding: 10px; text-align: center; font-size: 16px; font-weight: bold; border-radius: 5px 5px 0 0; }");
                out.println("#perfil-form { background-color: #ffffff; padding: 20px; border-radius: 0 0 5px 5px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }");
                out.println("#perfil-form label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; font-size: 14px; }");
                out.println("#perfil-form input[type='text'], #perfil-form input[type='email'], #perfil-form input[type='date'] { width: 100%; padding: 8px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; }");
                out.println("#perfil-form input[type='text']:focus, #perfil-form input[type='email']:focus, #perfil-form input[type='date']:focus { border-color: #00c900; outline: none; }");
                out.println("#perfil-form button { width: 100%; padding: 10px; background-color: #00c900; color: white; font-size: 16px; border: none; border-radius: 4px; cursor: pointer; margin-top: 10px; }");
                out.println("#perfil-form button:hover { background-color: #009a00; }");
                out.println("</style>");
                out.println("<script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>");
                out.println("<script>");
                out.println("$(document).ready(function() {");
                out.println("    $('.nav-link[data-toggle]').click(function(event) {");
                out.println("        event.preventDefault();");
                out.println("        var submenu = $($(this).data('toggle'));");
                out.println("        $('.submenu').not(submenu).hide();");
                out.println("        submenu.toggle();");
                out.println("    });");
                out.println("});");
                out.println("</script>");

                out.println("</head>");
                out.println("<body>");

                // Contenedor principal
                out.println("<div class='container-fluid'>");
                out.println("<div class='row'>");

                // Menú de navegación a la izquierda
                out.println("<nav class='col-md-3 bg-light sidebar' style='height: 100vh; background-color: lightgreen; padding: 0; width: 30%;'>");
                out.println("<div class='sidebar-sticky'>");
                out.println("<header class='bg-success text-white p-3 d-flex align-items-center'>");
                out.println("<img src='img/importante/logo.png' alt='Logo' class='logo mr-3' style='max-width: 30%; height: auto;'>");
                out.println("<h1 class='m-0' style='font-size: 1.5rem;'>SHUMPI Soccer Club</h1>");
                out.println("</header>");
                out.println("<ul class='nav flex-column'>");
                out.println("<li class='nav-item'><a class='nav-link' data-toggle='#reservarMenu' href='#'><i class='fas fa-calendar-plus'></i> Reservar</a></li>");
                out.println("<ul id='reservarMenu' class='submenu'>");
                out.println("<a class='nav-link' href='canchasDisponibles.html'><i class='fas fa-check'></i> Canchas disponibles</a>");
                out.println("<a class='nav-link' href='canchasNoDisponibles.html'><i class='fas fa-times'></i> Canchas no disponibles</a>");
                out.println("<a class='nav-link' href='calendarioDisponibilidad.html'><i class='fas fa-calendar-alt'></i> Calendario disponibilidad</a>");
                out.println("</ul>");
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
                out.println("<li class='nav-item'><a class='nav-link' href='cerrarSesion.html'><i class='fas fa-sign-out-alt'></i> Cerrar sesión</a></li>");
                out.println("</ul>");
                out.println("</div>");
                out.println("</nav>");

                // Contenido principal
                out.println("<main role='main' class='col-md-9 ml-sm-auto px-4'>");

                out.println("<div id='perfil-container'>");
                out.println("<div id='perfil-header'>MI PERFIL</div>");

                out.println("<div id='perfil-form'>");
                out.println("<label for='nombre'>Nombres:</label>");
                out.println("<input type='text' id='nombre' name='nombre' value='" + (cliente != null ? cliente.getNombre() : "") + "' readonly>");

                out.println("<label for='apellidoPaterno'>Apellido Paterno:</label>");
                out.println("<input type='text' id='apellidoPaterno' name='apellidoPaterno' value='" + (cliente != null ? cliente.getApellido() : "") + "' readonly>");

                out.println("<label for='nroIdentidad'>Nro Documento:</label>");
                out.println("<input type='text' id='nroIdentidad' name='nroIdentidad' value='" + (cliente != null ? cliente.getNroIdentidad() : "") + "' readonly>");

                out.println("<label for='telefono'>Nro Teléfono:</label>");
                out.println("<input type='text' id='telefono' name='telefono' value='" + (cliente != null ? cliente.getTelefono() : "") + "' readonly>");

                out.println("<label for='email'>Email:</label>");
                out.println("<input type='email' id='email' name='email' value='" + (cliente != null ? cliente.getEmail() : "") + "' readonly>");

                out.println("<label for='fechaNacimiento'>Fecha Nacimiento:</label>");
                out.println("<input type='date' id='fechaNacimiento' name='fechaNacimiento' value='" + (cliente != null ? cliente.getFechaNacimiento() : "") + "' readonly>");

                out.println("<button type='button'>Editar</button>");
                out.println("</div>"); // Cierra perfil-form
                out.println("</div>"); // Cierra perfil-container

                out.println("</div>"); // Cerrar row
                out.println("</div>"); // Cerrar container-fluid
                out.println("</main>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
