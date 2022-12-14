package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminPortal extends JPanel implements ActionListener {
    

    private CurrencyExchange cex;
    private JPanel adminPortalPanel;

    private JLabel headerLabel;
    private JButton popularCurrencies;
    private JButton newCurrency;
    private JButton updateRates;
    private JButton backButton;
    private JButton resetDB;

    // BACKEND
    private Admin admin;


    public AdminPortal(CurrencyExchange cex, Admin admin) {
        this.cex = cex;
        this.admin = admin;

        this.adminPortalPanel = new JPanel();
        this.adminPortalPanel.setLayout(new BoxLayout(adminPortalPanel, BoxLayout.PAGE_AXIS));
        this.adminPortalPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.adminPortalPanel);

    }


    private void addComponentsToScreen() {
        // HEADER LABEL
        headerLabel = new JLabel("<html><font color='purple'>Admin Portal</font></html>");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        this.adminPortalPanel.add(headerLabel);

        addSpace(adminPortalPanel, 30);

        // SET POPULAR CURRENCIES BUTTON
        popularCurrencies = new JButton("Set popular currencies");
        popularCurrencies.addActionListener(this);
        popularCurrencies.setActionCommand("popular");
        this.adminPortalPanel.add(popularCurrencies);
        
        addSpace(adminPortalPanel, 10);

        // ADD NEW CURRENCIES
        newCurrency = new JButton("Add new currency");
        newCurrency.addActionListener(this);
        newCurrency.setActionCommand("new");
        this.adminPortalPanel.add(newCurrency);

        addSpace(adminPortalPanel, 10);

        // UPDATE EXCHANGE RATES
        updateRates = new JButton("Update exchange rates");
        updateRates.addActionListener(this);
        updateRates.setActionCommand("update");
        this.adminPortalPanel.add(updateRates);

        addSpace(adminPortalPanel, 10);

        // RESET BUTTON
        resetDB = new JButton("Reset database to original 6 currencies");
        resetDB.addActionListener(this);
        resetDB.setActionCommand("reset");
        this.adminPortalPanel.add(resetDB);

        addSpace(adminPortalPanel, 30);
        
        // BACK BUTTON
        ImageIcon upIcon = new ImageIcon("./src/main/resources/CurrencyConverter/upFolder.png");
        backButton = new JButton("Back", upIcon);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setMaximumSize(new Dimension(100, 40));
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
        this.adminPortalPanel.add(backButton);
        /////
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.adminPortalPanel.setVisible(false);
            this.cex.getWelcomeScreen().getWelcomePanel().setVisible(true);
        } else if (e.getActionCommand().equals("popular")) {
            this.adminPortalPanel.setVisible(false);
            SelectPopular selectPopular = new SelectPopular(this.cex, this.admin);

            // application control now moves to SelectPopular.java
        } else if (e.getActionCommand().equals("new")) {
            String[] newCurrency = newCurrencyDialogue();  // currencyCode is index 0, currencyName is index 1
            if (newCurrency == null) return;
           
            // run verification on currCode
            boolean currencyValid = testCurrency(newCurrency);
            if (!currencyValid) {
                displayError();
            } else {
                this.admin.addCurrency(newCurrency[0], newCurrency[1]);
                displaySuccess();
            }
        } else if (e.getActionCommand().equals("update")) {
            this.adminPortalPanel.setVisible(false);
            UpdateExchange updateExchange = new UpdateExchange(this.cex, this.admin);
        } else if (e.getActionCommand().equals("reset")) {
            this.admin.reset();
            JOptionPane.showMessageDialog(this.adminPortalPanel,
                    "Successfully reset database to original 6 currencies."
                );
        }
    }


    private String[] newCurrencyDialogue() {
        JPanel newCurrPanel = new JPanel();

        JTextField currencyCode = new JTextField(3);
        JTextField currencyName = new JTextField(10);

        newCurrPanel.add(new JLabel("Currency code:"));
        newCurrPanel.add(currencyCode);
        newCurrPanel.add(Box.createHorizontalStrut(15));
        newCurrPanel.add(new JLabel("Currency name:"));
        newCurrPanel.add(currencyName);

        int result = JOptionPane.showConfirmDialog(this.adminPortalPanel, newCurrPanel, 
            "Create new currency",
            JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return new String[] {currencyCode.getText(), currencyName.getText()};
        } else {
            return null;
        }
    }


    private boolean testCurrency(String[] currency) {
        String currencyCode = currency[0];
        String currencyName = currency[1];
        // e.g.
        if (currencyCode.length() != 3) {
            return false;
        }
        return true;
    }

    
    private void displayError() {
        JOptionPane.showMessageDialog(this.adminPortalPanel,
            "A currency code should consist of exactly 3 digits. Currency not submitted.",
            "Currency code error",
            JOptionPane.ERROR_MESSAGE
        );
    }


    private void displaySuccess() {
        JOptionPane.showMessageDialog(this.adminPortalPanel,
                "Successfully added new currency."
            );
    }
    

    private void addSpace(JPanel jp, int y) {
        jp.add(Box.createRigidArea(new Dimension(0, y)));
    }


    public JPanel getAdminPortalPanel() {
        return this.adminPortalPanel;
    }
    

}
