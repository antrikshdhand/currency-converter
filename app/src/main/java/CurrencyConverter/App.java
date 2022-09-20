package CurrencyConverter;

import javax.swing.*;
import javax.swing.UIManager;

public class App extends JFrame {

    private Desktop desktop;


    /**
     * Constructor for app.
     * Just creates a new Desktop - basically opening the new window on gradle run.
     */
    public App() {
        desktop = new Desktop();
    }

    /**
     * Main function is what is run as the code is executed.
     * 
     * @param args - A list of arguments from command line.
     */
    public static void main(String[] args) {

        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        
        App app = new App();

    }

}
