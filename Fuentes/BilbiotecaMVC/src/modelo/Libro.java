package modelo;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class Libro {
    
    private String nSerie;
    private String isbn;
    private String titulo;
    private String editorial;
    private String idioma;
    private String autor;
    private int nPaginas;
    private int aPublicacion;
    private String categoria;
    
    
    public Libro(){
        
    }

    public Libro(String nSerie, String isbn, String titulo, String editorial, String idioma, String autor, int nPaginas, int aPublicacion, String categoria) {
        this.nSerie = nSerie;
        this.isbn = isbn;
        this.titulo = titulo;
        this.editorial = editorial;
        this.idioma = idioma;
        this.autor = autor;
        this.nPaginas = nPaginas;
        this.aPublicacion = aPublicacion;
        this.categoria = categoria;
    }

    public String getNSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }

    public int getaPublicacion() {
        return aPublicacion;
    }

    public void setaPublicacion(int aPublicacion) {
        this.aPublicacion = aPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

    
    
    
}
