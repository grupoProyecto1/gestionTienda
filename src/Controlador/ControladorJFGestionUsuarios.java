/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.JDAnadirUsuario;
import Vista.JDTablaUsuariosClientes;
import Vista.JFGestionUsuarios;

/**
 *
 * @author Mario
 */
public class ControladorJFGestionUsuarios {
    private JFGestionUsuarios vista;
    
    public ControladorJFGestionUsuarios(JFGestionUsuarios vista){
        this.vista = vista;
    }
    public void anadirUsuario(){
        new JDAnadirUsuario(vista, true).setVisible(true);
    }
    public void borrarUsuarios(){
        new JDTablaUsuariosClientes(1, 0).setVisible(true);
    }
    public void modificarUsuarios(){
        new JDTablaUsuariosClientes(2, 0).setVisible(true);
    }
    public void verUsuarios(){
        new JDTablaUsuariosClientes(0, 0).setVisible(true);
    }
}
