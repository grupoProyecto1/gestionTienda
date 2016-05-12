/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.swing.table.DefaultTableModel;
import Vista.JDTablaHorario;
import Modelo.horario;
import Modelo.horarioDAO;
import Modelo.Usuario;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joaquin
 */
public class ControladorJDTablaHorario {

    private JDTablaHorario vista;
    private horarioDAO horariodao = new horarioDAO();
    private Usuario usuarioLogueado;
    private int botonBorrar;

    /**
     * Constructor parametrizado que crea un objeto de tipo
     * controladorjdtablahorario
     *
     * @param usuarioLogueado objeto de tipo usuario
     * @param botonBorrar int 1 para mostrar el boton
     */
    public ControladorJDTablaHorario(Usuario usuarioLogueado, int botonBorrar) {
        this.usuarioLogueado = usuarioLogueado;
        this.botonBorrar = botonBorrar;
        creaVista();
    }

    /**
     * Metodo para crear la vista de jdtablahorario
     */
    public void creaVista() {
        this.vista = new JDTablaHorario(null, true);
        vista.setControlador(this);
        creaTabla();
        rellanaTabla();
        if (usuarioLogueado.isAdmin() && botonBorrar == 1) {
            vista.getjButtonBorrar().setVisible(true);
        }
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
     * Metodo para crear las columnas de la tabla y establecer el modelo
     */
    public void creaTabla() {
        miTableModel.addColumn("Fecha de Incio");
        miTableModel.addColumn("Fecha Finalizacion");
        if (usuarioLogueado.isAdmin()) {
            miTableModel.addColumn("Usuario");
        }
        miTableModel.addColumn("Descripcion");
        vista.setjTableHorario(miTableModel);
        vista.getjTableHorario().setAutoCreateRowSorter(true);
    }

    /**
     * Metodo para rellenar la tabla con los horarios de la base de datos
     */
    public void rellanaTabla() {
        for (int i = 0; i < vista.getjTableHorario().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }
        if (usuarioLogueado.isAdmin()) {
            try {
                horariodao.cargaHorarios();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(vista, "Error al cargar la lista de horarios en la tabla", "Error al cargar la lista", JOptionPane.ERROR_MESSAGE);
            }

            Object[] datos = new Object[4];

            for (horario h : horariodao.getListaHorarios()) {
                datos[2] = h.getUsuario();
                datos[0] = h.getFechaInicio();
                datos[1] = h.getFechaFin();
                datos[3] = h.getDescripcion();
                miTableModel.addRow(datos);
            }

        } else {
            try {
                horariodao.cargaHorarios();
                horariodao.cargaHorarioUsuario(usuarioLogueado);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(vista, "Error al cargar la lista de horarios en la tabla", "Error al cargar la lista", JOptionPane.ERROR_MESSAGE);
            }
            Object[] datos = new Object[3];

            for (horario h : horariodao.getListaHorarios()) {
                datos[0] = h.getFechaInicio();
                datos[1] = h.getFechaFin();
                datos[2] = h.getDescripcion();
                miTableModel.addRow(datos);
            }
        }
    }

    /**
     * Metodo para eliminar el horario seleccionado de la base de datos
     */
    public void eliminaHorario() {
        try {
            String fechaInicio = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 0).toString();
            String fechaFin = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 1).toString();
            String usuario = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 2).toString();
            String descripcion = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 3).toString();
            horario h = new horario(fechaInicio, fechaFin, usuario, descripcion);
            horariodao.eliminarHorario(h);
            rellanaTabla();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun horario", "Error de horario", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Establece el usuario logueado
     *
     * @param usuarioLogueado objeto de tipo usuario
     */
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * Metodo para crear una ventana de tipo jfgestionhorario
     */
    public void volver() {
        vista.dispose();
    }

}
