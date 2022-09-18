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

    public App(CurrManager manager) {
        desktop = new Desktop();
        this.manager = manager;
    }

    public void run() {
        calculator = new Calculator(this);
        manager.openConn();
        // manager.addCurrency("AUD", "Australian Dollar");
        // manager.addCurrency("USD", "American Dollar");
        // manager.addExchange("AUD", "USD", 1.3);
        
        // System.out.println(manager.getCurrName("TSC"));
        // System.out.println(manager.getAllCurrencies());
        // System.out.println(manager.getAllCurrencies());

        // System.out.println(manager.addCurrency("SGD", "Singaporean Dollar"));

        // Nemo testing command line code, @Antriksh I'm not sure how to connect to your front end.  Comment out when we can connect to front end.
        // gradle console:run -q --console=plain

        System.out.println("Welcome to the Currency Convertor");
        // System.out.println("Enter 1 for User and 2 for Admin: ");
        // Scanner sc = new Scanner(System.in);
        // String cmd = sc.nextLine();
        // if (cmd.equalsIgnoreCase("1")) {
        //     System.out.println("Logged in as User");
        //     while (!cmd.equalsIgnoreCase("q")) {
        //         System.out.println("Enter\nq to quit\n1 for Conversion");
        //         cmd = sc.nextLine();
        //         if (cmd.equalsIgnoreCase("q")) {
        //             return;
        //         }
        //         else if (cmd.equalsIgnoreCase("2")) {
        //             System.out.println("Enter the currencyFrom");
        //             String currencyFrom = sc.nextLine();
        //             System.out.println("Enter the currencyTo");
        //             String currencyTo = sc.nextLine();
        //             System.out.println("Enter the amount");
        //             Double amount = sc.nextDouble();
        //             System.out.println(amount + " " + currencyFrom + " equals " +
        //                 calculator.calculateConverstion(currencyFrom, currencyTo, amount)
        //                 + " " + currencyTo);
        //         }
        //     }
        // }


        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}

    }

    public static void main(String[] args) {
        CurrManager manager = new CurrManager();
        App app = new App(manager);
        app.run();
        manager.closeConn();
    }

    public CurrManager getManager() {
        return manager;
    }

}
