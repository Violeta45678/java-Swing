package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/labdsi";
    private String user = "root";
    private String password = "12345";

    public Connection getConnection() {
        try {
            // Cargar el controlador (opcional si estás usando el nuevo driver)
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Controlador no encontrado: " + e.getMessage());
        }
        return con;
    }

    public void desconectar() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
