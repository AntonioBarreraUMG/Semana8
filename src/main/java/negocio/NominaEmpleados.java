/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author jeant
 */
public interface NominaEmpleados {
    public void agregarEmpleado(String nombreEmpleado, Double enero, Double febrero, Double marzo);
    public void buscarMayorYMenorVendedorPorMes(int mes);
    public void buscarMayorVendedorGeneral();
    public void editarDato(String nuevoDato, int registro, int columna);
    public void buscarPorCantidad(Double cantidad);
    public void listarEmpleados();
    public void borrarEmpleado(int codigo);
}
