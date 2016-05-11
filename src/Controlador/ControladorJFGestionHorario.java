/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.JFGestionHorario;
import Vista.JDTablaHorario;
import Vista.JDAnadirHorario;

/**
 *
 * @author Joaquin
 */
public class ControladorJFGestionHorario {
    private JFGestionHorario vista;
    
    
    
    public ControladorJFGestionHorario(JFGestionHorario vista){
        this.vista = vista;
    }
    public void anadirHorario(){
        new JDAnadirHorario(vista, true).setVisible(true);
    }

    public void borrarHorario(){
        new JDTablaHorario(vista, true,1 );//muestra el boton de borrar
    }
    public void verHorario(){
        new JDTablaHorario(vista, true, 0);//no muestra botones A ESTO HAY QUE PASARLE EL USER PARA QUE FUNCIONE BIEN
    }
    
}
