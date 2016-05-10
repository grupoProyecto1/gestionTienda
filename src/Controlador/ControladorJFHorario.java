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
import Vista.JFHorario;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joaquin
 */
public class ControladorJFHorario {

    private JFHorario vista;
    private boolean editable = false;
    private horarioDAO horario = new horarioDAO();
    private Usuario usuarioLoguedo;

    public Usuario getUsuarioLoguedo() {
        return usuarioLoguedo;
    }

    public void setUsuarioLoguedo(Usuario usuarioLoguedo) {
        this.usuarioLoguedo = usuarioLoguedo;
    }

    public ControladorJFHorario(JFHorario vista) {
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

    public void getFechaInicio() {

        int ano = vista.getjDateChooserFecha().getCalendar().get(Calendar.YEAR);
        int mes = vista.getjDateChooserFecha().getCalendar().get(Calendar.MONTH);
        int dia = vista.getjDateChooserFecha().getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = Integer.parseInt(vista.getjSpinnerHoraInicio().getValue().toString());
        int minutos = Integer.parseInt(vista.getjSpinnerMinsInicio().getValue().toString());
        int segundos = Integer.parseInt(vista.getjSpinnerSecInicio().getValue().toString());

        String fecha = ano + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":" + segundos;
        System.out.println(fecha);
    }

    public void getFechaFin() {
        int ano = vista.getjDateChooserFechaFinal().getCalendar().get(Calendar.YEAR);
        int mes = vista.getjDateChooserFechaFinal().getCalendar().get(Calendar.MONTH);
        int dia = vista.getjDateChooserFechaFinal().getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = Integer.parseInt(vista.getjSpinnerHoraFin().getValue().toString());
        int minutos = Integer.parseInt(vista.getjSpinnerMinFin().getValue().toString());
        int segundos = Integer.parseInt(vista.getjSpinnerSecFin().getValue().toString());

        String fecha = ano + "-" + mes + "-" + dia + " " + hora + ":" + minutos + ":" + segundos;
        System.out.println(fecha);
    }

}
