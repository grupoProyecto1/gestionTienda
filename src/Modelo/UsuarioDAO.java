/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
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
    public void cargaUsuarioDAO() {
        listaUsuarios.removeAll(listaUsuarios);
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select nombre,admin,vistaclientes,vistaproductos,vistaproveedores,vistausuarios from usuario");
            String[] datos = new String[6];
            while (rs.next()) {
                datos[0] = rs.getString("nombre");
                datos[1] = rs.getString("admin");
                datos[2] = rs.getString("vistaClientes");
                datos[3] = rs.getString("vistaProductos");
                datos[4] = rs.getString("vistaProveedores");
                datos[5] = rs.getString("vistaUsuarios");
                Usuario u1 = new Usuario(datos[0], Boolean.parseBoolean(datos[1]), Boolean.parseBoolean(datos[2]), Boolean.parseBoolean(datos[3]), Boolean.parseBoolean(datos[4]), Boolean.parseBoolean(datos[5]));
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
     * @param nombre
     */
    public void eliminarUsuarios(String nombre) {
        try {
            Statement stm = con.createStatement();
            String consulta = "Delete from usuario where nombre='" + nombre + "'";
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

}
