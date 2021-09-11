/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import java.util.*;
import excepciones.*;
import dominio.MdEmpleado;
import conexiones.ClsConexion;


/**
 *
 * @author jeant
 */
public class AccesoDatosJDBCImpl implements AccesoDatosJDBC{
    private static final String SQL_SELECT = "SELECT * FROM tb_empleados";
    private static final String SQL_INSERT = "INSERT INTO tb_empleados(nombre, enero, febrero, marzo, total, promedio) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tb_empleados SET nombre = ?, enero = ?, febrero = ?, marzo = ?, total = ?, promedio = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM tb_empleados WHERE codigo = ?";
    
    @Override
    public List<MdEmpleado> listar() throws LecturaDatosEx {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MdEmpleado empleado = null;
        List<MdEmpleado> empleados = new ArrayList<MdEmpleado>();
        try {
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                String nombre = rs.getString(2);
                Double enero = rs.getDouble(3);
                Double febrero = rs.getDouble(4);
                Double marzo = rs.getDouble(5);
                empleado = new MdEmpleado(nombre, enero, febrero, marzo);
                empleado.setCodigo(codigo);
                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.close(conn);
            ClsConexion.close(stmt);
            ClsConexion.close(rs);
        }
        return empleados;
    }
    
    @Override
    public void escribir(MdEmpleado empleado) throws EscrituraDatosEx {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, empleado.getNombre());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
            stmt.setDouble(5, empleado.getTotal());
            stmt.setDouble(6, empleado.getPromedio());
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.close(conn);
            ClsConexion.close(stmt);
        }
    }
    
    @Override
    public void editar(MdEmpleado empleado) throws EscrituraDatosEx {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, empleado.getNombre());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
            stmt.setDouble(5, empleado.getTotal());
            stmt.setDouble(6, empleado.getPromedio());
            stmt.setInt(7, empleado.getCodigo());
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.close(conn);
            ClsConexion.close(stmt);
        }
    }
    
    @Override
    public void borrar(int codigo) throws AccesoDatosEx {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, codigo);
            System.out.println("Ejecutando query: " + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.close(conn);
            ClsConexion.close(stmt);
        }
    }
}