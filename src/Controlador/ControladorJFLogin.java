/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.JFLogin;
import Vista.JFMenu;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Mario
 */
public class ControladorJFLogin {

    private JFLogin vista;

    /**
     * Constructor que establece la vista
     *
     * @param vista
     */
    public ControladorJFLogin(JFLogin vista) {
        this.vista = vista;
    }

    /**
     * Metodo para comprobar que el usuario se autentica correctamente, si se
     * autentica de forma correcta creará una ventana JFMenu y establecera el
     * valor del atributo usuarioLogueado
     */
    public void aceptar() {
        UsuarioDAO udao = new UsuarioDAO();
        Usuario usuarioLogueado = udao.compruebaUsuario(vista.getjTextFieldUsuario().getText(), DigestUtils.sha512Hex(new String(vista.getjPasswordFieldContrasena().getPassword())));
        if (usuarioLogueado != null) {
            JFMenu jfm = new JFMenu();
            jfm.getControlador().setUsuarioLogueado(usuarioLogueado);
            udao.setUsuarioLogueado(usuarioLogueado);
            jfm.setVisible(true);
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrecta", "Error al Iniciar Sesion", JOptionPane.ERROR_MESSAGE);
        }
    }
}
