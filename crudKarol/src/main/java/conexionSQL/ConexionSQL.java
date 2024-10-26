package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    // Cambia los valores de URL, usuario y contraseña según tu base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_inventario"; // Asegúrate de que el nombre de la base de datos sea correcto
    private static final String USER = "root"; // Cambia si tu usuario es diferente
    private static final String PASSWORD = ""; // Coloca aquí tu contraseña

    // Método para obtener la conexión
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
}
