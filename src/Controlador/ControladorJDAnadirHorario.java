/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.horarioDAO;
import java.util.Calendar;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.horario;
import Vista.JDAnadirHorario;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joaquin
 */
public class ControladorJDAnadirHorario {

    private JDAnadirHorario vista;
    private horarioDAO horario = new horarioDAO();
    private Usuario usuarioLogueado;

    /**
     * Constructor parametrizado para establecer el usuario logueado
     *
     * @param usuarioLogueado objeto de tipo usuario
     */
    public ControladorJDAnadirHorario(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     *Metodo para crear la vista JDAnadirHorario
     */
    public void creaVista() {
        this.vista = new JDAnadirHorario(null, true);
        vista.setControlador(this);
        combo();
        vista.setVisible(true);
    }

    /**
     * Establece el usuario logueado
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public void setUsuarioLoguedo(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * Metodo para añadir al jcombobox los nombres de usuarios de la base de
     * datos
     */
    public void combo() {
        vista.getjComboBoxUsuario().removeAllItems();
        try {
            UsuarioDAO usuariodao = new UsuarioDAO();
            usuariodao.cargaUsuarios();
            for (Usuario u : usuariodao.getListaUsuarios()) {
                vista.getjComboBoxUsuario().addItem(u.getNombre());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar la lista de usuarios", "Error al cargar el usuario", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo que devuelve la fecha de inicio con los datos de la vista
     *
     * @return String fecha
     */
    public String getFechaInicio() {
        int ano = vista.getjDateChooserFecha().getCalendar().get(Calendar.YEAR);
        int mes = vista.getjDateChooserFecha().getCalendar().get(Calendar.MONTH);
        int dia = vista.getjDateChooserFecha().getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = Integer.parseInt(vista.getjSpinnerHoraInicio().getValue().toString());
        int minutos = Integer.parseInt(vista.getjSpinnerMinsInicio().getValue().toString());
        int segundos = Integer.parseInt(vista.getjSpinnerSecInicio().getValue().toString());
        String fecha = ano + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":" + segundos;
        return fecha;
    }

    /**
     * Metodo que devuelve la fecha de finalizacion con los datos de la vista
     *
     * @return String fecha
     */
    public String getFechaFin() {
        int ano = vista.getjDateChooserFechaFinal().getCalendar().get(Calendar.YEAR);
        int mes = vista.getjDateChooserFechaFinal().getCalendar().get(Calendar.MONTH);
        int dia = vista.getjDateChooserFechaFinal().getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = Integer.parseInt(vista.getjSpinnerHoraFin().getValue().toString());
        int minutos = Integer.parseInt(vista.getjSpinnerMinFin().getValue().toString());
        int segundos = Integer.parseInt(vista.getjSpinnerSecFin().getValue().toString());

        String fecha = ano + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":" + segundos;
        return fecha;
    }

    /**
     * Metodo para añadir a la base de datos el horario establecido en la vista
     */
    public void anadirHorario() {
        try {
            horarioDAO horarioDAO = new horarioDAO();
            horario h1 = new horario(getFechaInicio(), getFechaFin(), vista.getjComboBoxUsuario().getSelectedItem().toString(), vista.getjTextField1().getText());
            horarioDAO.anadirHorario(h1);
            limpiaDatos();
            JOptionPane.showMessageDialog(vista, "Horario añadido satisfactoriamente", "Horario creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(vista, "Error al añadir el horario, asegurate de añadir todos los campos correctamente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo para limpiar los datos de la vista
     */
    public void limpiaDatos() {
        vista.getjComboBoxUsuario().setSelectedIndex(0);
        vista.getjTextField1().setText("");
        vista.getjDateChooserFecha().setCalendar(null);
        vista.getjDateChooserFechaFinal().setCalendar(null);
        vista.getjSpinnerHoraFin().setValue(new Integer(1));
        vista.getjSpinnerHoraInicio().setValue(new Integer(1));
        vista.getjSpinnerMinFin().setValue(new Integer(1));
        vista.getjSpinnerMinsInicio().setValue(new Integer(1));
        vista.getjSpinnerSecFin().setValue(new Integer(1));
        vista.getjSpinnerSecInicio().setValue(new Integer(1));

    }

    /**
     * Metodo para cerrar la ventana
     */
    public void volver() {
        vista.dispose();
    }

}
