/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionArticulos;

/**
 *
 * @author Mario
 */
public class ControladorJFGestionArticulos {

    private JFGestionArticulos vista;
    private Usuario usuarioLogueado;

    /**
     *
     * @param usuarioLogueado
     */
    public ControladorJFGestionArticulos(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista de jfgestionarticulos
     */
    public void creaVista() {
        this.vista = new JFGestionArticulos();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de añadir
     * articulos
     */
    public void anadirArticulos() {
        ControladorJDAnadirArticulo cjdaa = new ControladorJDAnadirArticulo(usuarioLogueado);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de borrar
     * articulos
     */
    public void borrarArticulo() {
        ControladorJDTablaArticulos cjdta = new ControladorJDTablaArticulos(usuarioLogueado, 1);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * modificar articulos
     */
    public void modificarArticulos() {
        ControladorJDTablaArticulos cjdta = new ControladorJDTablaArticulos(usuarioLogueado, 2);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de ver
     * articulos
     */
    public void verArticulos() {
        ControladorJDTablaArticulos cjdta = new ControladorJDTablaArticulos(usuarioLogueado, 0);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de menu
     */
    public void volver() {
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }
}
