/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class LogicaInternaRetirarDinero {

    LogicaAperturaCuenta cuenta = new LogicaAperturaCuenta();
    String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";
    String rutaArchivoAperturaCuentaTxt = System.getProperty("user.dir") + "/CuentasAperturadas/cuentasAperturadas.txt";
    String rutaArchivoOperacionesTxt = System.getProperty("user.dir") + "/Operaciones/operaciones.txt";
    String rutaArchivoSaldosTxt = System.getProperty("user.dir") + "/Saldos/saldos.txt";
    LocalDate fechaHoy = LocalDate.now();
    String hoy = fechaHoy.toString();

    private String dpi;
    private String nombreCliente;
    private String apellidoCliente;
    private boolean clienteEncontrado = false;
    private String montoInicial;
    private boolean montoEncontrado = false;
    private boolean cuentaEncontrada = false;
    private boolean saldoInsuficiente = false;

    public LogicaInternaRetirarDinero() {
    }

    public LogicaInternaRetirarDinero(String dpi, String nombreCliente, String apellidoCliente, boolean clienteEncontrado, String montoInicial,
            boolean montoEncontrado, boolean cuentaEncontrada, boolean saldoInsuficiente) {
        this.dpi = dpi;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.clienteEncontrado = clienteEncontrado;
        this.montoInicial = montoInicial;
        this.montoEncontrado = montoEncontrado;
        this.cuentaEncontrada = cuentaEncontrada;
        this.saldoInsuficiente = saldoInsuficiente;

    }

    public LogicaAperturaCuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(LogicaAperturaCuenta cuenta) {
        this.cuenta = cuenta;
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

    public boolean getClienteEncontrado() {
        return clienteEncontrado;
    }

    public void setClienteEncontrado(boolean clienteEncontrado) {
        this.clienteEncontrado = clienteEncontrado;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(String montoInicial) {
        this.montoInicial = montoInicial;
    }

    public boolean getMontoEncontrado() {
        return montoEncontrado;
    }

    public void setMontoEncontrado(boolean montoEncontrado) {
        this.montoEncontrado = montoEncontrado;
    }

    public boolean getCuentaEncontrada() {
        return cuentaEncontrada;
    }

    public void setCuentaEncontrada(boolean cuentaEncontrada) {
        this.cuentaEncontrada = cuentaEncontrada;
    }

    public boolean isSaldoInsuficiente() {
        return saldoInsuficiente;
    }

    public void setSaldoInsuficiente(boolean saldoInsuficiente) {
        this.saldoInsuficiente = saldoInsuficiente;
    }

    
    
    
    public void buscarNumeroCuenta(String NumeroDeCuenta) {
        FileReader lector = null;
        BufferedReader br = null;
        FileReader lectorDos = null;
        BufferedReader brDos = null;

        try {
            // Primera búsqueda para encontrar el dpi a partir del NumeroDeCuenta
            lector = new FileReader(rutaArchivoAperturaCuentaTxt);
            br = new BufferedReader(lector);
            String linea;
            String dpi = null;

            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");
                String cuenta = partesLinea[1];

                if (cuenta.equals(NumeroDeCuenta)) {
                    dpi = partesLinea[0];
                    setDpi(dpi);  // Set the dpi
                    break;
                }
            }

            br.close();  // Close the first BufferedReader

            if (getDpi() != null) {
                // Segunda búsqueda utilizando el dpi encontrado
                lectorDos = new FileReader(rutaArchivoClientesTxt);
                brDos = new BufferedReader(lectorDos);
                String lineaDos;

                while ((lineaDos = brDos.readLine()) != null) {
                    String[] partesLineaDos = lineaDos.split(",");
                    String Dpi = partesLineaDos[0];

                    if (Dpi.equals(getDpi())) {
                        // Recupera los dos elementos necesarios de la línea
                        setNombreCliente(partesLineaDos[1]);  // Ejemplo de primer elemento
                        setApellidoCliente(partesLineaDos[2]);  // Ejemplo de segundo elemento

                        setClienteEncontrado(true);
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

    public void retirar(String numeroCuenta, String montoRetirado) {
        FileReader lector = null;
        BufferedReader br = null;
        FileWriter escritor = null;
        BufferedWriter bw = null;

        try {
            lector = new FileReader(rutaArchivoSaldosTxt);
            br = new BufferedReader(lector);
            StringBuilder contenido = new StringBuilder();
            String linea;
            //boolean cuentaEncontrada = false;
            double saldoActual = 0;

            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");
                String cuenta = partesLinea[0];

                if (cuenta.equals(numeroCuenta)) {
                    saldoActual = Double.parseDouble(partesLinea[1]);
                    setMontoInicial(partesLinea[1]);
                    setMontoEncontrado(true);
                    setCuentaEncontrada(true);

                    double montoARetirar = Double.parseDouble(montoRetirado);
                    if (montoARetirar <= saldoActual) {
                        double nuevoSaldo = saldoActual - montoARetirar;
                        contenido.append(cuenta).append(",").append(nuevoSaldo).append(System.lineSeparator());
                        registrarOperacion(numeroCuenta, montoARetirar);
                    } else {
                        System.out.println("Saldo insuficiente");
                        setSaldoInsuficiente(true);
                        contenido.append(linea).append(System.lineSeparator());
                    }
                } else {
                    contenido.append(linea).append(System.lineSeparator());
                }
            }

            br.close();

            if (getCuentaEncontrada() == true) {
                escritor = new FileWriter(rutaArchivoSaldosTxt);
                bw = new BufferedWriter(escritor);
                bw.write(contenido.toString());
                bw.close();
            } else {
                System.out.println("Cuenta no encontrada");
            }

        } catch (Exception e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los archivos: " + e.getMessage());
            }
        }
    }

    public void registrarOperacion(String numeroCuenta, double montoRetirado) {
        FileWriter escritor = null;
        BufferedWriter bw = null;

        try {
            escritor = new FileWriter(rutaArchivoOperacionesTxt, true);
            bw = new BufferedWriter(escritor);
            String montoString = Double.toString(montoRetirado);
            String operacion = numeroCuenta + "," + montoString + "," + "RETIRO" + "," + hoy;
            bw.write(operacion);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error al registrar la operación: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo de operaciones: " + e.getMessage());
            }
        }
    }
}
