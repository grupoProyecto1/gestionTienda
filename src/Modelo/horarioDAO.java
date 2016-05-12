/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Joaquin
 */
public class horarioDAO {

    private ArrayList<horario> listaHorarios = new ArrayList<>();
    private Connection con = ConexionBBDD.getConnection();
    private Usuario usuarioLogueado;

    /**
     * Carga el horario del usuario pasado por parametro
     *
     * @param u
     * @throws SQLException
     */
    public void cargaHorarioUsuario(Usuario u) throws SQLException {
        listaHorarios.removeAll(listaHorarios);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from horario where NombreUsuario = '" + u.getNombre() + "'");
        String[] datos = new String[4];
        while (rs.next()) {
            datos[0] = rs.getString("nombreusuario");
            datos[1] = rs.getString("fechainicio");
            datos[2] = rs.getString("fechafinal");
            datos[3] = rs.getString("descripcion");
            horario h1 = new horario(datos[0], datos[1], datos[2], datos[3]);
            listaHorarios.add(h1);
        }
    }

    /**
     * Carga todos los horarios de la base de datos
     *
     * @throws SQLException
     */
    public void cargaHorarios() throws SQLException {
        listaHorarios.removeAll(listaHorarios);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from horario");
        String[] datos = new String[4];
        while (rs.next()) {
            datos[0] = rs.getString("fechainicio");
            datos[1] = rs.getString("fechafinal");
            datos[2] = rs.getString("nombreusuario");
            datos[3] = rs.getString("descripcion");
            horario h1 = new horario(datos[0], datos[1], datos[2], datos[3]);
            listaHorarios.add(h1);
        }
    }

    /**
     * Añade el horario pasado por parametro a la base de datos
     *
     * @param h
     * @throws SQLException
     */
    public void anadirHorario(horario h) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "insert into horario"
                + "(nombreusuario, fechainicio, fechafinal, descripcion)"
                + "values('" + h.getUsuario() + "','" + h.getFechaInicio() + "','" + h.getFechaFin() + "','" + h.getDescripcion() + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    /**
     * Elimina el horario pasado como parametro de la base de datos
     *
     * @param h
     * @throws SQLException
     */
    public void eliminarHorario(horario h) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "delete from horario where nombreusuario='" + h.getUsuario() + "'and fechainicio ='" + h.getFechaInicio() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

    /**
     * Modifica el horario pasado como parametro de la base de datos
     *
     * @param h
     * @throws SQLException
     */
    public void modificarHorario(horario h) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "update horario set fechafinal='" + h.getFechaFin() + "'',descripcion ='" + h.getDescripcion()
                + "'where nombreusuario='" + h.getUsuario() + "' and fechainicio='" + h.getFechaInicio() + "'";
        stm.executeUpdate(consulta);
        stm.close();

    }

    /**
     * Devuelve una lista de horarios
     *
     * @return
     */
    public ArrayList<horario> getListaHorarios() {
        return listaHorarios;
    }

    /**
     * Estable la lista de horarios a otra lista dada
     *
     * @param listaHorarios
     */
    public void setListaHorarios(ArrayList<horario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

}
