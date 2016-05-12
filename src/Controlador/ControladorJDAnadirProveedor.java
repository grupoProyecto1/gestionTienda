/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Modelo.Usuario;
import Vista.JDAnadirProveedor;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ControladorJDAnadirProveedor {

    private JDAnadirProveedor vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor parametrizado que establece el usuario logueado
     *
     * @param usuarioLogueado objeto de tipo usuario
     */
    public ControladorJDAnadirProveedor(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    /**
     * Metodo que crea la vista para jdanadirproveedor
     */
    public void creaVista() {
        this.vista = new JDAnadirProveedor(null, true);
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo para comprobar si introdujo un cliente valido y si las contraseñas
     * son iguales, finalmente se agrega el cliente a la BD
     */
    public void comprobador() {
        //if para comprobaciones de los componentes
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        Proveedor p1 = new Proveedor(vista.getjTextFieldNifProveedor().getText(), vista.getjTextFieldNombreProveedor().getText(), vista.getjTextFieldDireccionProveedor().getText(), Integer.valueOf(vista.getjTextFieldTelefonoProveedor().getText()), vista.getjTextFieldEmailProveedor().getText());
        try {
            proveedorDAO.anadirProveedor(p1);
            limpiaDatos();
            JOptionPane.showMessageDialog(vista, "Proveedor añadido satisfactoriamente", "Proovedor creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "El Proveedor no ha podido ser añadido o ya existe", "Error al Crear el Proveedor", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que comprueba la valided del NIF
     *
     */
    public void checkNif() {
        String nif = vista.getjTextFieldNifProveedor().getText();
        try {
            int numNif = Integer.valueOf(nif.substring(0, 8));
            char letraNif = nif.charAt(8);
            int resto = numNif % 23;
            String letrasMayus = "TRWAGMYFPDXBNJZSQVHLCKE";
            String letrasMin = "trwagmyfpdxbnjzsqvhlcke";
            if (letraNif != letrasMayus.charAt(resto) && letraNif != letrasMin.charAt(resto)) {
                JOptionPane.showMessageDialog(vista, "El NIF no es correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(vista, "NIF vacio", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Longitud del NIF invalida", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo para limpiar los datos que hay rellenos en el formulario
     */
    public void limpiaDatos() {
        vista.getjTextFieldNombreProveedor().setText("");
        vista.getjTextFieldNifProveedor().setText("");
        vista.getjTextFieldTelefonoProveedor().setText("");
        vista.getjTextFieldDireccionProveedor().setText("");
        vista.getjTextFieldEmailProveedor().setText("");
    }
}
