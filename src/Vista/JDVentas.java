/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorJDVentas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Mario
 */
public class JDVentas extends javax.swing.JDialog {

    private ControladorJDVentas controlador;
    private TableRowSorter trsFiltro;

    /**
     * Creates new form JDVentas
     *
     * @param parent
     * @param modal
     */
    public JDVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Devuelve el objeto de jbutton vender
     *
     * @return
     */
    public JButton getjButtonVender() {
        return jButtonVender;
    }

    /**
     * Devuelve el objeto combobox filtrado
     *
     * @return
     */
    public JComboBox getjComboBoxFiltrado() {
        return jComboBoxFiltrado;
    }

    /**
     * Establece el objeto combobox filtrado
     *
     * @param jComboBoxFiltrado
     */
    public void setjComboBoxFiltrado(JComboBox jComboBoxFiltrado) {
        this.jComboBoxFiltrado = jComboBoxFiltrado;
    }

    /**
     * Devuelve objeto spinner cantidad
     *
     * @return
     */
    public JSpinner getjSpinnerCantidad() {
        return jSpinnerCantidad;
    }

    /**
     * Establece el spinner cantidad
     *
     * @param jSpinnerCantidad
     */
    public void setjSpinnerCantidad(JSpinner jSpinnerCantidad) {
        this.jSpinnerCantidad = jSpinnerCantidad;
    }

    /**
     * Devuelve el objeto jtable de articulos
     *
     * @return
     */
    public JTable getjTableArticulos() {
        return jTableArticulos;
    }

    /**
     * Establece el jtable articulos al modelo pasado por parametro
     *
     * @param tm tablemodel
     */
    public void setjTableArticulos(TableModel tm) {
        this.jTableArticulos.setModel(tm);
    }

    /**
     * Devuelve el objeto jtable venta
     *
     * @return
     */
    public JTable getjTableVenta() {
        return jTableVenta;
    }

    /**
     * Establece el modelo de jtable venta
     *
     * @param tm tablemodel
     */
    public void setjTableVenta(TableModel tm) {
        this.jTableVenta.setModel(tm);
    }

    /**
     * Devuelve el jtextfield descripcion
     *
     * @return
     */
    public JTextField getjTextFieldDescripcion() {
        return jTextFieldDescripcion;
    }

    /**
     * Establece el jtextfield descripcion
     *
     * @param jTextFieldDescripcion
     */
    public void setjTextFieldDescripcion(JTextField jTextFieldDescripcion) {
        this.jTextFieldDescripcion = jTextFieldDescripcion;
    }

    /**
     * Devuelve el jtextfield filtrado
     *
     * @return
     */
    public JTextField getjTextFieldFiltrado() {
        return jTextFieldFiltrado;
    }

    /**
     * Establece el jtextfield filtrado
     *
     * @param jTextFieldFiltrado
     */
    public void setjTextFieldFiltrado(JTextField jTextFieldFiltrado) {
        this.jTextFieldFiltrado = jTextFieldFiltrado;
    }

    /**
     * Devuelve el jtextfield dni
     *
     * @return
     */
    public JTextField getjTextFieldDni() {
        return jTextFieldDni;
    }

    /**
     * Establece el jtextfield dni
     *
     * @param jTextFieldDni
     */
    public void setjTextFieldDni(JTextField jTextFieldDni) {
        this.jTextFieldDni = jTextFieldDni;
    }

    /**
     * Devuelve el jtextfield id
     *
     * @return
     */
    public JTextField getjTextFieldId() {
        return jTextFieldId;
    }

    /**
     * Establece el jtextfield id
     *
     * @param jTextFieldId
     */
    public void setjTextFieldId(JTextField jTextFieldId) {
        this.jTextFieldId = jTextFieldId;
    }

    /**
     * Devuelve el jtextfield impuesto
     *
     * @return
     */
    public JTextField getjTextFieldImpuesto() {
        return jTextFieldImpuesto;
    }

    /**
     * Establece el jtextfield impuesto
     *
     * @param jTextFieldImpuesto
     */
    public void setjTextFieldImpuesto(JTextField jTextFieldImpuesto) {
        this.jTextFieldImpuesto = jTextFieldImpuesto;
    }

    /**
     * Devuelve el jtextfield nombre
     *
     * @return
     */
    public JTextField getjTextFieldNombre() {
        return jTextFieldNombre;
    }

