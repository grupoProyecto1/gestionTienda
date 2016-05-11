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
    private boolean editable = false;
    private horarioDAO horario = new horarioDAO();
    private Usuario usuarioLoguedo;

    public Usuario getUsuarioLoguedo() {
        return usuarioLoguedo;
    }

    public void setUsuarioLoguedo(Usuario usuarioLoguedo) {
        this.usuarioLoguedo = usuarioLoguedo;
    }

    public ControladorJDAnadirHorario(JDAnadirHorario vista) {
        this.vista = vista;
        combo();
    }

    public void combo() {
        UsuarioDAO usuariodao = new UsuarioDAO();
        try {
            usuariodao.cargaUsuarios();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar la lista de usuarios", "Error al cargar el usuario", JOptionPane.ERROR_MESSAGE);
        }
        
        vista.getjComboBoxUsuario().removeAllItems();

        for (Usuario u : usuariodao.getListaUsuarios()) {
            vista.getjComboBoxUsuario().addItem(u.getNombre());
        }

    }

    public String getFechaInicio() {

        int ano = vista.getjDateChooserFecha().getCalendar().get(Calendar.YEAR);
        int mes = vista.getjDateChooserFecha().getCalendar().get(Calendar.MONTH);
        int dia = vista.getjDateChooserFecha().getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = Integer.parseInt(vista.getjSpinnerHoraInicio().getValue().toString());
        int minutos = Integer.parseInt(vista.getjSpinnerMinsInicio().getValue().toString());
        int segundos = Integer.parseInt(vista.getjSpinnerSecInicio().getValue().toString());

        String fecha = ano + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":" + segundos;
        System.out.println(fecha);
        return fecha;
    }

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
    public void anadirHorario(){
        horarioDAO horarioDAO = new horarioDAO();
        horario h1 = new horario(getFechaInicio(), getFechaFin(),vista.getjComboBoxUsuario().getSelectedItem().toString(), vista.getjTextField1().getText());
        try {
            horarioDAO.anadirHorario(h1);
            limpiaDatos();
            JOptionPane.showMessageDialog(vista, "Horario añadido satisfactoriamente", "Horario creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al añadir el horario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void limpiaDatos(){
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

}