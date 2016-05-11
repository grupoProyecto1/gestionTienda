/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Usuario;
import Vista.JDAnadirCliente;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ControladorJDAnadirCliente {

    private JDAnadirCliente vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor parametrizado con un objeto para establecer la vista
     *
     * @param vista
     */
    public ControladorJDAnadirCliente(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    public void creaVista() {
        this.vista = new JDAnadirCliente(null, true);
        vista.setControlador(this);
        vista.setVisible(true);
    }

    /**
     * Metodo para comprobar si introdujo un cliente valido y si las contraseñas
     * son iguales, finalmente se agrega el cliente a la BD
     */
    public void comprobador() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente u1 = new Cliente(vista.getjTextFieldDniCliente().getText(), vista.getjTextFieldNombreCliente().getText(), vista.getjTextFieldApellidosCliente().getText(), Integer.parseInt(vista.getjTextFieldTelefonoCliente().getText()), vista.getjTextFieldDireccionCliente().getText(), vista.getjTextFieldEmailCliente().getText());
            clienteDAO.anadirCliente(u1);
            limpiaDatos();
            JOptionPane.showMessageDialog(vista, "Cliente añadido satisfactoriamente", "Cliente creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El cliente no ha podido ser añadido, ingrese todos los campos correctamente", "Error al Crear el Cliente", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que comprueba la valided del DNI
     *
     */
    public void checkDni() {
        String dni = vista.getjTextFieldDniCliente().getText();
        try {
            int numDni = Integer.valueOf(dni.substring(0, 8));
            char letraDni = dni.charAt(8);
            int resto = numDni % 23;
            String letrasMayus = "TRWAGMYFPDXBNJZSQVHLCKE";
            String letrasMin = "trwagmyfpdxbnjzsqvhlcke";
            if (letraDni != letrasMayus.charAt(resto) && letraDni != letrasMin.charAt(resto)) {
                JOptionPane.showMessageDialog(vista, "El DNI no es correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(vista, "DNI vacio", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Longitud del DNI invalida", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo para limpiar los datos que hay rellenos en el formulario
     */
    public void limpiaDatos() {
        vista.getjTextFieldNombreCliente().setText("");
        vista.getjTextFieldApellidosCliente().setText("");
        vista.getjTextFieldDniCliente().setText("");
        vista.getjTextFieldTelefonoCliente().setText("");
        vista.getjTextFieldDireccionCliente().setText("");
        vista.getjTextFieldEmailCliente().setText("");
    }
}
