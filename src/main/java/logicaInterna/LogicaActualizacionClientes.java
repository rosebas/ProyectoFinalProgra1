/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.FileWriter;

/**
 *
 * @author Keily Orellana
 */
public class LogicaActualizacionClientes {

    String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";
    private String direccionCliente;
    private String telefonoCliente;
    private String correoCliente;
    private String ocupacionCliente;
    private String ingresosCliente;
    private boolean cambiosCorrectos = false;

    public LogicaActualizacionClientes() {
    }

    public LogicaActualizacionClientes(String direccionCliente, String telefonoCliente, 
            String correoCliente, String ocupacionCliente, String ingresosCliente, boolean cambiosCorrectos) {
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.correoCliente = correoCliente;
        this.ocupacionCliente = ocupacionCliente;
        this.ingresosCliente = ingresosCliente;
        this.cambiosCorrectos = cambiosCorrectos;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getOcupacionCliente() {
        return ocupacionCliente;
    }

    public void setOcupacionCliente(String ocupacionCliente) {
        this.ocupacionCliente = ocupacionCliente;
    }

    public String getIngresosCliente() {
        return ingresosCliente;
    }

    public void setIngresosCliente(String ingresosCliente) {
        this.ingresosCliente = ingresosCliente;
    }

    public boolean getCambiosCorrectos() {
        return cambiosCorrectos;
    }

    public void setCambiosCorrectos(boolean cambiosCorrectos) {
        this.cambiosCorrectos = cambiosCorrectos;
    }
    
    

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

                    setDireccionCliente(partesLinea[3]);
                    setTelefonoCliente(partesLinea[4]);
                    setCorreoCliente(partesLinea[5]);
                    setOcupacionCliente(partesLinea[6]);
                    setIngresosCliente(partesLinea[7]);
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

    public void actualizarCambios(String dpi, String direccion, String telefono, String correo,
        String ocupacion, String ingresos) {

    FileWriter escritor = null;
    BufferedWriter bw = null;

    FileReader lector = null;
    BufferedReader br = null;

    try {
        lector = new FileReader(rutaArchivoClientesTxt);
        br = new BufferedReader(lector);
        StringBuilder contenido = new StringBuilder();
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] partesLinea = linea.split(",");
            String Dpi = partesLinea[0];
            
            if (Dpi.equals(dpi)) {
                linea = partesLinea[0] + "," + partesLinea[1] + "," + partesLinea[2] + ","
                        + direccion + "," + telefono + "," + correo + "," + ocupacion + "," + ingresos;
                setCambiosCorrectos(true);
            }

            contenido.append(linea).append(System.lineSeparator());
        }

        br.close();

        escritor = new FileWriter(rutaArchivoClientesTxt);
        bw = new BufferedWriter(escritor);
        bw.write(contenido.toString());

    } catch (Exception e) {
        System.out.println("Error al procesar el archivo: " + e.getMessage());
    } finally {
        try {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar el archivo: " + e.getMessage());
        }
    }
}  

}
