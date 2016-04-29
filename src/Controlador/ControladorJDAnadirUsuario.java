/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.JDAnadirUsuario;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Mario
 */
public class ControladorJDAnadirUsuario {

    private JDAnadirUsuario vista;

    /**
     * Constructor parametrizado con un objeto para establecer la vista
     * @param vista
     */
    public ControladorJDAnadirUsuario(JDAnadirUsuario vista) {
        this.vista = vista;
    }

    /**
     * Metodo para comprobar si introdujo un usuario valido y si las contraseñas
     * son iguales, finalmente se agrega el usuario a la BD
     */
    public void comprobador() {
        if (vista.getjTextFieldNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre es obligatorio", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (new String(vista.getjPasswordFieldContrasena().getPassword()).isEmpty() || new String(vista.getjPasswordFieldContrasena2().getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No puede haber una contraseña vacia", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (new String(vista.getjPasswordFieldContrasena().getPassword()).equals(new String(vista.getjPasswordFieldContrasena2().getPassword()))) {
            String pass = DigestUtils.sha512Hex(new String(vista.getjPasswordFieldContrasena().getPassword()));
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario u1 = new Usuario(vista.getjTextFieldNombre().getText(), pass,
                    vista.getjCheckBoxAdmin().isSelected(), vista.getjCheckBoxCliente().isSelected(),
                    vista.getjCheckBoxProductos().isEnabled(), vista.getjCheckBoxProveedores().isSelected(),
                    vista.getjCheckBoxUsuarios().isSelected());
            usuarioDAO.anadirUsuario(u1);
            limpiaDatos();
            JOptionPane.showMessageDialog(vista, "Usuario añadido satisfactoriamente", "Usuario creado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "Las contraseña son diferentes", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo para limpiar los datos que hay rellenos en el formulario
     */
    public void limpiaDatos() {
        vista.getjTextFieldNombre().setText("");
        vista.getjPasswordFieldContrasena().setText("");
        vista.getjPasswordFieldContrasena2().setText("");
        vista.getjCheckBoxAdmin().setSelected(false);
        vista.getjCheckBoxCliente().setSelected(false);
        vista.getjCheckBoxProductos().setSelected(false);
        vista.getjCheckBoxProveedores().setSelected(false);
        vista.getjCheckBoxUsuarios().setSelected(false);
    }
}
