
package logicaInterna;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LogicaReportes {
    // Rutas de los archivos
    String rutaArchivoSaldosTxt = System.getProperty("user.dir") + "/Saldos/saldos.txt";
    String rutaArchivoReporteExcel = System.getProperty("user.dir") + "/reporte_saldos.xlsx";
    private boolean reporteGenerado; // Variable booleana para indicar si el reporte se generó correctamente

    // Constructor
    public LogicaReportes() {
        this.reporteGenerado = false;
    }

    // Método principal para generar el reporte en Excel
    public void generarReporteExcel() {
        // Leer los datos del archivo de saldos
        List<String[]> listaSaldos = leerArchivoSaldos();

        // Crear un nuevo libro de trabajo y una hoja
        Workbook workbook = new XSSFWorkbook();
        Sheet hoja = workbook.createSheet("Reporte Saldos");

        // Títulos de las columnas
        String[] titulos = {"Número de Cuenta", "Saldo Actual"};
        Row filaEncabezados = hoja.createRow(0);

        // Llenar la fila del encabezado
        for (int i = 0; i < titulos.length; i++) {
            Cell celda = filaEncabezados.createCell(i);
            celda.setCellValue(titulos[i]);
            hoja.autoSizeColumn(i);
        }

        // Llenar los datos de los saldos
        for (int i = 0; i < listaSaldos.size(); i++) {
            Row filaDatos = hoja.createRow(i + 1);
            String[] datos = listaSaldos.get(i);

            for (int j = 0; j < datos.length; j++) {
                Cell celdaDato = filaDatos.createCell(j);
                celdaDato.setCellValue(datos[j]);
                hoja.autoSizeColumn(j);
            }
        }

        // Guardar el libro de trabajo en un archivo
        try (FileOutputStream fileOut = new FileOutputStream(rutaArchivoReporteExcel)) {
            workbook.write(fileOut);
            setReporteGenerado(true); // Reporte generado correctamente
            System.out.println("Archivo Excel creado correctamente.");
        } catch (IOException e) {
            setReporteGenerado(false); // Fallo al generar el reporte
            e.printStackTrace();
        }

        // Cerrar el libro de trabajo
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el archivo de saldos y devolver una lista de arreglos de strings
    private List<String[]> leerArchivoSaldos() {
        List<String[]> listaSaldos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoSaldosTxt))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) { // Verificar que la línea tenga el formato correcto
                    listaSaldos.add(partes);
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de saldos: " + e.getMessage());
        }
        return listaSaldos;
    }

    // Getter para la variable booleana reporteGenerado
    public boolean isReporteGenerado() {
        return reporteGenerado;
    }

    // Setter para la variable booleana reporteGenerado
    public void setReporteGenerado(boolean reporteGenerado) {
        this.reporteGenerado = reporteGenerado;
    }
}