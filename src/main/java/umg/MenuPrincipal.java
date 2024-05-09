package umg;

import javax.swing.*;

public class MenuPrincipal extends JFrame{
    private JPanel panel;
    private JButton actualizacionCliente;
    private JButton realizarDeposito;
    private JButton reportes;
    private JButton registroCliente;
    private JButton aperturaCuenta;
    private JButton retirarDinero;

    public static void main(String[] args){
        MenuPrincipal menu = new MenuPrincipal();
        menu.setContentPane(new MenuPrincipal().panel);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        menu.pack();
    }
}
