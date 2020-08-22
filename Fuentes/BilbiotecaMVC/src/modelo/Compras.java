/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class Compras {
    private int folioFactura;
    private String isbn;
    private String rutDist;
    private int cantidad;   
    
    public Compras(){
        
    }

    public Compras(int folioFactura, int cantidad, String rutDist, String isbn) {
        this.folioFactura = folioFactura;
        this.cantidad = cantidad;
        this.rutDist = rutDist;
        this.isbn = isbn;
    }

    public int getFolioFactura() {
        return folioFactura;
    }

    public void setFolioFactura(int folioFactura) {
        this.folioFactura = folioFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRutDist() {
        return rutDist;
    }

    public void setRutDist(String rutDist) {
        this.rutDist = rutDist;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    
    
}
