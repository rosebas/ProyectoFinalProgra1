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
 * @author PC
 */
public class LogicaInternaDeposito {

    String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";

    String rutaArchivoAperturaCuentaTxt = System.getProperty("user.dir") + "/CuentasAperturadas/cuentasAperturadas.txt";

    private String dpi;
    private String nombreCliente;
    private String apellidoCliente;

    public LogicaInternaDeposito() {
    }

    public LogicaInternaDeposito(String dpi, String nombreCliente, String apellidoCliente) {
        this.dpi = dpi;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
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

    public void buscarNumeroCuenta(String NumeroDeCuenta) {
        FileReader lector = null;
        BufferedReader br = null;
        FileReader lectorDos = null;
        BufferedReader brDos = null;

        try {
            lector = new FileReader(rutaArchivoAperturaCuentaTxt);
            br = new BufferedReader(lector);
            String linea;
            String dpi = null;

            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");
                String cuenta = partesLinea[2];

                if (cuenta.equals(NumeroDeCuenta)) {
                    dpi = partesLinea[3];
                    setDpi(dpi);
                    break;
                }
            }

            br.close();

            if (dpi != null) {
                lectorDos = new FileReader(rutaArchivoClientesTxt);
                brDos = new BufferedReader(lectorDos);
                String lineaDos;

                while ((lineaDos = brDos.readLine()) != null) {
                    String[] partesLineaDos = lineaDos.split(",");
                    String Dpi = partesLineaDos[0];

                    if (Dpi.equals(getDpi())) {
                        setNombreCliente(partesLineaDos[1]);
                        setApellidoCliente(partesLineaDos[2]);

                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (brDos != null) {
                    brDos.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los archivos: " + e.getMessage());
            }
        }
    }

}
