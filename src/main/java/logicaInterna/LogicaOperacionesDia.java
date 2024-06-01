package logicaInterna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class LogicaOperacionesDia {

    private String rutaArchivoOperacionesTxt = System.getProperty("user.dir") + "/Operaciones/operaciones.txt";
    private String rutaArchivoOperacionesDiaTxt = System.getProperty("user.dir") + "/Operaciones/operacionesDia.txt";
    
    private boolean reporteGeneradoCorrectamente = false;
    
    public boolean isReporteGeneradoCorrectamente(){
        return reporteGeneradoCorrectamente;
    }

    public void generarReporteOperacionesDia() {
        LocalDate fechaHoy = LocalDate.now();
        String hoy = fechaHoy.toString();

        double totalDepositos = 0;
        double totalRetiros = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoOperacionesTxt));
             BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoOperacionesDiaTxt))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");
                String fecha = partesLinea[3];

                if (fecha.equals(hoy)) {
                    bw.write(linea);
                    bw.newLine();

                    double monto = Double.parseDouble(partesLinea[1]);
                    if (partesLinea[2].equals("DEPOSITO")) {
                        totalDepositos += monto;
                    } else if (partesLinea[2].equals("RETIRO")) {
                        totalRetiros += monto;
                    }
                }
            }

            bw.write("Total depósitos: " + totalDepositos);
            bw.newLine();
            bw.write("Total retiros: " + totalRetiros);

            reporteGeneradoCorrectamente = true;

        } catch (IOException e) {
            reporteGeneradoCorrectamente = false;
        }
    }

}