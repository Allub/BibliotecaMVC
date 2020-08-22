
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class Conexion {
    Connection con;
    /**
     * metodo para efectuar conexion a la base de datos
     * @return conexion
     */
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/bibliotecadb_1";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
        }
        return con;
    }
}
