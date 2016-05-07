/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Vista.JDTablaHorario;
import Vista.JDVentas;
import Vista.JFGestionUsuarios;
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
    public ControladorJFMenu(JFMenu vista) {
        this.vista = vista;
    }

    /**
     * Metodo que crea una ventana JFGestionUsuarios, la hace visible y elimina
     * la ventana en la que nos encontramos
     */
    public void gestionUsuarios() {
        JFGestionUsuarios jfgu = new JFGestionUsuarios();
        jfgu.getControlador().setUsuarioLogueado(usuarioLogueado);
        jfgu.setVisible(true);
        vista.dispose();
    }
    
    public void gestionHorarios(){
        new JDTablaHorario(vista, true, usuarioLogueado).setVisible(true);
    }

    public void gestionVentas(){
        new JDVentas(vista, true).setVisible(true);
    }
    /**
     * Metodo para establecer el usuario que se ha logueado
     *
     * @param usuarioLogueado
     */
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        compruebaUsuario();
    }
    
    /**
     *Metedo que crea una ventana JFGestionCliente, la hace visible y elmina
     * la ventana en la que nos encontramos
     */
    public void gestionCliente() {
       new JFGestionClientes().setVisible(true);
        vista.dispose();
    }

    public void compruebaUsuario(){
        if(usuarioLogueado.isAdmin()){
            vista.getjButtonUsuarios().setVisible(true);
            vista.getjButtonClientes().setVisible(true);      
        }
        vista.repaint();
    }
    
}
