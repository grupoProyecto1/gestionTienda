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
 *Clase que maneja todas las operaciones relacionadas con los clientes
 * @author Alejandroo
 */
public class ClienteDAO {
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private Connection conn;
    
    /**
     *Inserta un nuevo cliente en la base de datos de la aplicacion
     * @param datosCliente
     */
    public void anadirCliente(Cliente datosCliente) {
        conn = ConexionBBDD.getConnection();
        String sql = "INSERT into cliente values (" + "'"+datosCliente.getDni()+"'" + ","+"'"+datosCliente.getNombre()+"'"+","+"'"+datosCliente.getApellidos()+"'"+","+"'"+datosCliente.getTelefono()+"'"+","+"'"+datosCliente.getDireccion()+"'"+","+"'"+datosCliente.getEmail()+"'" +")";

        try {
            Statement stm = conn.createStatement();
            int result = stm.executeUpdate(sql);
            
            if(result >0 ) {
                System.out.println("La inserción se realizó correctamente");
            } else {
                System.out.println("Ha ocurrido un error al intentar añadir un cliente");
            }
            stm.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *Elimina un cliente de la base de datos de la aplicacion
     * @param datosCliente
     */
    public void eliminarCliente(Cliente datosCliente){
        conn =ConexionBBDD.getConnection();
        String sql = "delete from cliente where DNI= '" + datosCliente.getDni() + "'";
        try {
            Statement stm = conn.createStatement();
            int result = stm.executeUpdate(sql);
            
            if(result >0 ) {
                System.out.println("Se ha borrado correctamente al cliente");
            } else {
                System.out.println("Ha ocurrido un error al intentar borrar un cliente");
            }
            stm.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *Modifica los datos de un cliente de la base de datos de la
     * aplicacion
     * @param datosCliente
     */
    public void modificarCliente(Cliente datosCliente) {
        conn = ConexionBBDD.getConnection();
        String sql = "update cliente set DNI = '" +datosCliente.getDni()+"',nombre='"+
        datosCliente.getNombre() +"',apellidos ='"+datosCliente.getApellidos()+
        "',telefono ='"+datosCliente.getTelefono()+"',direccion='"+datosCliente.getDireccion()
                +"',email ='"+datosCliente.getEmail()+"' where dni = '"+datosCliente.getDni()
                +"'";
        
        try {
            Statement stm = conn.createStatement();
            int result = stm.executeUpdate(sql);
            
            if(result >0 ) {
                System.out.println("Se ha modificado correctamente el cliente");
            } else {
                System.out.println("Ha ocurrido un error al intentar modificar un cliente");
            }
            stm.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *Muestra todos los clientes almacenados en la base de datos de 
     * nuestra aplicacion
     */
    public void cargaCliente(){
        listaClientes.removeAll(listaClientes);
        try{
        conn = ConexionBBDD.getConnection();
        String sql = "select * from cliente";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        String[] datosCliente = new String[6];
        
        while(rs.next()){
            datosCliente[0] = rs.getString("dni");
            datosCliente[1] = rs.getString("nombre");
            datosCliente[2] = rs.getString("apellidos");
            datosCliente[3] = rs.getString("telefono");
            datosCliente[4] = rs.getString("direccion");
            datosCliente[5] = rs.getString("email");
            Cliente c = new Cliente(datosCliente[0], datosCliente[1], datosCliente[2], Integer.parseInt(datosCliente[3]), datosCliente[4], datosCliente[5]);
            listaClientes.add(c);
        }
         stm.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Devuelve la lista con todos los objetos almacenados en listaClientes
     *
     * @return arraylist listaClientes
     */
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}
