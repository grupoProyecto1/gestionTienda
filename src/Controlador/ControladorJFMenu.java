/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFMenu;

/**
 *
 * @author Mario
 */
public class ControladorJFMenu {

    private JFMenu vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor que establece la vista
     *
     * @param usuarioLogueado objeto de tipo Usuario
     */
    public ControladorJFMenu(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista de menu
     */
    public void creaVista() {
        this.vista = new JFMenu();
        vista.setControlador(this);
        if (usuarioLogueado.isAdmin()) {
            vista.getjButtonUsuarios().setVisible(true);
            vista.getjButtonClientes().setVisible(true);
            vista.getjButtonProveedores().setVisible(true);
            vista.getjButtonFacturas().setVisible(true);
        } else {
            if (usuarioLogueado.isVistaClientes()) {
                vista.getjButtonClientes().setVisible(true);
            }
            if (usuarioLogueado.isVistaUsuarios()) {
                vista.getjButtonUsuarios().setVisible(true);
            }
            if (usuarioLogueado.isVistaProveedores()) {
                vista.getjButtonProveedores().setVisible(true);
            }
            if (usuarioLogueado.isVistaProductos()) {
                vista.getjButtonVentas().setVisible(true);
            }
        }
        vista.setVisible(true);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * gestion usuarios
     */
    public void gestionUsuarios() {
        ControladorJFGestionUsuarios cjfgu = new ControladorJFGestionUsuarios(usuarioLogueado);
        vista.dispose();
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * gestion horario
     */
    public void gestionHorarios() {
        ControladorJFGestionHorario cjfgh = new ControladorJFGestionHorario(usuarioLogueado);
        vista.dispose();
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * gestion ventas
     */
    public void gestionVentas() {
        ControladorJDVentas cjdv = new ControladorJDVentas(usuarioLogueado);
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * gestion cliente
     */
    public void gestionCliente() {
        ControladorJFGestionClientes cjfgc = new ControladorJFGestionClientes(usuarioLogueado);
        vista.dispose();
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * gestion proveedores
     */
    public void gestionProveedores() {
        ControladorJFGestionProveedores cjfgp = new ControladorJFGestionProveedores(usuarioLogueado);
        vista.dispose();
    }

    /**
     * Metodo que crea el controlador que se encarga de crear la vista de
     * gestion facturas
     */
    public void gestionFacturas() {
        ControladorJDTablaFactura cjdtf = new ControladorJDTablaFactura(usuarioLogueado);
    }
}
