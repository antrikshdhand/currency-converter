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
import java.util.*;

public class App extends JFrame {

    private Desktop desktop;
    private CurrManager manager;
    private Calculator calculator;

    public App() {
        desktop = new Desktop();
    }

    public void run() {
        calculator = new Calculator(this);
        manager = new CurrManager();
        manager.openConn();
        // manager.addCurrency("AUD", "Australian Dollar");
        // manager.addCurrency("USD", "American Dollar");
        // manager.addExchange("AUD", "USD", 1.3);
        
        // System.out.println(manager.getCurrName("TSC"));
        // System.out.println(manager.getAllCurrencies());
        // System.out.println(manager.getAllCurrencies());

        // Nemo testing command line code, @Antriksh I'm not sure how to connect to your front end.  Comment out when we can connect to front end.
        // gradle console:run -q --console=plain

        System.out.println("Welcome to the Currency Convertor");
        System.out.println("Enter 1 for User and 2 for Admin: ");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        if (cmd.equalsIgnoreCase("1")) {
    
            System.out.println("Logged in as User");
            System.out.println("Enter the currencyFrom");
            String currencyFrom = sc.nextLine();
            System.out.println("Enter the currencyTo");
            String currencyTo = sc.nextLine();
            System.out.println("Enter the amount");
            Double amount = sc.nextDouble();
            System.out.println(amount + " " + currencyFrom + " equals " +
                calculator.calculateConverstion(currencyFrom, currencyTo, amount)
                + " " + currencyTo);

        }


        manager.closeConn();


        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}

    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public CurrManager getManager() {
        return manager;
    }

}
