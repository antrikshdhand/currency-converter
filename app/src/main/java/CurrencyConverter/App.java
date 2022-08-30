package CurrencyConverter;

import javax.swing.*;

public class App extends JFrame {
    public App() {
        super("Currency Converter"); // calls the JFrame constructor to set window title
        setSize(1280, 720); // window dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quits run on exit
        setVisible(true);
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set!");
        }
        
    }

    public static void main(String[] args) {
        setLookAndFeel();
        App app = new App();
    }
}
