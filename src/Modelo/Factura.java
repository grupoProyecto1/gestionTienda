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
public class Factura {

    private int id;
    private String DNICliente;
    private String nombreUsuario;
    private double totalNeto;
    private double totalBruto;
    private String fecha;

    /**
     *Constructor parametrizado del objeto Factura
     * @param id
     * @param DNICliente
     * @param nombreUsuario
     * @param totalNeto
     * @param totalBruto
     * @param fecha
     */
    public Factura(int id, String DNICliente, String nombreUsuario, double totalNeto, double totalBruto, String fecha) {
        this.id = id;
        this.DNICliente = DNICliente;
        this.nombreUsuario = nombreUsuario;
        this.totalNeto = totalNeto;
        this.totalBruto = totalBruto;
        this.fecha = fecha;
    }

    /**
     *Devuelve el valor de TotalNeto
     * @return
     */
    public double getTotalNeto() {
        return totalNeto;
    }

    /**
     *Establece el valor de TotalNeto
     * @param totalNeto
     */
    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    /**
     *Devuelve el valor de  TotalBruto
     * @return
     */
    public double getTotalBruto() {
        return totalBruto;
    }

    /**
     *Establece el valor de TotalBruto
     * @param totalBruto
     */
    public void setTotalBruto(double totalBruto) {
        this.totalBruto = totalBruto;
    }

    /**
     *Devuelve el valor del ID
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *Establece el valor del ID
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Devuelve el valor de DNICliente
     * @return
     */
    public String getDNICliente() {
        return DNICliente;
    }

    /**
     *Establece el valor de DNIClinte
     * @param DNICliente
     */
    public void setDNICliente(String DNICliente) {
        this.DNICliente = DNICliente;
    }

    /**
     *Devuelve el valor de NombreUsuario
     * @return
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     *Establece el valor de NombreUsuario
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     *Devuelve el valor de la Fecha
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     *Establece el valor de la Fecha
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
