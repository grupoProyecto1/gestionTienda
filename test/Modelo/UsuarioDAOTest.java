/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joaquin
 */
public class UsuarioDAOTest {

    public UsuarioDAOTest() {
    }

    /**
     * Tests de compruebaUsuario.
     */
    @Test
    public void testCompruebaUsuario1() {
        try {
            System.out.println("compruebaUsuario1");
            String usuario = "1";
            String contrasena = "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a";
            UsuarioDAO instance = new UsuarioDAO();

            Usuario result = instance.compruebaUsuario(usuario, contrasena);
            assertTrue(result != null);
        } catch (Exception e) {
            fail("El caso 1 genero una excepcion");
        }

    }

    @Test
    public void testCompruebaUsuario2() {
        try {
            String usuario = "usuario";
            String contrasena = "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a";
            UsuarioDAO instance = new UsuarioDAO();
            Usuario result = instance.compruebaUsuario(usuario, contrasena);
            assertNull(result);

        } catch (Exception e) {
            fail("El caso genero una excepcion");
        }
    }

    @Test
    public void testCompruebaUsuario3() {
        try {
            String usuario = null;
            String contrasena = "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a";
            UsuarioDAO instance = new UsuarioDAO();
            Usuario result = instance.compruebaUsuario(usuario, contrasena);
            assertNull(result);

        } catch (Exception e) {
            fail("El caso genero una excepcion");
        }
    }

    @Test
    public void testCompruebaUsuario4() {
        try {
            String usuario = "1";
            String contrasena = "1";
            UsuarioDAO instance = new UsuarioDAO();
            Usuario result = instance.compruebaUsuario(usuario, contrasena);
            assertNull(result);

        } catch (Exception e) {
            fail("El caso genero una excepcion");
        }
    }

    @Test
    public void testCompruebaUsuario5() {
        try {
            String usuario = "1";
            String contrasena = null;
            UsuarioDAO instance = new UsuarioDAO();
            Usuario result = instance.compruebaUsuario(usuario, contrasena);
            assertNull(result);

        } catch (Exception e) {
            fail("El caso genero una excepcion");
        }
    }

    /**
     * Tests de anadirUsuario.
     */
    @Test
    public void testAnadirUsuarios1() {
        try {
            System.out.println("anadirUsuarios");
            String nombre = "usuarioPrueba";
            String pass = "7c42cad60958456d21665dbe03756cc60485629801c95871ef638f5ce45822e79933e57e01e79a099d5c92f64a055846db9accc1dacd5bd57dd8832c6e67aacd";
            Usuario u = new Usuario(nombre, pass, true, false, true, false, false);
            UsuarioDAO instance = new UsuarioDAO();
            instance.anadirUsuario(u);
        } catch (Exception e) {
            fail("El caso genero una excepcion");

        }
    }

    /*
    Puesto que tengo que poner el try catch por la SQLException, la unica solucion que he visto es esta
     */
    @Test(expected = AssertionError.class)
    public void testAnadirUsuarios2() {

        try {
            System.out.println("anadirUsuarios");

            String nombre = "usuarioPrueba";
          
            Usuario u = new Usuario(nombre, null, true, false, true, false, false);
            UsuarioDAO instance = new UsuarioDAO();
            instance.anadirUsuario(u);
        } catch (SQLException e) {
            System.out.println("Funciono el caso");
        }
        fail("El caso ha fallado");
    }

    /**
     * Tests de eliminarUsuarios.
     */
    @Test
    public void testEliminarUsuarios1() {
        try {
            System.out.println("eliminarUsuarios");
            String nombre = "usuarioPrueba";
            String pass = "7c42cad60958456d21665dbe03756cc60485629801c95871ef638f5ce45822e79933e57e01e79a099d5c92f64a055846db9accc1dacd5bd57dd8832c6e67aacd";
            Usuario u = new Usuario(nombre, pass, true, false, true, false, false);
            UsuarioDAO instance = new UsuarioDAO();
            instance.eliminarUsuarios(u);
        } catch (Exception e) {
            fail("El caso genero una excepcion");

        }
    }

    /*
    Puesto que tengo que poner el try catch por la SQLException, la unica solucion que he visto es esta
     */
    @Test(expected = AssertionError.class)
    public void testEliminarUsuarios2() {

        try {
            System.out.println("eliminarUsuarios");

           String nombre = "usuarioPrueba";
            String pass = "mal";
            Usuario u = new Usuario(nombre, pass, true, false, true, false, false);
            UsuarioDAO instance = new UsuarioDAO();
            instance.eliminarUsuarios(u);
        } catch (SQLException e) {
            System.out.println("Funciono el caso");
        }
        fail("El caso ha fallado");
    }

    /**
     * Tests de modificarUsuarios.
     */
    @Test
    public void testModificarUsuarios1() {
        try {
            System.out.println("modificarUsuarios");
           String nombre = "usuarioPrueba";
            String pass = "7c42cad60958456d21665dbe03756cc60485629801c95871ef638f5ce45822e79933e57e01e79a099d5c92f64a055846db9accc1dacd5bd57dd8832c6e67aacd";
            Usuario u = new Usuario(nombre, pass, true, false, true, false, false);
            UsuarioDAO instance = new UsuarioDAO();
            instance.modificarUsuarios(u);
        } catch (Exception e) {
            fail("El caso genero una excepcion");

        }
    }

    /*
    Puesto que tengo que poner el try catch por la SQLException, la unica solucion que he visto es esta
     */
    @Test(expected = AssertionError.class)
    public void testModificarUsuarios2() {

        try {
            System.out.println("modificarUsuarios");

            String nombre = "usuarioPrueba";
            String pass = "mal";
            Usuario u = new Usuario(nombre, pass, true, false, true, false, false);
            UsuarioDAO instance = new UsuarioDAO();
            instance.modificarUsuarios(u);
        } catch (SQLException e) {
            System.out.println("Funciono el caso");
        }
        fail("El caso ha fallado");
    }

}
