/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Usuario;
import Vista.JDTablaUsuariosClientesProveedorArticulo;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class ControladorJDTablaClientes {

    private JDTablaUsuariosClientesProveedorArticulo vista;
    private boolean editable = false;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private Usuario usuarioLogueado;
    private int botones;

    /**
     * Constructor parametrizado que establece la vista
     *
     * @param vista
     */
    public ControladorJDTablaClientes(Usuario usuarioLogueado,int botones) {
        this.usuarioLogueado = usuarioLogueado;
        this.botones = botones;
        creaVista();
    }
    
    public void creaVista(){
        this.vista = new JDTablaUsuariosClientesProveedorArticulo(botones, 1);
        vista.setControladorClientes(this);
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
        miTableModel.addColumn("DNI");
        miTableModel.addColumn("Nombre");
        miTableModel.addColumn("Apellidos");
        miTableModel.addColumn("Telefono");
        miTableModel.addColumn("Direccion");
        miTableModel.addColumn("Email");
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
        try {
            clienteDAO.cargaCliente();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar los clientes en la tabla", "Error al cargar los clientes", JOptionPane.ERROR_MESSAGE);
        }
        
        Object[] datos = new Object[6];
        for (Cliente c : clienteDAO.getListaClientes()) {
            datos[0] = c.getDni();
            datos[1] = c.getNombre();
            datos[2] = c.getApellidos();
            datos[3] = c.getTelefono();
            datos[4] = c.getDireccion();
            datos[5] = c.getEmail();
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
     * Metodo que elimina el cliente seleccionado en la tabla
     */
    public void eliminaCliente() {
        try {
            String dni = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString();
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1).toString();
            String apellidos = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2).toString();
            int telefono = Integer.parseInt(vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3).toString());
            String direccion = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4).toString();
            String email = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 5).toString();
            Cliente c = new Cliente(dni, nombre, apellidos, telefono, direccion, email);
            clienteDAO.eliminarCliente(c);
            rellenaTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun cliente", "Error de cliente", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que actualiza el cliente de la base de datos con el modificado en
     * la tabla
     */
    public void modificaCliente() {
        try {
            String dni = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 0).toString();
            String nombre = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 1).toString();
            String apellidos = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 2).toString();
            int telefono = (int) vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 3);
            String direccion = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 4).toString();
            String email = vista.getjTableUsuariosClientes().getValueAt(vista.getjTableUsuariosClientes().getSelectedRow(), 5).toString();
            Cliente c = new Cliente(dni, nombre, apellidos, telefono, direccion, email);
            clienteDAO.modificarCliente(c);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun cliente", "Error de cliente", JOptionPane.ERROR_MESSAGE);
        }
    }
}
