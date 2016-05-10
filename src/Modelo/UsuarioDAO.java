/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class UsuarioDAO {

    private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private Connection con = ConexionBBDD.getConnection();

    
    /**
     *  Metodo que carga los usuarios desde la base de datos
     * en el atributo privado listaUsuarios
     */
    public void cargaUsuarios() {
        listaUsuarios.removeAll(listaUsuarios);
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from usuario");
            String[] datos = new String[7];
            while (rs.next()) {
                datos[0] = rs.getString("nombre");
                datos[1] = rs.getString("contrasena");
                datos[2] = rs.getString("admin");
                datos[3] = rs.getString("vistaClientes");
                datos[4] = rs.getString("vistaProductos");
                datos[5] = rs.getString("vistaProveedores");
                datos[6] = rs.getString("vistaUsuarios");
                Usuario u1 = new Usuario(datos[0], datos[1],Boolean.parseBoolean(datos[2]), Boolean.parseBoolean(datos[3]), Boolean.parseBoolean(datos[4]), Boolean.parseBoolean(datos[5]), Boolean.parseBoolean(datos[6]));
                listaUsuarios.add(u1);
            }
            stm.close();
        } catch (SQLException e) {
            System.out.println("Ha petado en la creacion del objeto obtenido de la bd");

        }
    }

    /**
     * Metodo para añadir un nuevo usuario
     * @param u Objeto de tipo Usuario
     */
    public void anadirUsuario(Usuario u) {
        try {
            Statement stm = con.createStatement();
            String consulta = "Insert into usuario "
                    + "(nombre,contrasena,admin,vistaclientes,vistaproductos,vistaproveedores,vistausuarios,establecimiento_nif)"
                    + "values('" + u.getNombre() + "','" + u.getPass() + "','" + u.isAdmin() + "','" + u.isVistaClientes() + "','" + u.isVistaProductos() + "','" + u.isVistaProveedores() + "','" + u.isVistaUsuarios() + "','11111111A')";
            stm.executeUpdate(consulta);
            stm.close();
        } catch (SQLException e) {
            System.out.println("Ha petado al añadir el usuario.");
        }
    }

    /**
     * Devuelve la lista con todos los objetos almacenados en el ArrayList
     * listaUsuarios
     * @return listaUsuarios
     */
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Metodo para eliminar un usuario a partir de su nombre
     * 
     * @param u Objeto de tipo usuario
     */
    public void eliminarUsuarios(Usuario u) {
        try {
            Statement stm = con.createStatement();
            String consulta = "Delete from usuario where nombre='" + u.getNombre() + "'";
            stm.executeUpdate(consulta);
            stm.close();
        } catch (SQLException e) {
            System.out.println("Ha petado al eliminar el usuario.");
        }
    }

    /**
     * Metodo para modificar un usuario
     * @param u Objeto de tipo Usuario
     */
    public void modificarUsuarios(Usuario u) {
        try {
            Statement stm = con.createStatement();
            String consulta = "update usuario set admin='" + u.isAdmin() + "',vistaclientes='" + u.isVistaClientes()
                    + "',vistaproductos='" + u.isVistaProductos() + "',vistaproveedores='" + u.isVistaProveedores()
                    + "',vistausuarios='" + u.isVistaUsuarios() + "'where nombre = '" + u.getNombre() + "'";
            stm.executeUpdate(consulta);
            stm.close();
        } catch (SQLException e) {
            System.out.println("Ha petado al modificar el usuario.");
        }
    }

    /**
     *Comprueba si existe o no el usuario en la base de datos
     * @param usuario
     * @param contrasena
     * @return objeto de la clase Usuario
     */
    public Usuario compruebaUsuario(String usuario,String contrasena){
        try {
            PreparedStatement pstm = con.prepareStatement("select * from usuario where nombre=? and contrasena =?");
            pstm.setString(1, usuario);
            pstm.setString(2, contrasena);
            ResultSet rs = pstm.executeQuery();
            String[] datos = new String[7];
            if (rs.next()) {
                datos[0] = rs.getString("nombre");
                datos[1] = rs.getString("contrasena");
                datos[2] = rs.getString("admin");
                datos[3] = rs.getString("vistaClientes");
                datos[4] = rs.getString("vistaProductos");
                datos[5] = rs.getString("vistaProveedores");
                datos[6] = rs.getString("vistaUsuarios");
                Usuario u = new Usuario(datos[0],datos[1], Boolean.parseBoolean(datos[2]), Boolean.parseBoolean(datos[3]), Boolean.parseBoolean(datos[4]), Boolean.parseBoolean(datos[5]), Boolean.parseBoolean(datos[6]));
                rs.close();
                pstm.close();
                return u;   
            }
        } catch (SQLException ex) {
            System.out.println("Ha petado al compruebaUsuario");
        }
        return null;
    }
    
}
