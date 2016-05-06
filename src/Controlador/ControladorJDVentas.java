/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.JDVentas;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class ControladorJDVentas {
    private JDVentas vista;
    public ControladorJDVentas(JDVentas vista){
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
        modeloArticulos.addColumn("Impuesto");
        modeloArticulos.addColumn("Precio");
        //Modelo de la tabla ventas
        modeloVentas.addColumn("ID");
        modeloVentas.addColumn("Nombre");
        modeloVentas.addColumn("Descripción");
        modeloVentas.addColumn("Stock");
        modeloVentas.addColumn("Impuesto");
        modeloVentas.addColumn("Precio");
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
}
