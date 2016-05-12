/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Mario
 */
public class Articulo {

    private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precioUnitario;
    private double impuesto;

    /**
     * Constructor parametrizado para crear un objeto de tipo articulo
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param stock
     * @param precioUnitario
     * @param impuesto
     */
    public Articulo(int id, String nombre, String descripcion, int stock, double precioUnitario, double impuesto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.impuesto = impuesto;
    }

    /**
     * devuelve la id del articulo
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Establece la id del articulo
     *
     * @param id int id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del articulo
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del articulo
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la descripcion del articulo
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion del articulo
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el stock de articulo
     *
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establce el stock de articulo
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Devuelve el precio unitario del articulo
     *
     * @return
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del articulo
     *
     * @param precioUnitario
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Devuelve el impuesto del articulo
     *
     * @return
     */
    public double getImpuesto() {
        return impuesto;
    }

    /**
     * Establece el impuesto del articulo
     *
     * @param impuesto
     */
    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

}
