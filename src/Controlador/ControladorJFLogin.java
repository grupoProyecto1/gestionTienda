/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.JFLogin;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Mario
 */
public class ControladorJFLogin {

    private JFLogin vista;
    private Usuario usuarioLogueado;

    /**
     * Constructor que establece la vista
     *
     * @param vista objeto de tipo JFLogin
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
        try {
            UsuarioDAO udao = new UsuarioDAO();
            usuarioLogueado = udao.compruebaUsuario(vista.getjTextFieldUsuario().getText(), DigestUtils.sha512Hex(new String(vista.getjPasswordFieldContrasena().getPassword())));
            if (usuarioLogueado != null) {
                ControladorJFMenu cjfm = new ControladorJFMenu(usuarioLogueado);
                vista.dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrecta", "Error al Iniciar Sesion", JOptionPane.ERROR_MESSAGE);
        }

    }
}
