/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.JDTablaUsuariosClientes;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class ControladorJDTablaUsuarios {

    private JDTablaUsuariosClientes vista;
    private boolean editable = false;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Constructor parametrizado que establece la vista
     *
     * @param vista
     */
    public ControladorJDTablaUsuarios(JDTablaUsuariosClientes vista) {
        this.vista = vista;
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
        miTableModel.addColumn("Nombre");
        miTableModel.addColumn("Admin");
        miTableModel.addColumn("VistaClientes");
        miTableModel.addColumn("VistaProductos");
        miTableModel.addColumn("VistaProveedores");
        miTableModel.addColumn("VistaUsuarios");
        vista.setjTableUsuariosClientes(miTableModel);
        vista.getjTableUsuariosClientes().setAutoCreateRowSorter(true);

    }

    /**
     * Metodo para rellenar la tabla creada
     */
    public void rellenaTabla() {
        for (int i = 0; i < vista.getjTableUsuariosClientes().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        usuarioDAO.cargaUsuarios();
        Object[] datos = new Object[6];
        for (Usuario u : usuarioDAO.getListaUsuarios()) {
            datos[0] = u.getNombre();
            datos[1] = u.isAdmin();
            datos[2] = u.isVistaClientes();
            datos[3] = u.isVistaProductos();
            datos[4] = u.isVistaProveedores();
            datos[5] = u.isVistaUsuarios();
            miTableModel.addRow(datos);
        }
    }

    /**
     * Devuelve si las columnas son editables excepto la primera, o ninguna es
     * editable
     *
     * @return
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Establece si las columnas son editables excepto la primera, o ninguna es
     * editable
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * Metodo que elimina el usuario seleccionado en la tabla
     */
    public void eliminaUsuario() {
        try {
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString();
            Boolean admin = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1);
            Boolean vistaClientes = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2);
            Boolean vistaProductos = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3);
            Boolean vistaProveedores = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4);
            Boolean vistaUsuarios = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 5);
            Usuario u = new Usuario(nombre, null,admin, vistaClientes, vistaProductos, vistaProveedores, vistaUsuarios);
            usuarioDAO.eliminarUsuarios(u);
            rellenaTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun usuario", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que actualiza el usuario de la base de datos con el modificado en
     * la tabla
     */
    public void modificaUsuario() {
        try {
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString();
            Boolean admin = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1);
            Boolean vistaClientes = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2);
            Boolean vistaProductos = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3);
            Boolean vistaProveedores = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4);
            Boolean vistaUsuarios = (Boolean) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 5);
            Usuario u = new Usuario(nombre, null,admin, vistaClientes, vistaProductos, vistaProveedores, vistaUsuarios);
            usuarioDAO.modificarUsuarios(u);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun usuario", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }
}
