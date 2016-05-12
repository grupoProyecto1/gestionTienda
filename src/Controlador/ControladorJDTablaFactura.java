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

    /**
     * Constructor parametrizado que crea un objeto de tipo
     * controladorjdtablafactura
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public ControladorJDTablaFactura(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista de jdtablafactura
     */
    public void creaVista() {
        this.vista = new JDTablaFactura(null, true);
        vista.setControlador(this);
        creaTabla();
        rellenaTabla();
        vista.setVisible(true);

    }

    /**
     * Objeto de tablemodel con las propiedades isCellEditable(para poder
     * modificar o no las celdas) y getColumnClass(para obtener el tipo de valor
     * de la columna, y asi poder utilizar checkbox) sobreescritos
     */
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

    /**
     * Metodo que establece las columnas de la tabla y el modelo
     */
    public void creaTabla() {
        miTableModel.addColumn("ID");
        miTableModel.addColumn("DNI Cliente");
        miTableModel.addColumn("Nombre de Usuario");
        miTableModel.addColumn("Total Neto");
        miTableModel.addColumn("Total Bruto");
        miTableModel.addColumn("Fecha");
        vista.setjTableFactura(miTableModel);
    }

    /**
     * Metodo que rellena la tabla con las facturas de la base de datos
     */
    public void rellenaTabla() {
        for (int i = 0; i < vista.getjTableFactura().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        try {
            facturaDAO.cargaFacturas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar las facturas", "Error al cargar", JOptionPane.ERROR_MESSAGE);
        }
        Object[] datos = new Object[6];
        for (Factura f : facturaDAO.getListaFacturas()) {
            datos[0] = f.getId();
            datos[1] = f.getDNICliente();
            datos[2] = f.getNombreUsuario();
            datos[3] = f.getTotalNeto();
            datos[4] = f.getTotalBruto();
            datos[5] = f.getFecha();
            miTableModel.addRow(datos);
        }

    }

    /**
     * Metodo que llama al controlador de jdtablalineas pasandole la factura
     * seleccionada y el usuario logueado, para mostrar las lineas de la factura
     */
    public void seleccionarFactura() {
        try{
        String id = vista.getjTableFactura().getValueAt(vista.getjTableFactura().getSelectedRow(), 0).toString();
        String dni = vista.getjTableFactura().getValueAt(vista.getjTableFactura().getSelectedRow(), 1).toString();
        String usuario = vista.getjTableFactura().getValueAt(vista.getjTableFactura().getSelectedRow(), 2).toString();
        String totalNeto = vista.getjTableFactura().getValueAt(vista.getjTableFactura().getSelectedRow(), 3).toString();
        String totalBruto = vista.getjTableFactura().getValueAt(vista.getjTableFactura().getSelectedRow(), 4).toString();
        String fecha = vista.getjTableFactura().getValueAt(vista.getjTableFactura().getSelectedRow(), 5).toString();
        Factura f = new Factura(Integer.parseInt(id), dni, usuario, Double.parseDouble(totalNeto), Double.parseDouble(totalBruto), fecha);
        ControladorJDTablaLineas cjdtl = new ControladorJDTablaLineas(f, usuarioLogueado);
        }catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar la factura de la cual quiere ver las lineas", "Error Ninguna Factura Seleccionada", JOptionPane.ERROR_MESSAGE);
        }
    }

}
