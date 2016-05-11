/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionHorario;
import Vista.JDTablaHorario;
import Vista.JDAnadirHorario;

/**
 *
 * @author Joaquin
 */
public class ControladorJFGestionHorario {

    private JFGestionHorario vista;
    private Usuario usuarioLogueado;
    private ControladorJDTablaHorario cjdth;

    public ControladorJFGestionHorario(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    public void creaVista() {
        this.vista = new JFGestionHorario();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    public void anadirHorario() {
        ControladorJDAnadirHorario cjdah = new ControladorJDAnadirHorario(usuarioLogueado);
    }

    public void borrarHorario() {
        cjdth = new ControladorJDTablaHorario(usuarioLogueado);
    }

    public void verHorario() {
        cjdth = new ControladorJDTablaHorario(usuarioLogueado);
    }
    
    public void volver(){
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }

}
