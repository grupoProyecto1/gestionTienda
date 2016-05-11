/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Articulo;
import Modelo.ArticuloDAO;
import Modelo.Usuario;
import Vista.JDAnadirArticulo;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joaquin
 */
public class ControladorJDAnadirArticulo {
    private JDAnadirArticulo vista;
    private Usuario usuarioLogueado;
    
    public ControladorJDAnadirArticulo (Usuario usuarioLoguado){
        this.usuarioLogueado = usuarioLoguado;
       creaVista();
    }
    public void creaVista(){
        this.vista = new JDAnadirArticulo(null, true);
        vista.setControlador(this);
        vista.setVisible(true);
    }
    public void comprobador(){
        try {
        ArticuloDAO articulodao = new ArticuloDAO();
        Articulo a1 = new Articulo(0,vista.getjTextFieldNombreArticulo1().getText(),vista.getjTextFieldDescripcionArticulo().getText(), Integer.parseInt(vista.getjTextFieldStockArticulo().getText()), Double.parseDouble(vista.getjTextFieldPrecioUnitarioArticulo().getText()), Double.parseDouble(vista.getjTextFieldImpuestoArticulo().getText()));
        articulodao.anadirArticulo(a1);
        limpiaDatos();
        JOptionPane.showMessageDialog(vista, "Se ha añadido satisfactoriamente", "Articulo creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException |NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "No se ha podido insertar el articulo, asegurese de que todos los campos estan correctamente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }
    public void limpiaDatos(){
        vista.getjTextFieldDescripcionArticulo().setText("");
        vista.getjTextFieldImpuestoArticulo().setText("");
        vista.getjTextFieldNombreArticulo1().setText("");
        vista.getjTextFieldPrecioUnitarioArticulo().setText("");
        vista.getjTextFieldStockArticulo().setText("");
    }
    
}
