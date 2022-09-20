package CurrencyConverter;

import javax.swing.*;
import javax.swing.UIManager;

public class App extends JFrame {

    private Desktop desktop;
    private CurrManager manager;

    public static void main(String[] args) {

        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        
        App app = new App();

    }

    public CurrManager getManager() {
        return manager;
    }

}
