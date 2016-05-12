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

    public Factura(int id, String DNICliente, String nombreUsuario, double totalNeto, double totalBruto, String fecha) {
        this.id = id;
        this.DNICliente = DNICliente;
        this.nombreUsuario = nombreUsuario;
        this.totalNeto = totalNeto;
        this.totalBruto = totalBruto;
        this.fecha = fecha;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDNICliente() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente = DNICliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
