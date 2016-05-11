package Controlador;

import Modelo.Articulo;
import Modelo.ArticuloDAO;
import Modelo.Usuario;
import Vista.JDTablaUsuariosClientesProveedorArticulo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joaquin
 */
public class ControladorJDTablaArticulos {

    private JDTablaUsuariosClientesProveedorArticulo vista;
    private boolean editable = false;
    private ArticuloDAO articuloDAO = new ArticuloDAO();
    private Usuario usuarioLogueado;
    private int botones;

    public ControladorJDTablaArticulos(Usuario usuarioLogueado,int botones) {
        this.usuarioLogueado = usuarioLogueado;
        this.botones = botones;
        creaVista();
    }
    
    public void creaVista(){
        this.vista = new JDTablaUsuariosClientesProveedorArticulo(botones, 3);
        vista.setControladorArticulo(this);
        if (botones == 2) {
            editable = true;
        }
        creaTabla();
        rellenaTabla();
        vista.setVisible(true);
    }
    
    public DefaultTableModel miTableModel = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (!editable) {
                return false;
            } else {
                if (columnIndex == 0) {
                    return false;
                }
                return true;
            }
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    };

    public void creaTabla() {
        miTableModel.addColumn("ID");
        miTableModel.addColumn("Nombre");
        miTableModel.addColumn("Descripcion");
        miTableModel.addColumn("Stock");
        miTableModel.addColumn("PrecioUnitario");
        miTableModel.addColumn("Impuesto");
        vista.setjTableUsuariosClientes(miTableModel);
        vista.getjTableUsuariosClientes().setAutoCreateRowSorter(true);

    }

    public void rellenaTabla() {
        for (int i = 0; i < vista.getjTableUsuariosClientes().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        try {
            articuloDAO.cargaArticulos();
            Object[] datos = new Object[6];
            for (Articulo a : articuloDAO.getListaArticulos()) {
                datos[0] = a.getId();
                datos[1] = a.getNombre();
                datos[2] = a.getDescripcion();
                datos[3] = a.getStock();
                datos[4] = a.getPrecioUnitario();
                datos[5] = a.getImpuesto();
                miTableModel.addRow(datos);
            }
        } catch (Exception SQLException) {
            JOptionPane.showMessageDialog(vista, "Problema al cargar la lista de la Base de Datos", "Error conexion Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void eliminaArticulo() {
        try {
            int id = Integer.parseInt(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString());
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1).toString();
            String descripcion = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2).toString();
            int stock = Integer.parseInt(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3).toString());
            double precioUnitario = Double.parseDouble(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4).toString());
            double impuesto = Double.parseDouble(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 5).toString());
            Articulo a = new Articulo(id, nombre, descripcion, stock, precioUnitario, impuesto);
            articuloDAO.eliminarArticulo(a);
            rellenaTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun articulo", "Error de articulo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificaArticulo() {
        try {
            int id = Integer.parseInt(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString());
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1).toString();
            String descripcion = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2).toString();
            int stock = Integer.parseInt(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3).toString());
            double precioUnitario = Double.parseDouble(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4).toString());
            double impuesto = Double.parseDouble(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 5).toString());
            Articulo a = new Articulo(id, nombre, descripcion, stock, precioUnitario, impuesto);
            articuloDAO.modificarArticulo(a);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun articulo", "Error de articulo", JOptionPane.ERROR_MESSAGE);
        }

    }

}
