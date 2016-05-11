/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import com.sun.xml.internal.fastinfoset.EncodingConstants;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Joaquin
 */
public class FacturaDAO {
    private ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
    private Connection con = ConexionBBDD.getConnection();
    
    public void cargaFacturas() throws SQLException{
        listaFacturas.removeAll(listaFacturas);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from factura");
        String[] datos = new String[6];
        while (rs.next()) {
        datos[0]=rs.getString("ID");
        datos[1] = rs.getString("DNICliente");
        datos[2] = rs.getString("NombreUsuario");
        datos[3] = rs.getString("TotalNeto");
        datos[4] = rs.getString("TotalBruto");
        datos[5] = rs.getString("Fecha");
        Factura f1 = new Factura(Integer.parseInt(datos[0]),datos[1] , datos[2], Double.parseDouble(datos[3]), Double.parseDouble(datos[4]), datos[5]);
        listaFacturas.add(f1);
            
        }
        stm.close();
    }

    public void creaFactura(Usuario u, Cliente c, double totalNeto, double totalBruto) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "insert into factura(dnicliente,nombreusuario,totalneto,totalbruto)values('" + c.getDni() + "','" + u.getNombre() + "','" + totalNeto + "','" + totalBruto + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
  
            
    
}
