package CurrencyConverter;

import javax.swing.*;

public class CurrencyExchange extends JInternalFrame {
    
    // CurrencyExchange is a JInternalFrame which we will add JPanels to

    private JDesktopPane desktop;
    private WelcomeScreen welcomeScreen;


    /**
     * Construtor for currency exchange class.
     * Used to open new window for currency exchange in the application.
     * 
     * @param desktop the Java swing window
     */
    public CurrencyExchange(JDesktopPane desktop) {
        
        // set basic graphics
        super("Currency Exchange", 
        false, //resizable
        true, //closable
        false, //maximizable
        false); //iconifiable

        this.desktop = desktop;
        this.setSize(200, 200);
        this.setLocation(60, 20);
        this.setVisible(true);
        desktop.add(this); // add the JInternalFrame to the JDesktopPane

        try {
            this.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }

        welcomeScreen = new WelcomeScreen(this);
        
        // application control now moves to WelcomeScreen.java

    }

    
    /**
     * Return the homepage of the app.
     * 
     * @return the home page of the app 
     */
    public WelcomeScreen getWelcomeScreen() {
        return this.welcomeScreen;
    }

}
