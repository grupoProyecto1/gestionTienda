/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.JDTablaFactura;
import javax.swing.table.DefaultTableModel;
import Modelo.Factura;
import Modelo.FacturaDAO;
import Modelo.Usuario;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joaquin
 */
public class ControladorJDTablaFactura {
    private JDTablaFactura vista;
    private boolean editable = false;
    private FacturaDAO facturaDAO = new FacturaDAO();
    private Usuario usuarioLogueado;
    
    public void creaVista(){
        this.vista = new JDTablaFactura(null, true);
        vista.setControlador(this);
        vista.setVisible(true);
    
}
    public ControladorJDTablaFactura (JDTablaFactura vista){
        this.vista = vista;
       
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
    public void creaTabla(){
        miTableModel.addColumn("ID");
        miTableModel.addColumn("DNI Cliente");
        miTableModel.addColumn("Nombre de Usuario");
        miTableModel.addColumn("Total Neto");
        miTableModel.addColumn("Total Bruto");
        miTableModel.addColumn("Fecha");
        vista.setjTable1(miTableModel);
    }
    public void rellenaTabla(){
        for (int i = 0; i < vista.getjTable1().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        try {
            facturaDAO.cargaFacturas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar las facturas", "Error al cargar", JOptionPane.ERROR_MESSAGE);
        }
        Object[] datos = new Object[6];
        for (Factura f : facturaDAO.getListaFacturas()){
            datos[0] = f.getId();
            datos[1]=f.getDNICliente();
            datos[2]=f.getNombreUsuario();
            datos[3]=f.getTotalNeto();
            datos[4]=f.getTotalBruto();
            datos[5]=f.getFecha();
            miTableModel.addRow(datos);
        }
        
    }
    
    public void seleccionarFactura(){
        String id = vista.getjTable1().getValueAt(vista.getjTable1().getSelectedRow(), 0).toString();
        String dni = vista.getjTable1().getValueAt(vista.getjTable1().getSelectedRow(), 1).toString();
        String usuario = vista.getjTable1().getValueAt(vista.getjTable1().getSelectedRow(), 2).toString();
        String totalNeto = vista.getjTable1().getValueAt(vista.getjTable1().getSelectedRow(), 3).toString();
        String totalBruto = vista.getjTable1().getValueAt(vista.getjTable1().getSelectedRow(), 4).toString();
        String fecha = vista.getjTable1().getValueAt(vista.getjTable1().getSelectedRow(), 5).toString();
        Factura f = new Factura(Integer.parseInt(id), dni, usuario, Double.parseDouble(totalNeto), Double.parseDouble(totalBruto), fecha);
        ControladorJDTablaLineas cjdtl = new ControladorJDTablaLineas(f,usuarioLogueado);
    }

    
    
}
