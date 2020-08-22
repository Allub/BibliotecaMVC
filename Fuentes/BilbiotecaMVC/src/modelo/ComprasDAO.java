
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class ComprasDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    /**
     * metodo para guardar los atributos de la tabla compras,
     * en un Array
     * @return datos
     */
    public List listar(){
        List<Compras>datos = new ArrayList<>();
        String sql="select * from compras";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Compras c = new Compras();
                c.setFolioFactura(Integer.parseInt(rs.getString(1)));
                c.setIsbn(rs.getString(2));
                c.setRutDist(rs.getString(3));
                c.setCantidad(Integer.parseInt(rs.getString(4)));
                datos.add(c);
            }
        } catch (SQLException e) {
        }
        return datos;
    }
    /**
     * metodo para ingresar datos a la tabla compras
     * @param c elementos de la clase compras
     * @return 1
     */
    public int Agregar(Compras c){
        String sql ="insert INTO compras(folioFactura, isbn, rutDistribuidor, cantidad) VALUES(?,?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, c.getFolioFactura());
            ps.setString(2, c.getIsbn());
            ps.setString(3, c.getRutDist());           
            ps.setInt(4, c.getCantidad());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Debe ser una factura valida");
        }
        
        return 1;
    }
    /**
     * metodo para actualizar datos de la tabla compras
     * @param c elementos de la clase compras
     * @return 1
     */
    public int Actualizar(Compras c){
        String sql = "UPDATE compras SET folioFactura=?, cantidad=?, rutDistribuidor=? WHERE isbn=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
 
            ps.setInt(1, c.getFolioFactura()); 
            ps.setInt(2, c.getCantidad());
            ps.setString(3, c.getRutDist());
            ps.setString(4, c.getIsbn());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 1;
    }
    /**
     * metodo para borrar elementos de la tabla compras
     * @param isbn de la clase compras
     */
    public void delete(String isbn){
        String sql="DELETE from compras WHERE isbn="+isbn;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
