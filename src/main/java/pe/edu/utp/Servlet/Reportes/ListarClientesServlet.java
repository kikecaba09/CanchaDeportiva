package pe.edu.utp.Servlet.Reportes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Controller.ClienteController;
import pe.edu.utp.DAO.ClienteDAO;
import pe.edu.utp.Model.Cliente;
import java.io.*;
import java.util.List;

@WebServlet("/listarClientes")
public class ListarClientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteDAO clienteDAO;

    public void init() {
        clienteDAO = new ClienteController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Cliente> clientes = clienteDAO.listarClientes();

        // Establecer el tipo de contenido de la respuesta (HTML)
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Generar el contenido HTML
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Lista de Clientes</title>");
        out.println("""
                <style>
                    /* Reset de márgenes y padding */
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }
                
                    /* Estilo global para el cuerpo */
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f5f5f5;
                        color: #333;
                        line-height: 1.6;
                        padding: 20px;
                    }
                
                    /* Fondo de la página */
                    .background {
                        background-image: url('https://via.placeholder.com/1500x800'); /* Cambia la URL por tu fondo */
                        background-size: cover;
                        background-position: center;
                        padding: 60px 20px;
                        min-height: 100vh;
                    }
                
                    /* Contenedor principal */
                    .container {
                        max-width: 1200px;
                        margin: 0 auto;
                        background-color: rgba(255, 255, 255, 0.9);
                        padding: 30px;
                        border-radius: 10px;
                        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    }
                
                    /* Estilo del encabezado */
                    h1 {
                        color: #2c3e50;
                        text-align: center;
                        font-size: 36px;
                        margin-bottom: 30px;
                        font-weight: 600;
                        text-transform: uppercase;
                    }
                
                    /* Estilo para la tabla */
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-bottom: 30px;
                        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    }
                
                    /* Cabecera de la tabla */
                    th {
                        background-color: #2980b9;
                        color: white;
                        text-align: left;
                        padding: 12px;
                        font-size: 16px;
                        text-transform: uppercase;
                    }
                
                    /* Estilo de las celdas */
                    td {
                        background-color: #ecf0f1;
                        padding: 12px;
                        text-align: left;
                        font-size: 14px;
                    }
                
                    /* Fila par */
                    tr:nth-child(even) td {
                        background-color: #f9f9f9;
                    }
                
                    /* Efecto al pasar el ratón sobre las filas */
                    tr:hover {
                        background-color: #f39c12;
                        transition: background-color 0.3s ease;
                    }
                
                    /* Estilo para los enlaces */
                    a {
                        color: #2980b9;
                        text-decoration: none;
                        font-weight: bold;
                        border: 2px solid transparent;
                        padding: 6px 12px;
                        border-radius: 5px;
                        transition: all 0.3s ease;
                    }
                
                    a:hover {
                        background-color: #2980b9;
                        color: white;
                        border-color: #2980b9;
                    }
                
                    /* Botón de acción */
                    .button {
                        display: inline-block;
                        background-color: #27ae60;
                        color: white;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 5px;
                        font-size: 16px;
                        font-weight: bold;
                        transition: background-color 0.3s ease;
                    }
                
                    .button:hover {
                        background-color: #2ecc71;
                    }
                
                    /* Estilo para el pie de página */
                    footer {
                        background-color: #34495e;
                        color: white;
                        text-align: center;
                        padding: 20px;
                        font-size: 14px;
                        position: fixed;
                        width: 100%;
                        bottom: 0;
                    }
                
                    /* Contenedor de la fila para mostrar los datos */
                    .table-container {
                        overflow-x: auto;
                    }
                
                    /* Efecto de sombra para la tabla */
                    table {
                        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
                    }
                
                    /* Responsividad */
                    @media screen and (max-width: 768px) {
                        h1 {
                            font-size: 28px;
                        }
                
                        table, th, td {
                            font-size: 12px;
                        }
                
                        .container {
                            padding: 15px;
                        }
                
                        .table-container {
                            padding: 10px;
                        }
                    }
                
                    /* Transiciones suaves */
                    tr, td, th, a, .button {
                        transition: all 0.3s ease-in-out;
                    }
                
                    /* Animaciones suaves al cargar la tabla */
                    .table-container table {
                        animation: fadeIn 1s ease-in-out;
                    }
                
                    @keyframes fadeIn {
                        from {
                            opacity: 0;
                        }
                        to {
                            opacity: 1;
                        }
                    }
                </style>
                """);
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Lista de Clientes</h1>");
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Nombre</th>");
        out.println("<th>Apellido</th>");
        out.println("<th>Identidad</th>");
        out.println("<th>Telefono</th>");
        out.println("<th>Email</th>");
        out.println("<th>Fecha Nacimiento</th>");
        out.println("<th>Acciones</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        // Iterar a través de la lista de clientes y mostrar los datos
        for (Cliente cliente : clientes) {
            out.println("<tr>");
            out.println("<td>" + cliente.getNombre() + "</td>");
            out.println("<td>" + cliente.getApellido() + "</td>");
            out.println("<td>" + cliente.getNroIdentidad() + "</td>");
            out.println("<td>" + cliente.getTelefono() + "</td>");
            out.println("<td>" + cliente.getEmail() + "</td>");
            out.println("<td>" + cliente.getFechaNacimiento() + "</td>");

            // Botón o enlace para ver las reservas del cliente
            out.println("<td><a href='/ticket?cliente_id=" + cliente.getClienteId() + "'>Ver Reservas</a></td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
