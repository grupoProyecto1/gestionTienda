package Controlador;

import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Modelo.Usuario;
import Vista.JDTablaUsuariosClientesProveedorArticulo;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joaquin
 */
public class ControladorJDTablaProveedor {

    private JDTablaUsuariosClientesProveedorArticulo vista;
    private boolean editable = false;
    private ProveedorDAO proveedorDAO = new ProveedorDAO();
    private Usuario usuarioLogueado;
    private int botones;

    /**
     * Constructor parametrizado que crea un objeto de tipo
     * controladorjdtablaproveedor
     *
     * @param usuarioLogueado objeto de tipo Usuario
     * @param botones int para mostrar los botones
     */
    public ControladorJDTablaProveedor(Usuario usuarioLogueado, int botones) {
        this.usuarioLogueado = usuarioLogueado;
        this.botones = botones;
        creaVista();
    }

    /**
     * Metodo que crea la vista para jdtablaproveedor
     */
    public void creaVista() {
        this.vista = new JDTablaUsuariosClientesProveedorArticulo(botones, 2);
        vista.setControladorProveedor(this);
        vista.setTitle("Proveedores");
        if (botones == 2) {
            editable = true;
        }
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

    /**
     * Metodo que rellena la tabla con los datos de los proveedores de la base
     * de datos
     */
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

    /**
     * Devuelve si es editable toda la tabla
     *
     * @return boolean
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Establece si es editable toda la tabla
     *
     * @param editable boolean
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * Metodo para eliminar el proveedor seleccionado en la tabla, de la base de
     * datos
     */
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

    /**
     * Metodo para modificar en la base de datos el proveedor seleccionado en la
     * tabla
     */
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
