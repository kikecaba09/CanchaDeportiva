package pe.edu.utp.Ejecucion;

import pe.edu.utp.Servlet.Canchas.ActualizarCanchaServlet;
import pe.edu.utp.Servlet.Canchas.AgregarCanchaServlet;
import pe.edu.utp.Servlet.Canchas.EliminarCanchaServlet;
import pe.edu.utp.Servlet.Login.CerrarSesionServlet;
import pe.edu.utp.Servlet.Login.PerfilServlet;
import pe.edu.utp.Servlet.Reservar.CanchasDisponible;
import pe.edu.utp.Servlet.Reservar.CanchasNoDisponible;
import pe.edu.utp.Servlet.Canchas.ListarCanchaServlet;
import pe.edu.utp.Servlet.Login.LoginServlet;
import pe.edu.utp.utils.JettyUTP;
import java.net.URL;

public class AppCanchaDeportiva {

    public static void main(String[] args) throws Exception {
        String path = "src\\main\\resources\\";

        JettyUTP webserver = new JettyUTP(8080, path);
        webserver.addServlet(LoginServlet.class,"/login");
        webserver.addServlet(CerrarSesionServlet.class,"/cerrarSesion");
        webserver.addServlet(PerfilServlet.class,"/miPerfil");
        webserver.addServlet(AgregarCanchaServlet.class,"/agregarCancha");
        webserver.addServlet(ActualizarCanchaServlet.class,"/actualizarCancha");
        webserver.addServlet(EliminarCanchaServlet.class,"/eliminarCancha");
        webserver.addServlet(ListarCanchaServlet.class,"/listarCanchas");
        webserver.addServlet(CanchasDisponible.class,"/canchasDisponible");
        webserver.addServlet(CanchasNoDisponible.class,"/canchasNoDisponible");

        URL myURL = new URL("http://localhost:8080");
        System.out.println("*********************************************************");
        System.out.println("CLICK AQUI PARA ABRIR LA APLICACION:" + myURL);
        System.out.println("*********************************************************");
        webserver.start();
    }

    public static String getErrorLogFile() {
        String archivo = new String("src/main/resources/errores.txt");
        return archivo;
    }
}

