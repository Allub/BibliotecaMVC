
package modelo;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class Editoriales {
    private int idEditorial;
    private String nombreEditorial;

    public Editoriales() {
    }

    public Editoriales(int idEditorial, String nombreEditorial) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }
    
    
    
}
