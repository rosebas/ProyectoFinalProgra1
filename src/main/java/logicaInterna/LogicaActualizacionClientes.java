/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Keily Orellana
 */
public class LogicaActualizacionClientes {
    
    
    public void buscarCliente(String dpi){
        String rutaArchivoTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";
        FileReader lector = null;
        BufferedReader br = null;
        
        try {
            lector = new FileReader (rutaArchivoTxt);
            br = new BufferedReader (lector);
            String linea;
            
            while ((linea = br.readLine())!=null){
                String[] partesLinea = linea.split(",");

                String Dpi = partesLinea[0];
                if(Dpi.equals(dpi)){
                    
                    String direccionCliente = partesLinea[3];
                    String telefonoCliente = partesLinea[4];
                    String correoCliente = partesLinea[5];
                    String ocupacionCliente = partesLinea[6];
                    String ingresosCliente = partesLinea[7];
                } 
            }
            
                        
            
            
        } catch (Exception e) {
            System.out.println("Error al leer las tareas");
        } finally {
            try{
                br.close();
            } catch(Exception e){
                System.out.println("Error al cerra el archivo");
            }
        }
    }

    public void actualizarCliente (String direccionCliente, String dpiCliente, String telefonoCliente, 
            String correoCliente, String ocupacionCliente, String ingresosMensuales){
        
    }
}
