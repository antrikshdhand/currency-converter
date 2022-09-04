package CurrencyConverter;

import javax.swing.*;

public class CurrencyExchange extends JInternalFrame {
    
    public CurrencyExchange() {
        
        // set basic graphics
        super("Currency Exchange", 
        false, //resizable
        true, //closable
        false, //maximizable
        false); //iconifiable
        this.setSize(200, 200);
        this.setLocation(60, 20);

        this.getWelcomeScreen();
    }

    private void getWelcomeScreen() {
        WelcomeScreen ws = new WelcomeScreen();
        this.add(ws.getPanel());
    }

}
