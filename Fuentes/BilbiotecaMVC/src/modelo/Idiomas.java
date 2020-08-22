package modelo;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class Idiomas {
    private int idIdioma;
    private String nombreIdioma;

    public Idiomas() {
    }

    public Idiomas(int idIdioma, String nombreIdioma) {
        this.idIdioma = idIdioma;
        this.nombreIdioma = nombreIdioma;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }
    
    
    
}
