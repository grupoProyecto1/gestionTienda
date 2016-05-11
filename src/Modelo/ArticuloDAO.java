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

    public void anadirArticulo(Articulo a) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "Insert into articulo "
                + "(nombre,descripcion,stock,preciounitario,impuesto)"
                + "values('"+ a.getNombre() + "','" + a.getDescripcion() + "','" + a.getStock() + "','" + a.getPrecioUnitario() + "','" + a.getImpuesto() + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public void eliminarArticulo(Articulo a) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "Delete from articulo where idarticulo='" + a.getId() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public void modificarArticulo(Articulo a) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "update articulo set nombre='" + a.getNombre() + "',descripcion='" + a.getDescripcion()
                + "',stock='" + a.getStock() + "',preciounitario='" + a.getPrecioUnitario()
                + "',impuesto='" + a.getImpuesto() + "'where idarticulo = '" + a.getId() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public void creaFactura(Usuario u, Cliente c, double totalNeto, double totalBruto) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "insert into factura(dnicliente,nombreusuario,totalneto,totalbruto)values('" + c.getDni() + "','" + u.getNombre() + "','" + totalNeto + "','" + totalBruto + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public void creaLineasFactura(Articulo a, int cantidad) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "select id from factura order by id desc limit 1";
        ResultSet rs = stm.executeQuery(consulta);
        int facturaId = 0;
        if (rs.next()) {
            facturaId = rs.getInt("id");
        }
        String consulta2 = "insert into lineafactura(factura_id,articulo_idlineafactura,precioventa,cantidad)values('" + facturaId + "','" + a.getId() + "','" + a.getPrecioUnitario() + "','" + cantidad + "')";
        stm.executeUpdate(consulta2);
        stm.close();
    }
}
