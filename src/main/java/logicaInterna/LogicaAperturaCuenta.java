/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class LogicaAperturaCuenta {

    private String nombreCliente;
    private String apellidoCliente;
    private String numeroCuenta;
    private String montoInicial;
    
  
    public LogicaAperturaCuenta() {
    }

    public LogicaAperturaCuenta(String nombreCliente, String apellidoCliente, String numeroCuenta, String montoInicial) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.numeroCuenta = numeroCuenta;
        this.montoInicial = montoInicial;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(String montoInicial) {
        this.montoInicial = montoInicial;
    }
    
    
    
    String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";
    String rutaArchivoSaldosTxt = System.getProperty("user.dir") + "/Saldos/saldos.txt";
    String rutaArchivoAperturaCuentaTxt = System.getProperty("user.dir") + "/CuentasAperturadas/cuentasAperturadas.txt";
    public static int cuentaMonetaria = 20000000;
    public static int cuentaAhorro = 10000000;

    public void buscarDpi(String dpi) {
        FileReader lector = null;
        BufferedReader br = null;

        try {
            lector = new FileReader(rutaArchivoClientesTxt);
            br = new BufferedReader(lector);
            String linea;
           boolean encontrado = false;
           
            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");

                String Dpi = partesLinea[0];
                if (Dpi.equals(dpi)) {
                    setNombreCliente(partesLinea[1]);
                    setApellidoCliente(partesLinea[2]);
                    
                    encontrado = true;
                    
                    break;
                }
            }
            if (!encontrado){
                nombreCliente = "Cliente no encontrado";
                apellidoCliente = "";
            }
           
        } catch (Exception e) {
            System.out.println("Error al leer las tareas");
        } finally {
            try {
                if (br != null){
                    
                
                br.close();
                }
                if (lector != null){
                    lector.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerra el archivo");
            }
        }
    }

    public void nuevaCuenta(String DPI, String numeroCuenta, String tipoCuenta, String montoInicial, String fecha) {
        
        setMontoInicial(montoInicial);
        setNumeroCuenta(numeroCuenta);
        
        FileWriter escritor = null;
        BufferedWriter bw = null;

        try {
            escritor = new FileWriter(rutaArchivoAperturaCuentaTxt, true);
            bw = new BufferedWriter(escritor);
            String nuevaCuenta = DPI + "," + numeroCuenta + "," + tipoCuenta + ","
                    + montoInicial + "," + fecha;
            
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
    
    public void saldoCuentas(String numeroCuenta, String montoInicial){
        FileWriter escritor = null;
        BufferedWriter bw = null;

        try {
            escritor = new FileWriter(rutaArchivoSaldosTxt, true);
            bw = new BufferedWriter(escritor);
            
            String subirSaldos = numeroCuenta + "," + montoInicial;
            bw.write(subirSaldos);
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
