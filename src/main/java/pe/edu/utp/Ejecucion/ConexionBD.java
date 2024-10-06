package pe.edu.utp.Ejecucion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/CanchaDeportiva?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();

        if (conexion != null) {
            System.out.println("Conexión a la base de datos MySQL establecida con éxito.");
            ConexionBD.cerrarConexion(conexion);
        } else {
            System.err.println("No se pudo conectar a la base de datos MySQL.");
        }
    }
}
