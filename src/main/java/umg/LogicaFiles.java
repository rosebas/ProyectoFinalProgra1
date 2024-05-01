package umg;
import java.util.Scanner;
public class LogicaFiles {

    Scanner scanner = new Scanner(System.in);

    public void menuPrincipal(){

        System.out.println("ingrese su nombre:\t");
        String nombre = scanner.next();

        System.out.println("Su nombre es "+nombre);
    }
}
