package CurrencyConverter;

import javax.swing.*;
import javax.swing.UIManager;

public class App extends JFrame {

    private Desktop desktop;

    public App() {
        desktop = new Desktop();
    }

    public static void main(String[] args) {
        
        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        
        App app = new App();

    }

}
