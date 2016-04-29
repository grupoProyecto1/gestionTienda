/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.JFGestionClientes;
import Vista.JDTablaUsuariosClientes;
import Vista.JDAnadirCliente;


/**
 *
 * @author Mario
 */
public class ControladorJFGestionClientes {
    private JFGestionClientes vista;
    
    /**
     * Constructor con un parametro para establecer la vista
     *
     * @param vista
     */
    public ControladorJFGestionClientes(JFGestionClientes vista) {
        this.vista = vista;
    }
    
    /**
     * Metodo que crea una ventana para añadir el cliente
     */
    public void anadirCliente() {
        new JDAnadirCliente(vista, true).setVisible(true);
    }
    
    /**
     * Metodo que crea una ventana para borrar clientes
     */
    public void borrarClientes() {
        new JDTablaUsuariosClientes(1, 1).setVisible(true);
    }
    
    /**
     * Metodo que crea una ventana para modificar los clientes
     */
    public void modificarClientes() {
        new JDTablaUsuariosClientes(2, 1).setVisible(true);
    }
    
    /**
     * Metodo que crea una ventana para ver los clientes
     */
    public void verClientes() {
        new JDTablaUsuariosClientes(0, 1).setVisible(true);
    }
    
}