    /**
     * Establece el jtextfield nombre
     *
     * @param jTextFieldNombre
     */
    public void setjTextFieldNombre(JTextField jTextFieldNombre) {
        this.jTextFieldNombre = jTextFieldNombre;
    }

    /**
     * Devuelve el jtextfield precio
     *
     * @return
     */
    public JTextField getjTextFieldPrecio() {
        return jTextFieldPrecio;
    }

    /**
     * Establece el jtextfield precio
     *
     * @param jTextFieldPrecio
     */
    public void setjTextFieldPrecio(JTextField jTextFieldPrecio) {
        this.jTextFieldPrecio = jTextFieldPrecio;
    }

    /**
     * Establece el controlador de la vista
     *
     * @param controlador
     */
    public void setControlador(ControladorJDVentas controlador) {
        this.controlador = controlador;
    }

    /**
     * Metodo para filtrar segun la columna seleccionada
     */
    public void filtro() {
        int columnaABuscar = 0;
        if (jComboBoxFiltrado.getSelectedItem() == "ID") {
            columnaABuscar = 0;
        }
        if (jComboBoxFiltrado.getSelectedItem() == "Nombre") {
            columnaABuscar = 1;
        }
        if (jComboBoxFiltrado.getSelectedItem() == "Descripcion") {
            columnaABuscar = 2;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(jTextFieldFiltrado.getText(), columnaABuscar));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableArticulos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        jComboBoxFiltrado = new javax.swing.JComboBox();
        jTextFieldFiltrado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonVender = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldImpuesto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSpinnerCantidad = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Ventas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTableArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableArticulosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableArticulos);

        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableVentaFocusGained(evt);
            }
        });
        jTableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableVentaMousePressed(evt);
            }
        });
        jTableVenta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableVentaPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableVenta);

        jComboBoxFiltrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldFiltrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFiltradoKeyTyped(evt);
            }
        });

        jLabel6.setText("Filtrar:");

        jLabel7.setText("por");

        jButtonVender.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButtonVender.setText("Vender");
        jButtonVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVenderActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextFieldImpuesto.setEditable(false);

        jLabel2.setText("Nombre:");

        jTextFieldPrecio.setEditable(false);

        jLabel3.setText("Descripción:");

        jLabel5.setText("Impuesto:");

        jSpinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinnerCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerCantidadStateChanged(evt);
            }
        });

        jLabel10.setText("DNI:");

        jTextFieldId.setEditable(false);

        jTextFieldNombre.setEditable(false);

        jTextFieldDescripcion.setEditable(false);

        jLabel8.setText("Precio/u:");

        jLabel9.setText("Cantidad:");

        jLabel1.setText("ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldId, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(46, 46, 46)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel8))
                            .addGap(27, 27, 27)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addComponent(jTextFieldImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(53, 53, 53)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldDni)
                                .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonVender, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonVender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFiltradoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltradoKeyTyped
        // TODO add your handling code here:
        jTextFieldFiltrado.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (jTextFieldFiltrado.getText());
                jTextFieldFiltrado.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsFiltro = new TableRowSorter(jTableArticulos.getModel());
        jTableArticulos.setRowSorter(trsFiltro);
    }//GEN-LAST:event_jTextFieldFiltradoKeyTyped

    private void jButtonVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVenderActionPerformed
        // TODO add your handling code here:
        controlador.creaFactura();
    }//GEN-LAST:event_jButtonVenderActionPerformed

    private void jTableArticulosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableArticulosMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            controlador.anadeArticulo();
        }
    }//GEN-LAST:event_jTableArticulosMousePressed

    private void jTableVentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVentaMousePressed
        // TODO add your handling code here:
        controlador.establecerInformacion();
        if (evt.getClickCount() == 2) {
            controlador.eliminaArticulo();
        }


    }//GEN-LAST:event_jTableVentaMousePressed

    private void jSpinnerCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerCantidadStateChanged
        // TODO add your handling code here:
        controlador.cambiaCantidad();

    }//GEN-LAST:event_jSpinnerCantidadStateChanged

    private void jTableVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableVentaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableVentaFocusGained

    private void jTableVentaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableVentaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableVentaPropertyChange

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDVentas dialog = new JDVentas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonVender;
    private javax.swing.JComboBox jComboBoxFiltrado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JTable jTableArticulos;
    private javax.swing.JTable jTableVenta;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldFiltrado;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldImpuesto;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPrecio;
    // End of variables declaration//GEN-END:variables
}
