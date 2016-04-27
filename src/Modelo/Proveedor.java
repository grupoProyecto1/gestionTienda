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
public class Proveedor {

    private String nif;
    private String nombre;
    private String direccion;
    private int telefono;
    private String email;

    /**
     *Constructor parametrizado para crear un proveedor con sus atributos
     * 
     * @param nif
     * @param nombre
     * @param direccion
     * @param telefono
     * @param email
     */
    public Proveedor(String nif, String nombre, String direccion, int telefono, String email) {
        this.nif = nif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     *Devuelve el nif del proveedor
     * 
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     *Establece el nif del proveedor
     * 
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     *Devuelve el nombre del proveedor
     * 
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *Establece el nombre del proveedor
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *Devuelve la direccion del proveedor
     * 
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *Establece la direccion del proveedor
     * 
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *Devuelve el telefono del proveedor
     * 
     * @return
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     *Establece el telefono del proveedor
     * 
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     *Devuelve el email del proveedor
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *Establece el email del proveedor
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
