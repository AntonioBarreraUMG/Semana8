/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.*;
import excepciones.*;
import dominio.MdEmpleado;

/**
 *
 * @author jeant
 */
public interface AccesoDatosJDBC {
    public List<MdEmpleado> listar() throws LecturaDatosEx;
    public void escribir(MdEmpleado empleado) throws EscrituraDatosEx;
    public void editar(MdEmpleado empleado) throws EscrituraDatosEx;
    public void borrar(int codigo) throws AccesoDatosEx;
}