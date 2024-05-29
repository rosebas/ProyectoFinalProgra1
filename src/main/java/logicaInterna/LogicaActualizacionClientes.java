/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Keily Orellana
 */
public class LogicaActualizacionClientes {

    String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";

    public void buscarDpi(String dpi) {
        FileReader lector = null;
        BufferedReader br = null;

        try {
            lector = new FileReader(rutaArchivoClientesTxt);
            br = new BufferedReader(lector);
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");

                String Dpi = partesLinea[0];
                if (Dpi.equals(dpi)) {

                    String nombreCliente = partesLinea[1];
                    String apellidoCliente = partesLinea[2];
                    String direccionCliente = partesLinea[3];
                    String telefonoCliente = partesLinea[4];
                    String correoCliente = partesLinea[5];
                    String ocupacionCliente = partesLinea[6];
                    String ingresosCliente = partesLinea[7];
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer las tareas");
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("Error al cerra el archivo");
            }
        }
    }
    
    public void actualizarCliente(String dpi, String direccionCliente, String telefonoCliente, String correoCliente,
            String ocupacionCliente, String ingresosCliente){
        
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
