/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author sebas
 */
public class LogicaRegistroCliente {

    String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";

    public void nuevoCliente(String Dpi, String nombreCliente, String apellidoCliente, String direccionCliente,
            String telefonoCliente, String correoCliente, String ocupacionCliente, String ingresosMensuales) {

        FileWriter escritor = null;
        BufferedWriter bw = null;

        try {
            escritor = new FileWriter(rutaArchivoClientesTxt, true);
            bw = new BufferedWriter(escritor);
            String nuevoCliente = Dpi + "," + nombreCliente + "," + apellidoCliente + "," + 
                    direccionCliente + "," + telefonoCliente + "," + correoCliente + "," + 
                    ocupacionCliente + "," + ingresosMensuales;
            
            bw.write(nuevoCliente);
            bw.newLine();


        } catch (Exception e) {
            System.out.println("Error al guardar el archivo " + e.getMessage());
        } finally {

            try {
                bw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo " + e.getMessage());
            }
        }
    }

}
