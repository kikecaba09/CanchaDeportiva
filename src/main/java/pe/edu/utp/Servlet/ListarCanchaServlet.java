package pe.edu.utp.Servlet;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        CanchaDAO canchasDAO = new CanchaDAOImp();
        List<Cancha> canchas = canchasDAO.listarCanchas(); // Cambia a obtenerCanchas()

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < canchas.size(); i++) {
            Cancha cancha = canchas.get(i);
            json.append("{")
                    .append("\"id\":\"").append(cancha.getCanchaId()).append("\",") // Ajustar para usar el método correcto
                    .append("\"nro_cancha\":\"").append(cancha.getNroCancha()).append("\",")
                    .append("\"precio_dia\":\"").append(cancha.getPrecioDia()).append("\",")
                    .append("\"precio_noche\":\"").append(cancha.getPrecioNoche()).append("\"") // Quitar la coma al final
                    .append("}");
            if (i < canchas.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        out.print(json);
        out.flush();
    }
}
