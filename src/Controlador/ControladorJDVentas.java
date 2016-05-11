/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import Modelo.ArticuloDAO;
import Modelo.Cliente;
import Modelo.Usuario;
import Vista.JDVentas;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class ControladorJDVentas {

    private JDVentas vista;
    private Usuario usuarioLogueado;
    private ArticuloDAO articuloDAO = new ArticuloDAO();
    
    public ControladorJDVentas(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        creaVista();
    }

    public void creaVista() {
        this.vista = new JDVentas(null, true);
        vista.setControlador(this);
        creaModelos();
        rellenaTabla();
        vista.setVisible(true);
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

    public void rellenaTabla() {
        for (int i = 0; i < vista.getjTableArticulos().getRowCount(); i++) {
            modeloArticulos.removeRow(i);
            i -= 1;
        }
        try {
            articuloDAO.cargaArticulos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Ha fallado al cargar los articulos de la BD", "Error en conexion BD", JOptionPane.ERROR_MESSAGE);
        }
        
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
        if (!compruebaDatoColumna()) {
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
            DefaultTableModel model = (DefaultTableModel) vista.getjTableVenta().getModel();
            model.insertRow(0, obj);
            vista.getjTableVenta().getSelectionModel().setSelectionInterval(0, 0);
            establecerInformacion();
        } else {
            JOptionPane.showMessageDialog(vista, "El articulo ya se añadio a la tabla de venta", "Error al volver agregar un articulo", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public Boolean compruebaDatoColumna() {
        int datoSeleccionado = (Integer) vista.getjTableArticulos().getValueAt(vista.getjTableArticulos().getSelectedRow(), 0);
        for (int i = 0; i < vista.getjTableVenta().getRowCount(); i++) {
            if ((Integer) vista.getjTableVenta().getValueAt(i, 0) == datoSeleccionado) {
                return true;
            }
        }
        return false;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public void cambiaCantidad() {
        //Cojo articulos debido a que tu agregas y acto seguido lo cambias sin tocar la venta.
        if (vista.getjTableArticulos().isRowSelected(WIDTH)) {
            int fila = vista.getjTableVenta().getSelectedRow();
            int cantidad = (Integer) vista.getjSpinnerCantidad().getValue();
            double impuesto = (Double) vista.getjTableVenta().getValueAt(fila, 4);
            double precioUnid = (Double) vista.getjTableVenta().getValueAt(fila, 5);
            double precioTotal = cantidad * precioUnid;
            vista.getjTableVenta().setValueAt(cantidad, vista.getjTableVenta().getSelectedRow(), 3);
            vista.getjTableVenta().setValueAt(precioTotal, fila, 6);
            vista.getjTableVenta().setValueAt(precioTotal * impuesto, fila, 7);
        } else {
            JOptionPane.showMessageDialog(vista, "Debes seleccionar un elemento de la tabla ventas", "Error al variar la cantidad", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void eliminaArticulo(){
        modeloVentas.removeRow(vista.getjTableVenta().getSelectedRow());
    }
    
    public void establecerInformacion() {
        int fila = vista.getjTableVenta().getSelectedRow();
        vista.getjTextFieldId().setText(String.valueOf(vista.getjTableVenta().getValueAt(fila, 0)));
        vista.getjTextFieldNombre().setText((String) vista.getjTableVenta().getValueAt(fila, 1));
        vista.getjTextFieldDescripcion().setText((String) vista.getjTableVenta().getValueAt(fila, 2));
        vista.getjTextFieldPrecio().setText(String.valueOf(vista.getjTableVenta().getValueAt(fila, 5)));
        vista.getjTextFieldImpuesto().setText(String.valueOf(vista.getjTableVenta().getValueAt(fila, 4)));
        vista.getjSpinnerCantidad().setValue(vista.getjTableVenta().getValueAt(fila, 3));
    }

    public void creaFactura() {
        try {
            ArticuloDAO adao = new ArticuloDAO();
            Cliente c;
            if (vista.getjTextFieldDni().getText().length() == 0) {
                c = new Cliente("00000000A", null, null, 000000000, null, null);
            } else {
                c = new Cliente(vista.getjTextFieldDni().getText(), null, null, 000000000, null, null);
            }
            adao.creaFactura(usuarioLogueado, c, totalNeto(), totalBruto());
            for (int i = 0; i < vista.getjTableVenta().getRowCount(); i++) {
                Articulo a1 = new Articulo((Integer) vista.getjTableVenta().getValueAt(i, 0), null, null, 0, (Double) vista.getjTableVenta().getValueAt(i, 5), 0);
                adao.creaLineasFactura(a1, (Integer) vista.getjTableVenta().getValueAt(i, 3));
            }
            JOptionPane.showMessageDialog(vista, "Venta realizada correctamente.", "Información Venta", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Ha fallado al crear la factura,repita la acción", "Error en factura", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
    }

    public double totalNeto() {
        double totalNeto = 0;
        for (int i = 0; i < vista.getjTableVenta().getRowCount(); i++) {
            totalNeto += (Double) vista.getjTableVenta().getValueAt(i, 7);
        }
        return totalNeto;
    }

    public double totalBruto() {
        double totalNeto = 0;
        for (int i = 0; i < vista.getjTableVenta().getRowCount(); i++) {
            totalNeto += (Double) vista.getjTableVenta().getValueAt(i, 6);
        }
        return totalNeto;
    }

}
