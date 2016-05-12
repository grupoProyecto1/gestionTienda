/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionProveedores;

/**
 *
 * @author Mario
 */
public class ControladorJFGestionProveedores {

    private JFGestionProveedores vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor parametrizado para crear un objeto de controlador con el
     * usuarioLogueado
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public ControladorJFGestionProveedores(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        crearVista();
    }

    /**
     * Metodo que crea la vista de la gestion de proveedores
     */
    public void crearVista() {
        this.vista = new JFGestionProveedores();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de añadir
     * proveedor
     */
    public void anadirProveedor() {
        ControladorJDAnadirProveedor cjdap = new ControladorJDAnadirProveedor(usuarioLogueado);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de borrar
     * proveedor
     */
    public void borrarProveedor() {
        ControladorJDTablaProveedor cjdtp = new ControladorJDTablaProveedor(usuarioLogueado, 1);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * modificar proveedor
     */
    public void modificarProveedor() {
        ControladorJDTablaProveedor cjdtp = new ControladorJDTablaProveedor(usuarioLogueado, 2);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de ver
     * proveedores
     */
    public void verProveedor() {
        ControladorJDTablaProveedor cjdtp = new ControladorJDTablaProveedor(usuarioLogueado, 0);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de menu
     */
    public void volver() {
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }
}
