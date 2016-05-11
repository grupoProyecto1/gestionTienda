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

    public ControladorJFGestionProveedores(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        crearVista();
    }

    public void crearVista() {
        this.vista = new JFGestionProveedores();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo que crea una ventana para añadir el proveedor
     */
    public void anadirProveedor() {
        ControladorJDAnadirProveedor cjdap = new ControladorJDAnadirProveedor(usuarioLogueado);
    }

    /**
     * Metodo que crea una ventana para borrar proveedor
     */
    public void borrarProveedor() {
        ControladorJDTablaProveedor cjdtp = new ControladorJDTablaProveedor(usuarioLogueado, 1);
    }

    /**
     * Metodo que crea una ventana para modificar los proveedores
     */
    public void modificarProveedor() {
        ControladorJDTablaProveedor cjdtp = new ControladorJDTablaProveedor(usuarioLogueado, 2);
    }

    /**
     * Metodo que crea una ventana para ver los proveedores
     */
    public void verProveedor() {
        ControladorJDTablaProveedor cjdtp = new ControladorJDTablaProveedor(usuarioLogueado, 0);
    }
    
    public void volver(){
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }
}
