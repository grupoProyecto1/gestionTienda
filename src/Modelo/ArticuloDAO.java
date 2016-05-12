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
public class ArticuloDAO {

    private ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
    private Connection con = ConexionBBDD.getConnection();

    /**
     * Carga los articulos de la base de datos
     *
     * @throws SQLException
     */
    public void cargaArticulos() throws SQLException {
        listaArticulos.removeAll(listaArticulos);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from articulo");
        String[] datos = new String[6];
        while (rs.next()) {
            datos[0] = rs.getString("idarticulo");
            datos[1] = rs.getString("nombre");
            datos[2] = rs.getString("descripcion");
            datos[3] = rs.getString("stock");
            datos[4] = rs.getString("precioUnitario");
            datos[5] = rs.getString("impuesto");
            Articulo a1 = new Articulo(Integer.parseInt(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3]), Double.parseDouble(datos[4]), Double.parseDouble(datos[5]));
            listaArticulos.add(a1);
        }
        stm.close();
    }

    /**
     * Añade el articulo pasado como parametro a la base de datos
     *
     * @param a
     * @throws SQLException
     */
    public void anadirArticulo(Articulo a) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "Insert into articulo "
                + "(nombre,descripcion,stock,preciounitario,impuesto)"
                + "values('" + a.getNombre() + "','" + a.getDescripcion() + "','" + a.getStock() + "','" + a.getPrecioUnitario() + "','" + a.getImpuesto() + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    /**
     * Elimina el articulo pasado como parametro de la base de datos
     *
     * @param a
     * @throws SQLException
     */
    public void eliminarArticulo(Articulo a) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "Delete from articulo where idarticulo='" + a.getId() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

    /**
     * Modifica el articulo pasado como parametro de la base de datos
     *
     * @param a
     * @throws SQLException
     */
    public void modificarArticulo(Articulo a) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "update articulo set nombre='" + a.getNombre() + "',descripcion='" + a.getDescripcion()
                + "',stock='" + a.getStock() + "',preciounitario='" + a.getPrecioUnitario()
                + "',impuesto='" + a.getImpuesto() + "'where idarticulo = '" + a.getId() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }
    
    /**
     *Actualiza el stock despues de una venta
     * @param a
     * @param cantidad
     * @throws SQLException
     */
    public void cambiaStock(Articulo a,int cantidad) throws SQLException{
        Statement stm = con.createStatement();
        String consulta = "update articulo set stock='" + (devuelveStock(a)-cantidad) +  "'where idarticulo = '" + a.getId() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }
    
    /**
     *Devuelve el Stock de un articulo
     * @param a
     * @return
     * @throws SQLException
     */
    public int devuelveStock(Articulo a)throws SQLException{
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from articulo where idarticulo ='"+a.getId()+"'");
        int cantidad=-1;
        if(rs.next()){
            cantidad=rs.getInt("stock");
        }
        stm.close();
        return cantidad;
    }

    /**
     * Devuelve una lista de articulos
     *
     * @return
     */
    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }
}
