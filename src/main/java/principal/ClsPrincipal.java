/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import negocio.*;
import java.util.Scanner;

/**
 *
 * @author jeant
 */
public class ClsPrincipal {
    private static int opcion = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final NominaEmpleados empleados = new NominaEmpleadosImpl();
    
    /**
     * @param args the commando line arguments
     */
    public static void main(String[] args) {
        while (opcion != 0) {
            try {
                System.out.println("Elegir opcion:\n1-. Agregar empleado"
                        + "\n2-. Mayor y Menor Vendedor por Mes"
                        + "\n3-. Mayor Vendedor General"
                        + "\n4-. Editar Dato Especifico "
                        + "\n5-. Buscar por Cantidad"
                        + "\n6-. Listar Informacion"
                        + "\n7-. Borrar Empleado"
                        + "\n0-. Salir");
                
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Ingrese el nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese enero: ");
                        Double enero = scanner.nextDouble();
                        System.out.println("Ingrese febrero: ");
                        Double febrero = scanner.nextDouble();
                        System.out.println("Ingrese marzo: ");
                        Double marzo = scanner.nextDouble();
                        empleados.agregarEmpleado(nombre, enero, febrero, marzo);
                        scanner.nextLine();
                    }
                    case 2 -> {
                        System.out.println("Ingrese el mes: \n<1>Enero <2>Febrero <3>Marzo");
                        int mes = Integer.parseInt(scanner.nextLine());
                        empleados.buscarMayorYMenorVendedorPorMes(mes);
                    }
                    case 3 -> empleados.buscarMayorVendedorGeneral();
                    case 4 -> {
                        System.out.println("Ingrese el codigo de registro: ");
                        int registro = Integer.parseInt(scanner.nextLine());
                        System.out.println("Elija el dato que desea editar: \n<1>Nombre <2>Enero <3>Febrero <4>Marzo");
                        int columna = Integer.parseInt(scanner.nextLine());
                        System.out.println("Ingrese el nuevo dato: ");
                        String nuevoDato = scanner.nextLine();
                        empleados.editarDato(nuevoDato, registro, columna);
                    }
                    case 5 -> {
                        System.out.println("Ingrese la cantidad: ");
                        Double cantidad = scanner.nextDouble();
                        scanner.nextLine();
                        empleados.buscarPorCantidad(cantidad);
                    }
                    case 6 -> empleados.listarEmpleados();
                    case 7 -> {
                        System.out.println("Ingrese el codigo del empleado que desea borrar: ");
                        int codigo = scanner.nextInt();
                        empleados.borrarEmpleado(codigo);
                    }
                    case 0 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opcion no valida");
                }
                System.out.println("\n");
            } catch (Exception ex) {
                System.out.println("Error\n");
                ex.printStackTrace(System.out);
            }
        }
    }
}
