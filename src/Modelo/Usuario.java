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
public class Usuario {

    private String nombre;
    private String pass;
    private boolean admin;
    private boolean vistaClientes;
    private boolean vistaProductos;
    private boolean vistaProveedores;
    private boolean vistaUsuarios;

    /**
     * Constructor parametrizado para crearte un usuario con todos sus atributos
     *
     * @param nombre
     * @param pass
     * @param admin
     * @param cliente
     * @param producto
     * @param proveedor
     * @param usuarios
     */
    public Usuario(String nombre, String pass, boolean admin, boolean cliente, boolean producto, boolean proveedor, boolean usuarios) {
        this.nombre = nombre;
        this.pass = pass;
        this.admin = admin;
        this.vistaClientes = cliente;
        this.vistaProductos = producto;
        this.vistaProveedores = proveedor;
        this.vistaUsuarios = usuarios;
    }

    /**
     * Devuelve el nombre del usuario
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la pass del usuario
     *
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     * Establece la pass del usuario
     *
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Devuelve si es admin el usuario
     *
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Establece como admin al usuario
     *
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Devuelve si tiene acceso a la VistaClientes el usuario
     *
     * @return
     */
    public boolean isVistaClientes() {
        return vistaClientes;
    }

    /**
     * Establece el acceso a la VistaClientes del usuario
     *
     * @param vistaClientes
     */
    public void setVistaClientes(boolean vistaClientes) {
        this.vistaClientes = vistaClientes;
    }

    /**
     * Devuelve si tiene acceso a la VistaProductos el usuario
     *
     * @return
     */
    public boolean isVistaProductos() {
        return vistaProductos;
    }

    /**
     * Establece el acceso a la VistaProductos del usuario
     *
     * @param vistaProductos
     */
    public void setVistaProductos(boolean vistaProductos) {
        this.vistaProductos = vistaProductos;
    }

    /**
     * Devuelve si tiene acceso a la VistaProveedores el usuario
     *
     * @return
     */
    public boolean isVistaProveedores() {
        return vistaProveedores;
    }

    /**
     * Establece el acceso a la VistaProveedores del usuario
     *
     * @param vistaProveedores
     */
    public void setVistaProveedores(boolean vistaProveedores) {
        this.vistaProveedores = vistaProveedores;
    }

    /**
     * Devuelve si tiene acceso a la VistaUsuarios el usuario
     *
     * @return
     */
    public boolean isVistaUsuarios() {
        return vistaUsuarios;
    }

    /**
     * Establece el acceso a la VistaUsuarios del usuario
     *
     * @param vistaUsuarios
     */
    public void setVistaUsuarios(boolean vistaUsuarios) {
        this.vistaUsuarios = vistaUsuarios;
    }

}
