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
    
    public void cargaArticulos(){
        listaArticulos.removeAll(listaArticulos);
        try {
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
        } catch (SQLException e) {
            System.out.println("Ha fallado en la creacion del articulo obtenido de la bd");
            
        }
    }

    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }
    
}
