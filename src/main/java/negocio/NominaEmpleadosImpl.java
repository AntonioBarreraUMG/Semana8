/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.*;
import java.util.*;
import excepciones.*;
import dominio.MdEmpleado;

/**
 *
 * @author jeant
 */
public class NominaEmpleadosImpl implements NominaEmpleados{
    private final AccesoDatosJDBC datos;
    private final String errorAcceso = "Error de acceso a datos";
    private final String errorLectura = "Error de lectura de datos";
    private final String errorEscritura = "Error de escritura de datos";
    
    public NominaEmpleadosImpl(){
        this.datos = new AccesoDatosJDBCImpl();
    }
    
    public Double calcularTotal() {
        Double total = 0.0;
        List<MdEmpleado> empleados = null;
        try {
            empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                total += empleado.getTotal();
            }
        } catch (LecturaDatosEx ex) {
            System.out.println(errorLectura);
            ex.printStackTrace(System.out);
        }  
        return total;
    }

    @Override
    public void agregarEmpleado(String nombreEmpleado, Double enero, Double febrero, Double marzo) {
        MdEmpleado empleado = new MdEmpleado(nombreEmpleado, enero, febrero, marzo);
        try {
            datos.escribir(empleado);
            System.out.println("**Registro exitoso**");
        } catch (EscrituraDatosEx ex) {
            System.out.println(errorEscritura);
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarMayorYMenorVendedorPorMes(int mes) {
        mes++;
        Double mayor = 0.0;
        Double menor = calcularTotal();
        String nombreMayor = null;
        String nombreMenor = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                String[] empDatos = empleado.toString().split("\\|");
                if (Double.valueOf(empDatos[mes]) > mayor) {
                    mayor = Double.valueOf(empDatos[mes]);
                    nombreMayor = empleado.getNombre();
                }
                if (Double.valueOf(empDatos[mes]) < menor) {
                    menor = Double.valueOf(empDatos[mes]);
                    nombreMenor = empleado.getNombre();
                }
            }
            if (nombreMayor != null && nombreMenor != null) {
                System.out.println("MAYOR vendedor: " + nombreMayor + " con " + mayor
                               + "\nMENOR vendedor: " + nombreMenor + " con " + menor);
            }
        } catch (LecturaDatosEx ex) {
            System.out.println(errorLectura);
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarMayorVendedorGeneral() {
        Double mayor = 0.0;
        String resultado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for(MdEmpleado empleado : empleados) {
                if (empleado.getTotal() > mayor) {
                    resultado = empleado.getNombre() + " con " + empleado.getTotal();
                }
            }
            System.out.println("El MAYOR vendedor general es " + resultado);
        } catch (LecturaDatosEx ex) {
            System.out.println(errorAcceso);
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void editarDato(String nuevoDato, int codigo, int columna) {
        MdEmpleado nuevoEmpleado = null;
        String nombre = null;
        Double enero = null;
        Double febrero = null;
        Double marzo = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                if (empleado.getCodigo() == codigo) {
                    nombre = empleado.getNombre();
                    enero = empleado.getEnero();
                    febrero = empleado.getFebrero();
                    marzo = empleado.getMarzo();
                    break;
                }
            }  
            switch (columna) {
                case 1 -> nombre = nuevoDato;
                case 2 -> enero = Double.valueOf(nuevoDato);
                case 3 -> febrero = Double.valueOf(nuevoDato);
                case 4 -> marzo = Double.valueOf(nuevoDato);
            }
            nuevoEmpleado = new MdEmpleado(nombre, enero, febrero, marzo);
            nuevoEmpleado.setCodigo(codigo);
            datos.editar(nuevoEmpleado);
        } catch (EscrituraDatosEx ex) {
            System.out.println(errorEscritura);
            ex.printStackTrace(System.out);
        } catch (LecturaDatosEx ex) {
            System.out.println(errorAcceso);
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPorCantidad(Double cantidad) {
        String resultado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                if (Objects.equals(empleado.getEnero(), cantidad)) {
                    resultado = empleado.getNombre() + " en el mes de enero";
                    break;
                } else if (Objects.equals(empleado.getFebrero(), cantidad)) {
                    resultado = empleado.getNombre() + " en el mes de febrero";
                    break;
                } else if (Objects.equals(empleado.getMarzo(), cantidad)) {
                    resultado = empleado.getNombre() + " en el mes de marzo";
                    break;
                }
            }
        } catch (LecturaDatosEx ex) {
            System.out.println(errorLectura);
            ex.printStackTrace(System.out);
        }
        System.out.println("Resultado: " + resultado);
    }

    @Override
    public void listarEmpleados() {
        try {
            List<MdEmpleado> empleados = datos.listar();
            if (empleados.size() > 0) {
                empleados.forEach(empleado -> {
                    System.out.println(empleado);
                });
            } else {
                System.out.println("No existen registros");
            }
        } catch (LecturaDatosEx ex) {
            System.out.println(errorAcceso);
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void borrarEmpleado(int codigo) {
        try {
            datos.borrar(codigo);
        } catch (AccesoDatosEx ex) {
            System.out.println(errorAcceso);
            ex.printStackTrace(System.out);
        }
    }
}
