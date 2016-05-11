/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JDTablaHorario;
import Vista.JDVentas;
import Vista.JFMenu;
import Vista.JFGestionClientes;

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
     * @param vista
     */
    public ControladorJFMenu(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    public void creaVista() {
        this.vista = new JFMenu();
        vista.setControlador(this);
        if (usuarioLogueado.isAdmin()) {
            vista.getjButtonUsuarios().setVisible(true);
            vista.getjButtonClientes().setVisible(true);
        }
        vista.setVisible(true);
    }

    /**
     * Metodo que crea una ventana JFGestionUsuarios, la hace visible y elimina
     * la ventana en la que nos encontramos
     */
    public void gestionUsuarios() {
        ControladorJFGestionUsuarios cjfgu = new ControladorJFGestionUsuarios(usuarioLogueado);
        vista.dispose();
    }

    public void gestionHorarios() {
       ControladorJFGestionHorario cjfgh = new ControladorJFGestionHorario(usuarioLogueado);
       vista.dispose();       
    }

    public void gestionVentas() {
        ControladorJDVentas cjdv = new ControladorJDVentas(usuarioLogueado);
    }

    /**
     * Metedo que crea una ventana JFGestionCliente, la hace visible y elmina la
     * ventana en la que nos encontramos
     */
    public void gestionCliente() {
        ControladorJFGestionClientes cjfgc = new ControladorJFGestionClientes(usuarioLogueado);
        vista.dispose();
    }
   
    public void gestionProveedores(){
        ControladorJFGestionProveedores cjfgp = new ControladorJFGestionProveedores(usuarioLogueado);
        vista.dispose();
    }

}
