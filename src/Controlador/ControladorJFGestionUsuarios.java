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
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public ControladorJFGestionUsuarios(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista para gestion del usuario
     */
    public void creaVista() {
        this.vista = new JFGestionUsuarios();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de añadir
     * usuario
     */
    public void anadirUsuario() {
        ControladorJDAnadirUsuario cjdau = new ControladorJDAnadirUsuario(usuarioLogueado);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de borrar
     * usuario
     */
    public void borrarUsuarios() {
        cjdtu = new ControladorJDTablaUsuarios(usuarioLogueado, 1);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * modificar usuario
     */
    public void modificarUsuarios() {
        cjdtu = new ControladorJDTablaUsuarios(usuarioLogueado, 2);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de ver
     * usuarios
     */
    public void verUsuarios() {
        cjdtu = new ControladorJDTablaUsuarios(usuarioLogueado, 0);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de menu
     */
    public void volver() {
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }

    /**
     * Establece el usuario que se ha logueado
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
