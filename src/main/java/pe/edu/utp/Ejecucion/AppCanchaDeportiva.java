package pe.edu.utp.Ejecucion;

import pe.edu.utp.Servlet.ListarCanchaServlet;
import pe.edu.utp.Servlet.LoginServlet;
import pe.edu.utp.utils.JettyUTP;
import java.net.URL;

public class AppCanchaDeportiva {

    public static void main(String[] args) throws Exception {
        String path = "src\\main\\resources\\";

        JettyUTP webserver = new JettyUTP(8080, path);
        webserver.addServlet(LoginServlet.class,"/login");
        webserver.addServlet(ListarCanchaServlet.class,"/listarCanchas");


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

