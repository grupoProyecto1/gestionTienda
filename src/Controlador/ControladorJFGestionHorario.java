/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionHorario;

/**
 *
 * @author Joaquin
 */
public class ControladorJFGestionHorario {

    private JFGestionHorario vista;
    private Usuario usuarioLogueado;
    private ControladorJDTablaHorario cjdth;

    /**
     * Constructor parametrizado que crea un objeto de tipo controlador con el
     * usuario logueado
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public ControladorJFGestionHorario(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista de gestion horario
     */
    public void creaVista() {
        this.vista = new JFGestionHorario();
        vista.setControlador(this);
        if(usuarioLogueado.isAdmin()){
            vista.getjButtonBorrarHorario().setVisible(true);
            vista.getjButtonAnadirHorario().setVisible(true);
        }
        
        vista.setVisible(true);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de añadir
     * horario
     */
    public void anadirHorario() {
        ControladorJDAnadirHorario cjdah = new ControladorJDAnadirHorario(usuarioLogueado);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de borrar
     * horario
     */
    public void borrarHorario() {
        cjdth = new ControladorJDTablaHorario(usuarioLogueado,1);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de ver
     * horario
     */
    public void verHorario() {
        cjdth = new ControladorJDTablaHorario(usuarioLogueado,0);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de menu
     */
    public void volver() {
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }

}
