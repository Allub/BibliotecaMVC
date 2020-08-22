
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class EditorialesDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    /**
     * metodo para guardar los atributos de la tabla editoriales,
     * en un Array
     * @return datos
     */
     public List listar(){
        List<Editoriales>datos = new ArrayList<>();
        String sql="select * from editoriales";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Editoriales editorial = new Editoriales();
                editorial.setIdEditorial(Integer.parseInt(rs.getString(1)));
                editorial.setNombreEditorial(rs.getString(2));               
                datos.add(editorial);
            }
        } catch (SQLException e) {
        }
        return datos;
    } 
}
