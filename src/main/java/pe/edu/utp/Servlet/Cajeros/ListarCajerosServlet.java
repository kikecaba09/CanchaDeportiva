package pe.edu.utp.Servlet.Cajeros;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.UsuarioController;
import pe.edu.utp.DAO.UsuarioDAO;
import pe.edu.utp.Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarCajeros")
public class ListarCajerosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UsuarioDAO usuarioDAO = new UsuarioController();
        List<Usuario> cajeros = usuarioDAO.listarUsuariosCajeros(); // Método para obtener la lista de cajeros

        // HTML y CSS
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Lista de Cajeros</title>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css'>"); // Importar FontAwesome
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
        out.println(".container { max-width: 1200px; margin: auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1); }");
        out.println("h1 { text-align: center; color: #333; margin-bottom: 20px; font-size: 2.5em; text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2); }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
        out.println("th, td { padding: 15px; text-align: left; border-bottom: 1px solid #ddd; }");
        out.println("th { background-color: #4CAF50; color: white; font-size: 1.2em; }");
        out.println("tr:hover { background-color: #f1f1f1; }");
        out.println("td { background-color: white; }");
        out.println(".button { background-color: #4CAF50; color: white; border: none; padding: 10px 15px; text-align: center; text-decoration: none; display: inline-block; margin: 4px 2px; cursor: pointer; border-radius: 5px; transition: background-color 0.3s ease; font-weight: bold; font-size: 1em; }");
        out.println(".button:hover { background-color: #45a049; }");
        out.println(".button.delete { background-color: #f44336; }");
        out.println(".button.delete:hover { background-color: #d32f2f; }");
        out.println(".button.edit { background-color: #2196F3; }");
        out.println(".button.edit:hover { background-color: #1976D2; }");
        out.println(".header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }");
        out.println(".search-container { margin: 20px 0; display: flex; justify-content: center; }");
        out.println(".search-container input[type='text'] { padding: 10px; width: 60%; border: 1px solid #ccc; border-radius: 5px; font-size: 1em; }");
        out.println(".search-container button { padding: 10px; background-color: #2196F3; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; margin-left: 5px; transition: background-color 0.3s ease; }");
        out.println(".search-container button:hover { background-color: #1976D2; }");
        out.println(".footer { text-align: center; padding: 20px 0; background-color: #333; color: white; position: relative; margin-top: 20px; border-radius: 0 0 10px 10px; }");
        out.println(".footer p { margin: 0; }");
        out.println("input::placeholder { color: #999; font-style: italic; }");
        out.println("table th, table td { border: 1px solid #ddd; transition: background-color 0.3s; }");
        out.println("table th:hover { background-color: #45a049; color: white; }");
        out.println("table td:hover { background-color: #f1f1f1; }");
        out.println(".alert { display: none; padding: 15px; margin-bottom: 20px; border-radius: 5px; }");
        out.println(".alert.success { background-color: #4CAF50; color: white; }");
        out.println(".alert.error { background-color: #f44336; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='header'>");
        out.println("<h1>Administrar Cajeros</h1>");
        out.println("</div>");

        // Mensaje de éxito o error (puedes modificar esto según sea necesario)
        out.println("<div class='alert success' id='alert'>Los cajeros se han cargado exitosamente.</div>");

        out.println("<div class='search-container'>");
        out.println("<input type='text' placeholder='Buscar cajero...' aria-label='Buscar cajero'>");
        out.println("<button type='button' class='button'><i class='fas fa-search'></i> Buscar</button>");
        out.println("</div>");

        out.println("<table>");
        out.println("<tr><th>Nombre</th><th>Apellido</th><th>DNI</th><th>Teléfono</th><th>Email</th><th>Fecha de Nacimiento</th><th>Username</th><th>Password</th><th>Acciones</th></tr>");

        for (Usuario cajero : cajeros) {
            out.println("<tr>");
            out.println("<td>" + cajero.getCliente().getNombre() + "</td>");
            out.println("<td>" + cajero.getCliente().getApellido() + "</td>");
            out.println("<td>" + cajero.getCliente().getNroIdentidad() + "</td>");
            out.println("<td>" + cajero.getCliente().getTelefono() + "</td>");
            out.println("<td>" + cajero.getCliente().getEmail() + "</td>");
            out.println("<td>" + cajero.getCliente().getFechaNacimiento() + "</td>");
            out.println("<td>" + cajero.getUsername() + "</td>"); // Mostrar Username
            out.println("<td>" + cajero.getPassword() + "</td>"); // Mostrar Password
            out.println("<td>");
            out.println("<div style='display: flex; gap: 10px;'>"); // Usa flexbox para los botones
            out.println("<a href='/ActualizarCajero?id=" + cajero.getIdUsuario() + "' class='button edit'><i class='fas fa-edit'></i> Editar</a>");
            out.println("<form action='/EliminarCajero' method='post' style='display:inline;'>");
            out.println("<input type='hidden' name='user_id' value='" + cajero.getIdUsuario() + "'>");
            out.println("<button type='submit' class='button delete'>");
            out.println("<i class='fas fa-trash'></i> Eliminar</button>");
            out.println("</form>");
            out.println("</form>");
            out.println("</form>");
            out.println("</div>");
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</div>"); // Close container
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}

