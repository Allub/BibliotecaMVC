
package modelo;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class Distribuidores {
    private String rutDis;
    private String nombre;
    private int telefono;
    private int anioVende;

    public Distribuidores() {
    }

    public Distribuidores(String rutDis, String nombre, int telefono, int anioVende) {
        this.rutDis = rutDis;
        this.nombre = nombre;
        this.telefono = telefono;
        this.anioVende = anioVende;
    }

    public String getRutDis() {
        return rutDis;
    }

    public void setRutDis(String rutDis) {
        this.rutDis = rutDis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getAnioVende() {
        return anioVende;
    }

    public void setAnioVende(int anioVende) {
        this.anioVende = anioVende;
    }
    
    
}
