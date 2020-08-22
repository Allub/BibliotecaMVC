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
import modelo.Compras;
import modelo.ComprasDAO;
import modelo.Distribuidores;
import modelo.DistribuidoresDAO;
import vista.ComprasFrame;

/**
 * @version 1.0
 * @author Franco
 * @author Loreto
 */
public class CtrlCompras implements ActionListener{
    
    ComprasDAO cdao = new ComprasDAO();
    DistribuidoresDAO disdao = new DistribuidoresDAO();
    Compras c = new Compras();
    ComprasFrame vista = new ComprasFrame();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    /**
     * Constructor de la clase
     * @param v de la clase ComprasFrame
     */
     public CtrlCompras(ComprasFrame v){
         this.vista = v;
         this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
         Listar(vista.tablaCompras);
         comboDistr(vista.cbRut);
     }
     
    /**
     * metodo para efectuar acciones en el frame
     * @param e de la clase ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()== vista.btnAgregar){
           agregar();
           limpiarTabla();
           Listar(vista.tablaCompras);
           
       }
       if(e.getSource()==vista.btnEditar){
           int fila = vista.tablaCompras.getSelectedRow();
           if(fila==-1){
               JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
           }else{
               int folioF=Integer.parseInt((String)vista.tablaCompras.getValueAt(fila, 0).toString());
               String isbn=(String)vista.tablaCompras.getValueAt(fila, 1);
               String rutD=(String)vista.tablaCompras.getValueAt(fila, 2);
               int cantidad=Integer.parseInt((String)vista.tablaCompras.getValueAt(fila, 3).toString());
               

               vista.txtFolioFac.setText(""+folioF);
               vista.txtIsbn.setText(isbn);
               vista.txtIsbn.setEditable(false);
               vista.cbRut.setSelectedItem(rutD);
               vista.txtCantidad.setText(""+cantidad);

           }
       }
       
       
       
        if(e.getSource()==vista.btnActualizar){
           Actualizar();
           limpiarTabla();
           Listar(vista.tablaCompras);
       }
       
       
       if(e.getSource()==vista.btnEliminar){
           delete();
            limpiarTabla();
             limpiar();
           Listar(vista.tablaCompras);                     
       }
    }
    /**
     * metodo para borrar elementos de la tabla
     */
    public void delete(){
         int fila = vista.tablaCompras.getSelectedRow();
           
           if(fila==-1){
               JOptionPane.showMessageDialog(vista, "Debe seleccionar una compra");
           }else{
               String isbn = vista.tablaCompras.getValueAt(fila, 1).toString();
               cdao.delete(isbn);
               JOptionPane.showMessageDialog(vista, "Compra eliminada");
           }
    }
    /**
     * metodo para limpiar tabla
     */
    void limpiarTabla(){
          for (int i = 0; i < vista.tablaCompras.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
        }
    }
     /**
      * metodo para actualizar los elementos de la clase compras
      */
    public void Actualizar(){
        List<String> errores = new ArrayList<>();
        int folioFac = Integer.parseInt(vista.txtFolioFac.getText().trim());
        String isbn = vista.txtIsbn.getText().trim();
        String cantidad = vista.txtCantidad.getText().trim();  
        
        //VALIDACIONES
       
        
        //ISBN ES VACIO
        if(isbn.trim().isEmpty()){
            errores.add("\n-Debe ingresar un ISBN");
        }
        
        
        //CANTIDAD ES NUMERICO
        int canti = 0;
        try {
            canti = Integer.parseInt(cantidad);
        } catch (NumberFormatException ex) {
            errores.add("\n-La cantidad debe ser valor numerico");
        }
        
        //SI ERRORES ES VACIO, SE CREA UNA NUEVA COMPRA
        if(errores.isEmpty()){
        c.setFolioFactura(folioFac);
        c.setIsbn(isbn);
        c.setRutDist(vista.cbRut.getSelectedItem().toString());
        c.setCantidad(canti); 
        
        int r=cdao.Actualizar(c);
        JOptionPane.showMessageDialog(vista, "-Compra Actualizada");
            limpiar();
        }else{
             JOptionPane.showMessageDialog(vista,
              errores.toString().replace("[", "").replace("]", "").replace(",", "")
              , "ERROR", JOptionPane.WARNING_MESSAGE);
           
        }
    }
     /**
      * metodo para agregar los elementos a la clase Compras
      */
    public void agregar(){
          List<String> errores = new ArrayList();
        String folioFac = vista.txtFolioFac.getText().trim();
        String isbn = vista.txtIsbn.getText().trim();
        String cantidad = vista.txtCantidad.getText().trim();     

        //VALIDACIONES
        //FOLIO FACTURA ES NUMERICO
        int folioF = 0;
        try {
            folioF = Integer.parseInt(folioFac);
        } catch (NumberFormatException ex) {
            errores.add("-Folio factura debe ser valor numerico");
        }
        //ISBN ES VACIO
        if(isbn.trim().isEmpty()){
            errores.add("\n-Debe ingresar un ISBN");
        }
        
        
        //CANTIDAD ES NUMERICO
        int canti = 0;
        try {
            canti = Integer.parseInt(cantidad);
        } catch (NumberFormatException ex) {
            errores.add("\n-La cantidad debe ser valor numerico");
        }
        
        //SI ERRORES ES VACIO, SE CREA UNA NUEVA COMPRA
        if(errores.isEmpty()){
        c.setFolioFactura(folioF);
        c.setIsbn(isbn);
        c.setRutDist(vista.cbRut.getSelectedItem().toString());
        c.setCantidad(canti);       
        int r=cdao.Agregar(c);
         
            
            vista.txtCantidad.setText("");
            vista.txtIsbn.setText("");
            vista.cbRut.setSelectedItem(c.getRutDist());
        
        }else{
             JOptionPane.showMessageDialog(vista,
              errores.toString().replace("[", "").replace("]", "").replace(",", "")
              , "ERROR", JOptionPane.WARNING_MESSAGE);
         } 
    }
     /**
      * metodo para listar los elementos en la tabla
      * @param tabla de la clase Jtable
      */
    public void Listar(JTable tabla){
            modelo=(DefaultTableModel)tabla.getModel();
        List<Compras>lista=cdao.listar();
        Object[]object=new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getFolioFactura();
            object[1]=lista.get(i).getIsbn();
            object[2]=lista.get(i).getRutDist();
            object[3]=lista.get(i).getCantidad();
            modelo.addRow(object);
        }
        vista.tablaCompras.setModel(modelo);
    }
     /**
      * metodo para limpirar los Txt
      */
    public void limpiar(){
         vista.txtCantidad.setText("");
         vista.txtFolioFac.setText("");
         vista.txtIsbn.setText("");
    }
    /**
     * metodo para llenar comboBox distribuidor
     * @param combito de la clase JcomboBox
     */
    public void comboDistr(JComboBox combito){
       model = (DefaultComboBoxModel)combito.getModel();
       List<Distribuidores>lista=disdao.listar();
        for (int i = 0; i < lista.size(); i++) {
            combito.addItem(lista.get(i).getRutDis());
        }                                           
        vista.cbRut.setModel(model);              
    }
    
}
