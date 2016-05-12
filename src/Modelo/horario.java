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
public class horario {

    private String fechaInicio;
    private String fechaFin;
    private String usuario;
    private String descripcion;

    /**
     * Constructor parametrizado para crear un objeto de tipo horario
     *
     * @param fechaInicio
     * @param fechaFin
     * @param usuario
     * @param descripcion
     */
    public horario(String fechaInicio, String fechaFin, String usuario, String descripcion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la fecha de inicio
     *
     * @return
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio
     *
     * @param fechaInicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve la fecha de finalizacion
     *
     * @return
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de finalizacion
     *
     * @param fechaFin
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Devuelve el usuario
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve la descripcion
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
