/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.JDTablaLineas;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Modelo.LineaFactura;
import Modelo.LineafacturaDAO;
import Modelo.Factura;
import Modelo.Usuario;
/**
 *
 * @author Joaquin
 */
public class ControladorJDTablaLineas {
    private Factura f;
    private Usuario usuarioLogueado;
    private JDTablaLineas vista;
    private LineafacturaDAO lineaFacturaDAO;

    public ControladorJDTablaLineas(Factura f, Usuario usuarioLogueado) {
        this.f = f;
        this.usuarioLogueado = usuarioLogueado;
    }
    public void creaVista(){
        this.vista = new JDTablaLineas(null, true);
        vista.setControlador(this);
        vista.setVisible(true);
    
}
    public DefaultTableModel miTableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
           
                return false;
         
       }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    };
    public void creaTabla (){
        miTableModel.addColumn("Linea");
        miTableModel.addColumn("Id Factura");
        miTableModel.addColumn("Precio de venta");
        miTableModel.addColumn("Cantidad");
        vista.setjTable1(miTableModel);
    }
    public void rellenaTabla(){
        for (int i = 0; i < vista.getjTable1().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        try{
            lineaFacturaDAO.cargaLineas(f);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(vista, "Error al cargar las lineas", "Error al cargar", JOptionPane.ERROR_MESSAGE);
        }
        Object[] datos = new Object[5];
        for (LineaFactura l : lineaFacturaDAO.getListaLineas()){
            datos[0] = l.getId();
            datos[1] = l.getFacturaId();
            datos[2] = l.getArticuloId();
            datos[3] = l.getPrecioVenta();
            datos[4] = l.getCantidad();
            miTableModel.addRow(datos);
        }
    }
    
}
