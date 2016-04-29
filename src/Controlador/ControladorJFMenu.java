/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionUsuarios;
import Vista.JFMenu;

/**
 *
 * @author Mario
 */
public class ControladorJFMenu {

    private JFMenu vista;
    private Usuario usuarioLogueado;

    public ControladorJFMenu(JFMenu vista) {
        this.vista = vista;
    }

     public void gestionUsuarios(){
         new JFGestionUsuarios().setVisible(true);
         vista.dispose();
    }
       
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
}
