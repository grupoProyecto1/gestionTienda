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
public class LineafacturaDAO {

    private ArrayList<LineaFactura> listaLineas = new ArrayList<LineaFactura>();
    private Connection con = ConexionBBDD.getConnection();

    /**
     * Carga las lineas de factura de la base de datos
     *
     * @param f
     * @throws SQLException
     */
    public void cargaLineas(Factura f) throws SQLException {
        listaLineas.removeAll(listaLineas);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from lineaFactura where Factura_id =" + f.getId());
        String[] datos = new String[5];
        while (rs.next()) {
            datos[0] = rs.getString("IdLineaFactura");
            datos[1] = rs.getString("Factura_ID");
            datos[2] = rs.getString("Articulo_idLineaFactura");
            datos[3] = rs.getString("PrecioVenta");
            datos[4] = rs.getString("cantidad");
            LineaFactura l1 = new LineaFactura(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]));
            listaLineas.add(l1);

        }
        stm.close();

    }

    /**
     * Añade a la base de datos una lineaFactura
     *
     * @param a
     * @param cantidad
     * @throws SQLException
     */
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

    /**
     * Devuelve la lista de lineas
     *
     * @return
     */
    public ArrayList<LineaFactura> getListaLineas() {
        return listaLineas;
    }

    /**
     * Establece la lista de lineas a otra lista
     *
     * @param listaLineas
     */
    public void setListaLineas(ArrayList<LineaFactura> listaLineas) {
        this.listaLineas = listaLineas;
    }

}
