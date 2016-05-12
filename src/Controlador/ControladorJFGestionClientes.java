/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionClientes;

/**
 *
 * @author Mario
 */
public class ControladorJFGestionClientes {

    private JFGestionClientes vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor parametrizado para crear un objeto de tipo controlador con el
     * usuario logueado
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */ 
    public ControladorJFGestionClientes(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista de gestion clientes
     */
    public void creaVista() {
        this.vista = new JFGestionClientes();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de añadir
     * clientes
     */
    public void anadirCliente() {
        ControladorJDAnadirCliente cjdac = new ControladorJDAnadirCliente(usuarioLogueado);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de borrar
     * clientes
     */
    public void borrarClientes() {
        ControladorJDTablaClientes cjdtc = new ControladorJDTablaClientes(usuarioLogueado, 1);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * modificar clientes
     */
    public void modificarClientes() {
        ControladorJDTablaClientes cjdtc = new ControladorJDTablaClientes(usuarioLogueado, 2);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de ver
     * clientes
     */
    public void verClientes() {
        ControladorJDTablaClientes cjdtc = new ControladorJDTablaClientes(usuarioLogueado, 0);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de menu
     */
    public void volver() {
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }

}
