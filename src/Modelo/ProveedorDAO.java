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
 * @author Joaquin
 */
public class ProveedorDAO {

    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private Connection con = ConexionBBDD.getConnection();

    /**
     * Metodo que carga los proveedores desde la base de datros en el atributo
     * listaProveedores
     *
     * @throws java.sql.SQLException
     */
    public void cargaProveedorDAO() throws SQLException {
        listaProveedores.removeAll(listaProveedores);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from proveedor");
        String[] datos = new String[5];
        while (rs.next()) {
            datos[0] = rs.getString("NIF");
            datos[1] = rs.getString("Nombre");
            datos[2] = rs.getString("Direccion");
            datos[3] = String.valueOf(rs.getInt("Telefono"));
            datos[4] = rs.getString("Email");
            Proveedor p1 = new Proveedor(datos[0], datos[1], datos[2], Integer.valueOf(datos[3]), datos[4]);
            listaProveedores.add(p1);

        }
    }

    /**
     * Metodo para añadir un nuevo proveedor
     *
     * @param p Objeto de tipo Proveedor
     * @throws java.sql.SQLException
     */
    public void anadirProveedor(Proveedor p) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "Insert into proveedor "
                + "(NIF,Nombre,Direccion,Telefono,Email)"
                + "values('" + p.getNif() + "','" + p.getNombre() + "','" + p.getDireccion() + "','" + p.getTelefono() + "','" + p.getEmail() + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    /**
     * Devuelve la lista con todos los objetos almacenados en listaProveedores
     *
     * @return
     */
    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    /**
     * Metodo para eliminar un proveedor a partir de su NIF
     *
     * @param datosProveedor
     * @throws java.sql.SQLException
     */
    public void eliminarProveedores(Proveedor datosProveedor) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "Delete from proveedor where NIF='" + datosProveedor.getNif() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

    /**
     * Metodo para modificar un proveedor
     *
     * @param p
     * @throws java.sql.SQLException
     */
    public void modificarProveedor(Proveedor p) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "update proveedor set nombre='" + p.getNombre() + "',direccion='" + p.getDireccion() + "',telefono=" + p.getTelefono() + ",email='" + p.getEmail() + "'where nif = '" + p.getNif() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

}
