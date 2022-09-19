package CurrencyConverter;

import javax.swing.*;
import javax.swing.UIManager;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class App extends JFrame {

    private Desktop desktop;

    public App() {
        desktop = new Desktop();
    }

    public static void main(String[] args) {
      
        CurrManager manager = new CurrManager();
        manager.openConn();
        manager.addCurrency("AUD", "Australian Dollar");
        manager.addCurrency("USD", "American Dollar");
        manager.addExchange("AUD", "USD", 1.3);
        manager.addExchange("AUD", "USD", 1.4);
        // System.out.println(manager.getCurrName("TSC"));
        // System.out.println(manager.getAllCurrencies());
        
        System.out.println(manager.getExchange("AUD", "USD"));

        
        manager.closeConn();


        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        
        App app = new App();

    }

}
