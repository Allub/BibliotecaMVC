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
public class IdiomasDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    /**
     * metodo para guardar los atributos de la tabla idiomas,
     * en un Array
     * @return datos
     */
    public List listar(){
        List<Idiomas>datos = new ArrayList<>();
        String sql="select * from idiomas";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Idiomas idioma = new Idiomas();
                idioma.setIdIdioma(Integer.parseInt(rs.getString(1)));
                idioma.setNombreIdioma(rs.getString(2));               
                datos.add(idioma);
            }
        } catch (SQLException e) {
        }
        return datos;
    } 
}
