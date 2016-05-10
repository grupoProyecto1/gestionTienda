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

    public ControladorJDVentas(JDVentas vista) {
        this.vista = vista;
        creaModelos();
        usuarioLogueado = new Usuario("1", "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a", true, true, true, true, true);
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
        if (!compruebaDatoColumnaa()) {
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
            modeloVentas.insertRow(0, obj);
            vista.getjTableVenta().getSelectionModel().setSelectionInterval(0, 0);
            establecerInformacion();
        } else {
            JOptionPane.showMessageDialog(vista, "El articulo ya se añadio a la tabla de venta", "Error al volver agregar un articulo", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public Boolean compruebaDatoColumnaa() {
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Ha fallado al crear la factura,repita la acción", "Error en factura", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public int totalNeto() {
        int totalNeto = 0;
        for (int i = 0; i < vista.getjTableVenta().getRowCount(); i++) {
            totalNeto += (Double) vista.getjTableVenta().getValueAt(i, 7);
        }
        return totalNeto;
    }

    public int totalBruto() {
        int totalNeto = 0;
        for (int i = 0; i < vista.getjTableVenta().getRowCount(); i++) {
            totalNeto += (Double) vista.getjTableVenta().getValueAt(i, 6);
        }
        return totalNeto;
    }

}
