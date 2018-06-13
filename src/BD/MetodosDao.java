/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Presentacion.frmLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class MetodosDao {

    ConexionBD cnx = new ConexionBD();

    public int validarUsuario(String usuario) {

        int resultado = 0;

        String SSQL = "SELECT * FROM profesor WHERE usuario='" + usuario + "'";
        Connection conect = null;

        try {
            System.out.println("usuario: " + usuario);

            conect = cnx.conectar();
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            if (rs.next()) {

                resultado = 1;

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

        } finally {

            try {
                
                conect.close();
                
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

            }

        }

        return resultado;

    }



    public int validarIngreso(String usuario, String clave) {

        int resultado = 0;

        String SSQL = "SELECT * FROM profesor WHERE usuario='" + usuario + "' AND clave='" + clave + "'";
        Connection conect = null;

        try {
            System.out.println("usuario: " + usuario);
            System.out.println("clave: " + clave);
            conect = cnx.conectar();
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            if (rs.next()) {

                resultado = 1;

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

        } finally {

            try {

                conect.close();

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

            }

        }

        return resultado;

    }

    public int validarCodigo(String codigo, String grupo) {
        
        int resultado = 0;
        String SSQL = "SELECT * FROM estudiante WHERE codigo='" + codigo + "' AND grupo_codigo='" + grupo + "'";
        Connection conect = null;
        try {
            System.out.println("usuario: " + codigo);
            conect = cnx.conectar();
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(SSQL);
            if (rs.next()) {
                resultado = 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conect.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);
            }
        }

        return resultado;
    }

}
