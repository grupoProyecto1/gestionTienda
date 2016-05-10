package Controlador;

import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Vista.JDTablaUsuariosClientes;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joaquin
 */
public class ControladorJDTablaProveedor {

    private JDTablaUsuariosClientes vista;
    private boolean editable = false;
    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    public ControladorJDTablaProveedor(JDTablaUsuariosClientes vista) {
        this.vista = vista;
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

    /**
     * Metodo que crea el modelo de tabla y lo establece
     */
    public void creaTabla() {
        miTableModel.addColumn("NIF");
        miTableModel.addColumn("Nombre");
        miTableModel.addColumn("Telefono");
        miTableModel.addColumn("Direccion");
        miTableModel.addColumn("Email");
        vista.setjTableUsuariosClientes(miTableModel);
        vista.getjTableUsuariosClientes().setAutoCreateRowSorter(true);

    }

    public void rellenaTabla() {
        for (int i = 0; i < vista.getjTableUsuariosClientes().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        try {
            proveedorDAO.cargaProveedorDAO();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar la lista de proveedores en la tabla", "Error al cargar los proveedores", JOptionPane.ERROR_MESSAGE);
        }

        Object[] datos = new Object[6];
        for (Proveedor p : proveedorDAO.getListaProveedores()) {
            datos[0] = p.getNif();
            datos[1] = p.getNombre();
            datos[2] = p.getTelefono();
            datos[3] = p.getDireccion();
            datos[4] = p.getEmail();
            miTableModel.addRow(datos);
        }

    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void eliminaProveedor() {
        try {
            String dni = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString();
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1).toString();

            int telefono = Integer.parseInt(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2).toString());
            String direccion = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3).toString();
            String email = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4).toString();
            Proveedor p = new Proveedor(dni, nombre, direccion, telefono, email);
            proveedorDAO.eliminarProveedores(p);
            rellenaTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun proveedor", "Error de Proveedor", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificaProveedor() {
        try {
            String dni = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString();
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1).toString();

            int telefono = (int) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2);
            String direccion = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3).toString();
            String email = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4).toString();
            Proveedor p = new Proveedor(dni, nombre, direccion, telefono, email);
            proveedorDAO.modificarProveedor(p);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun proveedor", "Error de proveedor", JOptionPane.ERROR_MESSAGE);
        }
    }

}
