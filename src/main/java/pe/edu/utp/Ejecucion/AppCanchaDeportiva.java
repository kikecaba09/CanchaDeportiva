package pe.edu.utp.Ejecucion;

import pe.edu.utp.Servlet.Cajeros.ActualizarCajero;
import pe.edu.utp.Servlet.Cajeros.EliminarCajero;
import pe.edu.utp.Servlet.Cajeros.ListarCajerosServlet;
import pe.edu.utp.Servlet.Cajeros.RegistrarCajeroServlet;
import pe.edu.utp.Servlet.Canchas.*;
import pe.edu.utp.Servlet.Login.CerrarSesionServlet;
import pe.edu.utp.Servlet.Login.PerfilServlet;
import pe.edu.utp.Servlet.Reportes.*;
import pe.edu.utp.Servlet.Reservar.CalendarioReserva;
import pe.edu.utp.Servlet.Reservar.CanchasDisponible;
import pe.edu.utp.Servlet.Reservar.CanchasNoDisponible;
import pe.edu.utp.Servlet.Login.LoginServlet;
import pe.edu.utp.utils.JettyUTP;
import java.net.URL;

public class AppCanchaDeportiva {

    public static void main(String[] args) throws Exception {
        String path = "src\\main\\resources\\";

        JettyUTP webserver = new JettyUTP(8081, path);
        webserver.addServlet(LoginServlet.class,"/login");
        webserver.addServlet(CerrarSesionServlet.class,"/cerrarSesion");
        webserver.addServlet(PerfilServlet.class,"/miPerfil");
        webserver.addServlet(RegistrarCajeroServlet.class,"/RegistrarCajero");
        webserver.addServlet(ListarCajerosServlet.class,"/listarCajeros");
        webserver.addServlet(ActualizarCajero.class,"/ActualizarCajero");
        webserver.addServlet(EliminarCajero.class,"/eliminarCajero");
        webserver.addServlet(AdministrarCanchasServlet.class,"/administrarCancha");
        webserver.addServlet(AgregarCanchaServlet.class,"/agregarCancha");
        webserver.addServlet(ActualizarCanchaServlet.class,"/actualizarCancha");
        webserver.addServlet(EliminarCanchaServlet.class,"/eliminarCancha");
        webserver.addServlet(ListarCanchaServlet.class,"/listarCanchas");
        webserver.addServlet(ReservarCancha.class,"/reservarCancha");
        webserver.addServlet(CanchasDisponible.class,"/canchasDisponible");
        webserver.addServlet(CanchasNoDisponible.class,"/canchasNoDisponible");
        webserver.addServlet(CalendarioReserva.class,"/calendario");
        webserver.addServlet(ListarClientesServlet.class,"/listarClientes");
        webserver.addServlet(TicketReporte.class,"/ticket");
        webserver.addServlet(ExportarReporteExcel.class,"/exportarExcel");
        webserver.addServlet(ExportarReportePDF.class,"/exportarPdf");
        webserver.addServlet(ReporteGeneral.class,"/reporteGeneral");

        URL myURL = new URL("http://localhost:8081");
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

