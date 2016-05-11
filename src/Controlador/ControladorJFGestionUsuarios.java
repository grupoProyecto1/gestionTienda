/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionUsuarios;

/**
 *
 * @author Mario
 */
public class ControladorJFGestionUsuarios {

    private JFGestionUsuarios vista;
    private Usuario usuarioLogueado;
    private ControladorJDTablaUsuarios cjdtu;

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
        vista.setControlador(this);
        vista.setVisible(true);
    }
    
    /**
     * Metodo que crea una ventana para añadir el usuario
     */
    public void anadirUsuario() {
        ControladorJDAnadirUsuario cjdau = new ControladorJDAnadirUsuario(usuarioLogueado);
    }

    /**
     * Metodo que crea una ventana para borrar usuarios
     */
    public void borrarUsuarios() {
        cjdtu = new ControladorJDTablaUsuarios(usuarioLogueado, 1);
    }

    /**
     * Metodo que crea una ventana para modificar los usuarios
     */
    public void modificarUsuarios() {
        cjdtu = new ControladorJDTablaUsuarios(usuarioLogueado, 2);
    }

    /**
     * Metodo que crea una ventana para ver los usuarios
     */
    public void verUsuarios() {
        cjdtu = new ControladorJDTablaUsuarios(usuarioLogueado, 0);
    }
    
    public void volver(){
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    
}
