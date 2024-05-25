/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaInterna;
import java.util.Random;

public class LogicaAperturaCuenta {
    private static String LogicaAperturaCuenta() {
        Random random = new Random();
        int numero = random.nextInt(90000000) + 10000000;  // Generar un número de 8 dígitos
        return String.valueOf(numero);
}
}