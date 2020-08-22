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
public class LibroDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    /**
     * metodo para guardar los atributos de la tabla libros,
     * en un array
     * @return datos
     */
    public List listar(){
        List<Libro>datos = new ArrayList<>();
        String sql="select * from libros";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Libro l = new Libro();
                l.setnSerie(rs.getString(1));
                l.setIsbn(rs.getString(2));
                l.setTitulo(rs.getString(3));
                l.setEditorial(rs.getString(4));
                l.setIdioma(rs.getString(5));
                l.setAutor(rs.getString(6));
                l.setnPaginas(Integer.parseInt(rs.getString(7)));
                l.setaPublicacion(Integer.parseInt(rs.getString(8)));                              
                l.setCategoria(rs.getString(9));
                
                datos.add(l);
            }
        } catch (SQLException e) {
        }
        return datos;
    }
    /**
     * metodo para ingresar datos a la tabla libros
     * @param l elementos de la clase Libro
     * @return 1
     */
    public int Agregar(Libro l){
        String sql ="insert INTO libros(NSerie, isbn, titulo, editorial, idioma, autor, numeroPaginas, anioPublicacion, categoria) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, l.getNSerie());
            ps.setString(2, l.getIsbn());
            ps.setString(3, (l.getTitulo()));
            ps.setString(4, l.getEditorial());
            ps.setString(5, l.getIdioma());
            ps.setString(6, l.getAutor());
            ps.setInt(7, (l.getnPaginas()));
            ps.setInt(8, (l.getaPublicacion()));                  
            ps.setString(9, l.getCategoria());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return 1;
    }
    
    /**
     * metodo para actualizar datos de la tabla Libros
     * @param l elementos de la clase Libro
     * @return 1
     */
    public int Actualizar(Libro l){
        String sql = "UPDATE libros SET isbn=?, titulo=?, editorial=?, idioma=?, autor=?, numeroPaginas=?, anioPublicacion=?, categoria=? WHERE NSerie=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql); 
            ps.setString(1, l.getIsbn());
            ps.setString(2, l.getTitulo());
            ps.setString(5, l.getEditorial());
            ps.setString(6, l.getIdioma());
            ps.setString(7, l.getAutor());
            ps.setInt(3, l.getnPaginas());
            ps.setInt(4, l.getaPublicacion());                             
            ps.setString(8, l.getCategoria());
            ps.setString(9, l.getNSerie());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            
        }
        return 1;
        
    }
    /**
     * metodo para borrar elementos de la tabla libros
     * @param Nserie de la clase Libro
     */
    public void delete(String Nserie){
        String sql="DELETE from libros WHERE NSerie="+Nserie;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
