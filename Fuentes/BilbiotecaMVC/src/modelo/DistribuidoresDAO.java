
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
public class DistribuidoresDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    /**
     * metodo para guardar los atributos de la tabla distribuidores,
     * en un Array
     * @return datos
     */
    public List listar(){
        List<Distribuidores>datos = new ArrayList<>();
        String sql="select * from distribuidores";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Distribuidores dis = new Distribuidores();
                dis.setRutDis(rs.getString(1));
                dis.setNombre(rs.getString(2)); 
                dis.setTelefono(Integer.parseInt(rs.getString(3)));
                dis.setAnioVende(Integer.parseInt(rs.getString(4)));
                
                datos.add(dis);
            }
        } catch (SQLException e) {
        }
        return datos;
    }    
}
