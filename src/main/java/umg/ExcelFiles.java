package umg;
import java.util.Scanner;
public class ExcelFiles {
    Scanner scanner = new Scanner(System.in);

    public void holaTu() {
        System.out.println("Este cosa la cree para que vieran como deben de usar el git");
        //Cada linea que agreguen, deben de comentar para ver que hicieron y para que lo hicieron
        //Por ejenmplo
        String Javier = "Hola soy javier"; //Variable javier para hacer algo
        System.out.println(Javier);

        System.out.println("ingresa tu nombre");
        String nombre = scanner.next();

        System.out.println(nombre);
    }
}
