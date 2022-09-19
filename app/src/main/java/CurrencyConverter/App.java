package CurrencyConverter;

import javax.swing.*;
import javax.swing.UIManager;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// test

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class App extends JFrame {

    private Desktop desktop;
    private CurrManager manager;
    private Calculator calculator;

    public Calculator getCalculator() {
        return calculator;
    }

    public App(CurrManager manager) {
        desktop = new Desktop();
        desktop.setApp(this);
        
        this.manager = manager;
    }

    public void run() {
        calculator = new Calculator(this);
        
        // manager.addCurrency("AUD", "Australian Dollar");
        // manager.addCurrency("USD", "American Dollar");
        // manager.addExchange("AUD", "AUD", 1.0);
        // manager.addExchange("AUD", "USD", 1.49);
        // manager.addExchange("USD", "AUD", 0.67);
        
        // System.out.println(manager.getCurrName("TSC"));
        // System.out.println(manager.getAllCurrencies());
        // System.out.println(manager.getAllCurrencies());

        // System.out.println(manager.addCurrency("SGD", "Singaporean Dollar"));

        // gradle run -q --console=plain

        System.out.println("Welcome to the Currency Convertor");

        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}

    }

    public static void main(String[] args) {
        CurrManager manager = new CurrManager();
        App app = new App(manager);
        app.run();
    }

    public CurrManager getManager() {
        return manager;
    }

    public void test() {
        System.out.println(getCalculator().calculateConverstion("AUD", "USD", 100));
    }

}
