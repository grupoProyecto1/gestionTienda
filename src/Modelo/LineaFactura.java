/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Joaquin
 */
public class LineaFactura {

    private int id;
    private int facturaId;
    private int articuloId;
    private double precioVenta;
    private double cantidad;

    /**
     * Constructor parametrizado para crear un objeto de tipo lineafactura
     *
     * @param id
     * @param facturaId
     * @param articuloId
     * @param precioVenta
     * @param cantidad
     */
    public LineaFactura(int id, int facturaId, int articuloId, double precioVenta, double cantidad) {
        this.id = id;
        this.facturaId = facturaId;
        this.articuloId = articuloId;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
    }

    /**
     * Devuelve el id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el id de factura
     *
     * @return
     */
    public int getFacturaId() {
        return facturaId;
    }

    /**
     * Establece el id de factura
     *
     * @param facturaId
     */
    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    /**
     * Devuelve el id de articulo
     *
     * @return
     */
    public int getArticuloId() {
        return articuloId;
    }

    /**
     * Establece el id de articulo
     *
     * @param articuloId
     */
    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    /**
     * Devuelve el precioVenta
     *
     * @return
     */
    public double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * Establece el precioVenta
     *
     * @param precioVenta
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * Devuelve la cantidad
     *
     * @return
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Estable la cantidad
     *
     * @param cantidad
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

}
