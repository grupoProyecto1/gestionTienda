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
import java.util.Arrays;

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
     */
    public void cargaProveedorDAO() {
        listaProveedores.removeAll(listaProveedores);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Peto al crear el objeto en la bbdd");
        }
    }

    /**
     * Metodo para añadir un nuevo proveedor
     *
     * @param p Objeto de tipo Proveedor
     */
    public void anadirProveedor(Proveedor p) {
        try {
            Statement stm = con.createStatement();
            String consulta = "Insert into proveedor "
                    + "(NIF,Nombre,Direccion,Telefono,Email)"
                    + "values('" + p.getNif() + "','" + p.getNombre() + "','" + p.getDireccion() + "','" + p.getTelefono() + "','" + p.getEmail() + "')";
            stm.executeUpdate(consulta);
            stm.close();
        } catch (Exception e) {
            System.out.println("Pero al meter el prov");
        }
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
     * @param nif
     */
    public void eliminarProveedores(String nif) {
        try {
            Statement stm = con.createStatement();
            String consulta = "Delete from proveedor where NIF='" + nif + "'";
            stm.executeUpdate(consulta);
            stm.close();
        } catch (Exception e) {
            System.out.println("Peto al borrar el prov");
        }
    }

    /**
     * Metodo para modificar un proveedor
     *
     * @param p
     */
    public void modificarProveedor(Proveedor p) {
        try {
            Statement stm = con.createStatement();
            String consulta = "update proveedor set nombre='" + p.getNombre() + "',direccion='" + p.getDireccion() + "',telefono=" + p.getTelefono() + ",email='" + p.getEmail() + "'where nif = '" + p.getNif() + "'";
            stm.executeUpdate(consulta);
            stm.close();
        } catch (Exception e) {
            System.out.println("Peto al modificar el prov");
        }
    }

}
