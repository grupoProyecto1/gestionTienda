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

    public ControladorJDTablaHorario(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public void creaVista() {
        this.vista = new JDTablaHorario(null, true);
        vista.setControlador(this);
        if (usuarioLogueado.isAdmin()) {
            vista.getjButtonBorrar().setVisible(true);
        }
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

    public void creaTabla() {
        miTableModel.addColumn("Fecha de Incio");
        miTableModel.addColumn("Fecha Finalizacion");
        miTableModel.addColumn("Descripcion");
        vista.setjTableHorario(miTableModel);
        vista.getjTableHorario().setAutoCreateRowSorter(true);

    }

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
                datos[0] = h.getFechaInicio();
                datos[1] = h.getFechaFin();
                datos[2] = h.getUsuario();
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

    public void eliminaHorario() {
        try {
            String fechaInicio = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 0).toString();
            String fechaFin = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 1).toString();
            String usuario = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 2).toString();
            String descripcion = vista.getjTableHorario().getValueAt(vista.getjTableHorario().getSelectedRow(), 3).toString();
            horario h = new horario(fechaInicio, fechaFin, usuario, descripcion);
            horariodao.eliminarHorario(h);
            rellanaTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No has seleccionado ningun horario", "Error de horario", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
