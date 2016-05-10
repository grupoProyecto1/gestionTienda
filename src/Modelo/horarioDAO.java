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

    public void cargaHorario() throws SQLException {
        listaHorarios.removeAll(listaHorarios);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from horario");
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

    public void cargaHorarios() throws SQLException {
        listaHorarios.removeAll(listaHorarios);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from horario");
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

    public void anadirHorario(horario h) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "insert into horario"
                + "(nombreusuario, fechainicio, fechafinal, descripcion)"
                + "values('" + h.getUsuario() + "','" + h.getFechaInicio() + "','" + h.getFechaFin() + "','" + h.getDescripcion() + "')";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public void eliminarHorario(horario h) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "delete from horario where nombre='" + h.getUsuario() + "'and fechainicio ='" + h.getFechaInicio() + "'";
        stm.executeUpdate(consulta);
        stm.close();
    }

    public void modificarHorario(horario h) throws SQLException {
        Statement stm = con.createStatement();
        String consulta = "update horario set fechafinal='" + h.getFechaFin() + "'',descripcion ='" + h.getDescripcion()
                + "'where nombreusuario='" + h.getUsuario() + "' and fechainicio='" + h.getFechaInicio() + "'";
        stm.executeUpdate(consulta);
        stm.close();

    }

    public ArrayList<horario> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(ArrayList<horario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

}
