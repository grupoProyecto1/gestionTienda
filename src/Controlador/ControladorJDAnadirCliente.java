/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Vista.JDAnadirCliente;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ControladorJDAnadirCliente {
    private JDAnadirCliente vista;

    /**
     * Constructor parametrizado con un objeto para establecer la vista
     * @param vista
     */
    public ControladorJDAnadirCliente(JDAnadirCliente vista) {
        this.vista = vista;
    }

    /**
     * Metodo para comprobar si introdujo un cliente valido y si las contraseñas
     * son iguales, finalmente se agrega el cliente a la BD
     */
    public void comprobador() {
        //if para comprobaciones de los componentes
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente u1 = new Cliente(vista.getjTextFieldDniCliente().getText(),vista.getjTextFieldNombreCliente().getText(), vista.getjTextFieldApellidosCliente().getText(), Integer.parseInt(vista.getjTextFieldTelefonoCliente().getText()), vista.getjTextFieldDireccionCliente().getText(), vista.getjTextFieldEmailCliente().getText());
            clienteDAO.anadirCliente(u1);
            limpiaDatos();
            JOptionPane.showMessageDialog(vista, "Cliente añadido satisfactoriamente", "Cliente creado", JOptionPane.INFORMATION_MESSAGE);
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
