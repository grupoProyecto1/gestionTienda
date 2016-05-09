/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import Modelo.ArticuloDAO;
import Modelo.Usuario;
import Vista.JDVentas;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class ControladorJDVentas {

    private JDVentas vista;
    private Usuario usuarioLogueado;
    private ArticuloDAO articuloDAO = new ArticuloDAO();

    public ControladorJDVentas(JDVentas vista) {
        this.vista = vista;
        creaModelos();
    }

    public DefaultTableModel modeloArticulos = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    };
    public DefaultTableModel modeloVentas = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    };

    /**
     * Metodo que crea el modelo de tabla y lo establece
     */
    public void creaModelos() {
        //Modelo de la tabla articulos
        modeloArticulos.addColumn("ID");
        modeloArticulos.addColumn("Nombre");
        modeloArticulos.addColumn("Descripción");
        modeloArticulos.addColumn("Stock");
        modeloArticulos.addColumn("Precio");
        modeloArticulos.addColumn("Impuesto");
        //Modelo de la tabla ventas
        modeloVentas.addColumn("ID");
        modeloVentas.addColumn("Nombre");
        modeloVentas.addColumn("Descripción");
        modeloVentas.addColumn("Cantidad");
        modeloVentas.addColumn("Impuesto");
        modeloVentas.addColumn("PrecioUnit");
        modeloVentas.addColumn("PrecioTotal");
        modeloVentas.addColumn("PrecioImp");
        //Establecer modelos 
        vista.setjTableVenta(modeloVentas);
        vista.setjTableArticulos(modeloArticulos);
        //Modelo del jcombobox de filtrado
        vista.getjComboBoxFiltrado().removeAllItems();
        vista.getjComboBoxFiltrado().addItem("ID");
        vista.getjComboBoxFiltrado().addItem("Nombre");
        vista.getjComboBoxFiltrado().addItem("Descripcion");
    }

    public void rellenaTablas() {
        for (int i = 0; i < vista.getjTableArticulos().getRowCount(); i++) {
            modeloArticulos.removeRow(i);
            i -= 1;
        }
        articuloDAO.cargaArticulos();
        Object[] datos = new Object[6];
        for (Articulo a : articuloDAO.getListaArticulos()) {
            datos[0] = a.getId();
            datos[1] = a.getNombre();
            datos[2] = a.getDescripcion();
            datos[3] = a.getStock();
            datos[4] = a.getPrecioUnitario();
            datos[5] = a.getImpuesto();
            modeloArticulos.addRow(datos);
        }
    }

    public void anadeArticulo() {
        Object[] obj = new Object[8];
        int linea = vista.getjTableArticulos().getSelectedRow();
        double precio = (Double) vista.getjTableArticulos().getValueAt(linea, 4);
        double impuesto = (Double) vista.getjTableArticulos().getValueAt(linea, 5);
        obj[0] = (Integer) vista.getjTableArticulos().getValueAt(linea, 0);//id
        obj[1] = (String) vista.getjTableArticulos().getValueAt(linea, 1);//nombre
        obj[2] = (String) vista.getjTableArticulos().getValueAt(linea, 2);//descipcion
        obj[3] = (Integer) 1;//cantidad
        obj[4] = impuesto;
        obj[5] = (Double) precio;//precioUnidad
        obj[6] = (Double) precio;//precioTotal
        obj[7] = (Double) precio * impuesto;//precioImp
        modeloVentas.addRow(obj);
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
