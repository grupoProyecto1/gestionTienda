/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JFGestionClientes;
import Vista.JDTablaUsuariosClientesProveedorArticulo;
import Vista.JDAnadirCliente;


/**
 *
 * @author Mario
 */
public class ControladorJFGestionClientes {
    private JFGestionClientes vista;
    private Usuario usuarioLogueado;
    
    /**
     * Constructor con un parametro para establecer la vista
     *
     * @param vista
     */
    public ControladorJFGestionClientes(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }
    
    public void creaVista() {
        this.vista = new JFGestionClientes();
        vista.setControlador(this);
        vista.setVisible(true);
    }
    
    /**
     * Metodo que crea una ventana para añadir el cliente
     */
    public void anadirCliente() {
        ControladorJDAnadirCliente cjdac = new ControladorJDAnadirCliente(usuarioLogueado);
    }
    
    /**
     * Metodo que crea una ventana para borrar clientes
     */
    public void borrarClientes() {
        ControladorJDTablaClientes cjdtc = new ControladorJDTablaClientes(usuarioLogueado, 1);
    }
    
    /**
     * Metodo que crea una ventana para modificar los clientes
     */
    public void modificarClientes() {
        ControladorJDTablaClientes cjdtc = new ControladorJDTablaClientes(usuarioLogueado, 2);
    }
    
    /**
     * Metodo que crea una ventana para ver los clientes
     */
    public void verClientes() {
        ControladorJDTablaClientes cjdtc = new ControladorJDTablaClientes(usuarioLogueado, 0);
    }
    
    public void volver(){
        ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
        vista.dispose();
    }
    
}
