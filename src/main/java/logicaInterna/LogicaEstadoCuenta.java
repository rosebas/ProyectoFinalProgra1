package logicaInterna;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogicaEstadoCuenta {

    private boolean reporteGeneradoCorrectamente;
    private boolean cuentaNoEncontrada = false;

    public boolean isReporteGeneradoCorrectamente() {
        return reporteGeneradoCorrectamente;
    }
    
    public boolean isCuentaNoEncontrada(){
        return cuentaNoEncontrada;
    }
    

    public void generarReporte(String numeroCuenta) {
        String rutaArchivoClientesTxt = System.getProperty("user.dir") + "/Clientes/clientes.txt";
        String rutaArchivoAperturaCuentaTxt = System.getProperty("user.dir") + "/CuentasAperturadas/cuentasAperturadas.txt";
        String rutaArchivoOperacionesTxt = System.getProperty("user.dir") + "/Operaciones/operaciones.txt";
        String rutaArchivoSaldosTxt = System.getProperty("user.dir") + "/Saldos/saldos.txt";
        String rutaReporteTxt = System.getProperty("user.dir") + "/estadoCuenta_" + numeroCuenta + ".txt";

        // Datos de la cuenta y cliente
        String nombreCliente = "";
        String apellidoCliente = "";
        String montoInicial = "";
        double saldoActual = 0.0;

        // Lista para almacenar las transacciones
        List<String[]> transacciones = new ArrayList<>();

        try (BufferedReader brCuentas = new BufferedReader(new FileReader(rutaArchivoAperturaCuentaTxt));
             BufferedReader brClientes = new BufferedReader(new FileReader(rutaArchivoClientesTxt));
             BufferedReader brOperaciones = new BufferedReader(new FileReader(rutaArchivoOperacionesTxt));
             BufferedReader brSaldos = new BufferedReader(new FileReader(rutaArchivoSaldosTxt))) {

            // Obtener el DPI a partir del número de cuenta
            String linea;
            String dpi = null;
            while ((linea = brCuentas.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[1].equals(numeroCuenta)) {
                    dpi = partes[0];
                    montoInicial = partes[3];
                    break;
                }
            }

            if (dpi != null) {
                // Obtener el nombre y apellido del cliente a partir del DPI
                while ((linea = brClientes.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes[0].equals(dpi)) {
                        nombreCliente = partes[1];
                        apellidoCliente = partes[2];
                        break;
                    }
                }

                // Obtener el saldo actual de la cuenta
                while ((linea = brSaldos.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes[0].equals(numeroCuenta)) {
                        saldoActual = Double.parseDouble(partes[1]);
                        break;
                    }
                }

                // Obtener las transacciones realizadas en el presente mes
                LocalDate fechaActual = LocalDate.now();
                String mesActual = String.format("%02d", fechaActual.getMonthValue());
                String anioActual = String.valueOf(fechaActual.getYear());
                while ((linea = brOperaciones.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes[0].equals(numeroCuenta)) {
                        String[] fechaPartes = partes[3].split("-");
                        if (fechaPartes[0].equals(anioActual) && fechaPartes[1].equals(mesActual)) {
                            transacciones.add(partes);
                        }
                    }
                }

                // Crear el archivo de texto
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReporteTxt))) {
                    writer.write("Número de Cuenta: " + numeroCuenta);
                    writer.newLine();
                    writer.write("Nombre Cliente: " + nombreCliente);
                    writer.newLine();
                    writer.write("Apellido Cliente: " + apellidoCliente);
                    writer.newLine();
                    writer.write("Monto Inicial: " + montoInicial);
                    writer.newLine();
                    writer.write("Saldo Actual: " + saldoActual);
                    writer.newLine();
                    writer.newLine();
                    writer.write("Transacciones del mes:");
                    writer.newLine();
                    writer.write("Número de Cuenta, Monto, Tipo, Fecha");
                    writer.newLine();

                    for (String[] transaccion : transacciones) {
                        writer.write(String.join(",", transaccion));
                        writer.newLine();
                    }
                    reporteGeneradoCorrectamente = true;
                }
            } else {
                System.out.println("Número de cuenta no encontrado");
                cuentaNoEncontrada = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            reporteGeneradoCorrectamente = false;
        }
    }
}
