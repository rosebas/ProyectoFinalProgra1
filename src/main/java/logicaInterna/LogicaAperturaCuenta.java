        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class LogicaAperturaCuenta {
    
   String rutaArchivoAperturaCuentaTxt = System.getProperty("user.dir") + "/CuentasAperturadas/cuentasAperturadas.txt";
   public static int cuentaMonetaria = 20000000;
   public static int cuentaAhorro = 10000000;
   
   public void nuevaCuenta(String montoInicial, String tipoCuenta, String numeroCuenta, String fecha ,String DPI){
   FileWriter escritor = null;
        BufferedWriter bw = null;
        
        try {
            escritor = new FileWriter(rutaArchivoAperturaCuentaTxt, true);
            bw = new BufferedWriter(escritor);
            String nuevaCuenta = DPI + "," + numeroCuenta + "," + tipoCuenta + "," + 
                    montoInicial + "," + fecha;
            
            bw.write(nuevaCuenta);
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
   
   public static int generarNumeroDeCuenta(String tipoDeCuenta) {
    if (tipoDeCuenta.equalsIgnoreCase("Monetaria")) {
        return ++cuentaMonetaria;
    } else if (tipoDeCuenta.equalsIgnoreCase("Ahorro")) {
        return ++cuentaAhorro;
    }
    return -1; 
    }
}

       

