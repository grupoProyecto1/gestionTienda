/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JDAnadirUsuario;
import Vista.JDTablaUsuariosClientes;
import Vista.JFGestionUsuarios;
import Vista.JFMenu;

/**
 *
 * @author Mario
 */
public class ControladorJFGestionUsuarios {

    private JFGestionUsuarios vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor con un parametro para establecer el usuarioLogueado
     *
     * @param usuarioLogueado
     */
    public ControladorJFGestionUsuarios(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    public void creaVista() {
        this.vista = new JFGestionUsuarios();      
        vista.setVisible(true);
    }
    
    /**
     * Metodo que crea una ventana para añadir el usuario
     */
    public void anadirUsuario() {
        new JDAnadirUsuario(vista, true).setVisible(true);
    }

    /**
     * Metodo que crea una ventana para borrar usuarios
     */
    public void borrarUsuarios() {
        new JDTablaUsuariosClientes(1, 0).setVisible(true);
    }

    /**
     * Metodo que crea una ventana para modificar los usuarios
     */
    public void modificarUsuarios() {
        new JDTablaUsuariosClientes(2, 0).setVisible(true);
    }

    /**
     * Metodo que crea una ventana para ver los usuarios
     */
    public void verUsuarios() {
        new JDTablaUsuariosClientes(0, 0).setVisible(true);
    }
    
    public void volver(){
        JFMenu jfm = new JFMenu();
        jfm.getControlador();
        jfm.setVisible(true);
        vista.dispose();
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    
}
