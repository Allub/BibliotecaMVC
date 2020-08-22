package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categorias;
import modelo.CategoriasDAO;
import modelo.Compras;
import modelo.ComprasDAO;
import modelo.Editoriales;
import modelo.EditorialesDAO;
import modelo.Idiomas;
import modelo.IdiomasDAO;
import modelo.Libro;
import modelo.LibroDAO;


import vista.LibroFrame;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class CtrlLibro implements ActionListener{
    LibroDAO dao = new LibroDAO();
    ComprasDAO cdao = new ComprasDAO();
    CategoriasDAO cadao = new CategoriasDAO();
    IdiomasDAO idao = new IdiomasDAO();
    EditorialesDAO edao = new EditorialesDAO();
    Libro l = new Libro();    
    LibroFrame vista = new LibroFrame();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    
    /**
     * Contrusctor de la clase
     * @param v de la clase LibroFrame
     */
    public CtrlLibro(LibroFrame v){
        this.vista=v;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        Listar(vista.tabla);
        comboIsbn(vista.cbIsbn);
        comboCategorias(vista.cbCategoria);
        comboIdiomas(vista.cbIdioma);
        comboEditoriales(vista.cbEditorial);
    }
    /**
     * metodo para efectuar acciones en el frame
     * @param e de la clase ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

       if(e.getSource()== vista.btnGuardar){
           agregar();
           limpiarTabla();
           Listar(vista.tabla);
       }
       if(e.getSource()==vista.btnEditar){
           int fila = vista.tabla.getSelectedRow();
           if(fila==-1){
               JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
           }else{
               
               String nSerie=(String)vista.tabla.getValueAt(fila, 0);
               String isbn=(String)vista.tabla.getValueAt(fila, 1);
               String titulo=(String)vista.tabla.getValueAt(fila, 2);               
               int aPublicacion=Integer.parseInt((String)vista.tabla.getValueAt(fila, 3).toString());
               int nPaginas=Integer.parseInt((String)vista.tabla.getValueAt(fila, 4).toString());
               String editorial=(String)vista.tabla.getValueAt(fila, 5);
               String idioma=(String)vista.tabla.getValueAt(fila, 6);
               String autor=(String)vista.tabla.getValueAt(fila, 7);
               String categoria=(String)vista.tabla.getValueAt(fila, 8);
               
               
               
               vista.txtNSerie.setText(nSerie);
               vista.txtNSerie.setEditable(false);
               vista.cbIsbn.setSelectedItem(isbn);
               vista.txtTitulo.setText(titulo);
               vista.txtNpaginas.setText(""+nPaginas);
               vista.txtAPublicacion.setText(""+aPublicacion);              
               vista.cbEditorial.setSelectedItem(editorial);
               vista.cbIdioma.setSelectedItem(idioma);
               vista.txtAutor.setText(autor);
               vista.cbCategoria.setSelectedItem(categoria);
           }
       }
       if(e.getSource()==vista.btnActualizar){
           Actualizar();
           limpiarTabla();
           Listar(vista.tabla);
       }
       if(e.getSource()==vista.btnEliminar){
           delete();
            limpiarTabla();
           Listar(vista.tabla);
           
       }
    }
    /**
     * metodo para borrar elementos de la tabla
     */
    public void delete(){
        int fila = vista.tabla.getSelectedRow();
           
           if(fila==-1){
               JOptionPane.showMessageDialog(vista, "Debe seleccionar un libro");
           }else{
               String Nserie = (String)vista.tabla.getValueAt(fila, 0);
               dao.delete(Nserie);
               JOptionPane.showMessageDialog(vista, "Libro eliminado");
           }
    }
    /**
     * metodo para limpiar tabla
     */
    void limpiarTabla(){
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
        }
    }
    /**
     * metodo para actualizar los elementos de la clase libros
     */
    public void Actualizar(){
        List<String> errores = new ArrayList<>();
        String nSerie = vista.txtNSerie.getText();
        String titulo = vista.txtTitulo.getText();
        String nPag = vista.txtNpaginas.getText();
        String aPub = vista.txtAPublicacion.getText();       
        String autor = vista.txtAutor.getText();
        
       //VALIDACIONES
             
        //TITULO ES VACIO?
        if(titulo.isEmpty()){
            errores.add("\n-Debe ingresar un titulo");
        }
        //AUTOR ES VACIO?
        if(autor.isEmpty()){
            errores.add("\n-Debe ingresar autor");
        }
        //NUMERO DE PAGINAS ES NUMERICO?
        int numP = 0;
        try {
            numP = Integer.parseInt(nPag);
        } catch (NumberFormatException ex) {
            errores.add("\n-Numero de paginas debe ser valor numerico");
        }
        //AÑO DE PUBLICACION ES NUMERICO?
        int anioP = 0;
        try {
            anioP = Integer.parseInt(aPub);
        } catch (NumberFormatException ex) {
            errores.add("\n"+"-El año de publicacion debe ser numerico");
        }
        //AÑO ES MAYOR A 2021?
        if(anioP > 2021){
            errores.add("\n"+"-Debe ser año valido");
        }
        
        //SI ERRORES ES VACION ENTONCES
        if(errores.isEmpty()){
        l.setnSerie(nSerie);
        l.setIsbn(vista.cbIsbn.getSelectedItem().toString());
        l.setTitulo(titulo);
        l.setnPaginas(anioP);       
        l.setaPublicacion(numP);
        l.setEditorial(vista.cbEditorial.getSelectedItem().toString());
        l.setIdioma(vista.cbIdioma.getSelectedItem().toString());
        l.setAutor(autor);
        l.setCategoria(vista.cbCategoria.getSelectedItem().toString());
        int r=dao.Actualizar(l);
         
            JOptionPane.showMessageDialog(vista, "-Libro actualizado");
            limpiar();
        }else{
             JOptionPane.showMessageDialog(vista,
              errores.toString().replace("[", "").replace("]", "").replace(",", "")
              , "ERROR", JOptionPane.WARNING_MESSAGE);
         }   
        }
    
    /**
     * Metodo para agregar los elementos a la clase Libro
     */
    public void agregar(){
        List<String> errores = new ArrayList<>();
        String nSerie = vista.txtNSerie.getText().trim();
        String titulo = vista.txtTitulo.getText().trim();
        String nPag = vista.txtNpaginas.getText().trim();     
        String aPub = vista.txtAPublicacion.getText().trim();
        String autor = vista.txtAutor.getText().trim();

        if(nSerie.isEmpty()){
            errores.add("-Debe ingresar un número de serie");
        }
        if(titulo.isEmpty()){
            errores.add("\n-Debe ingresar un titulo");
        }
        if(autor.isEmpty()){
            errores.add("\n-Debe ingresar autor");
        }    
        int numP = 0;
        try {
            numP = Integer.parseInt(nPag);
        } catch (NumberFormatException ex) {
            errores.add("\n-Numero de paginas debe ser valor numerico");
        }
        int anioP = 0;
        try {
            anioP = Integer.parseInt(aPub);
        } catch (NumberFormatException ex) {
            errores.add("\n"+"-El año de publicacion debe ser numerico");
        }
        if(anioP > 2021){
            errores.add("\n"+"-Debe ser año valido");
        }
        

        if(errores.isEmpty()){
        l.setnSerie(nSerie);
        l.setIsbn(vista.cbIsbn.getSelectedItem().toString());
        l.setTitulo(titulo);
        l.setnPaginas(anioP);       
        l.setaPublicacion(numP);
        l.setEditorial(vista.cbEditorial.getSelectedItem().toString());
        l.setIdioma(vista.cbIdioma.getSelectedItem().toString());
        l.setAutor(autor);
        l.setCategoria(vista.cbCategoria.getSelectedItem().toString());
        int r=dao.Agregar(l);
         
            JOptionPane.showMessageDialog(vista, "-Libro agregado");
            limpiar();
        }else{
             JOptionPane.showMessageDialog(vista,
              errores.toString().replace("[", "").replace("]", "").replace(",", "")
              , "ERROR", JOptionPane.WARNING_MESSAGE);
         }  
        }
    
    /**
     * Metodo para listar los elementos en la tabla
     * @param tabla de la clase JTable
     */
    public void Listar(JTable tabla){
        modelo=(DefaultTableModel)tabla.getModel();
        List<Libro>lista=dao.listar();
        Object[]object=new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getNSerie();
            object[1]=lista.get(i).getIsbn();
            object[2]=lista.get(i).getTitulo();
            object[3]=lista.get(i).getnPaginas();
            object[4]=lista.get(i).getaPublicacion();
            object[5]=lista.get(i).getEditorial();
            object[6]=lista.get(i).getIdioma();
            object[7]=lista.get(i).getAutor();
            object[8]=lista.get(i).getCategoria();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
    
            
       /**
        * metodo para limpiar los txt
        */     
    public void limpiar(){
        vista.txtNSerie.setText("");
        vista.txtTitulo.setText("");
        vista.txtAPublicacion.setText("");
        vista.txtAutor.setText("");
        vista.txtNpaginas.setText("");
    }
    /**
     * Metodo para llenar ComboBox Isbn
     * @param combito de la clase JComboBox
     */
    public void comboIsbn(JComboBox combito){
       model = (DefaultComboBoxModel)combito.getModel();
       List<Compras>lista=cdao.listar();
        for (int i = 0; i < lista.size(); i++) {
            combito.addItem(lista.get(i).getIsbn());
        }                                           
        vista.cbIsbn.setModel(model);              
    }
    /**
     * metodo para llenar ComboBox Categorias
     * @param combito de la clase JComboBox
     */
    public void comboCategorias(JComboBox combito){
       model = (DefaultComboBoxModel)combito.getModel();
       List<Categorias>lista=cadao.listar();
        for (int i = 0; i < lista.size(); i++) {
            combito.addItem(lista.get(i).getNombreCategoria());
        }                                           
        vista.cbCategoria.setModel(model);              
    }
    /**
     * Metodo para llenar ComboBox Idiomas
     * @param combito de la clase JComboBox
     */
    public void comboIdiomas(JComboBox combito){
       model = (DefaultComboBoxModel)combito.getModel();
       List<Idiomas>lista=idao.listar();
        for (int i = 0; i < lista.size(); i++) {
            combito.addItem(lista.get(i).getNombreIdioma());
        }                                           
        vista.cbIdioma.setModel(model);              
    }
    /**
     * Metodo para llenar ComboBox Editoriales
     * @param combito de la clase JComboBox
     */
    public void comboEditoriales(JComboBox combito){
       model = (DefaultComboBoxModel)combito.getModel();
       List<Editoriales>lista=edao.listar();
        for (int i = 0; i < lista.size(); i++) {
            combito.addItem(lista.get(i).getNombreEditorial());
        }                                           
        vista.cbEditorial.setModel(model);              
    }

}
