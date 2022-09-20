package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateExchange extends JPanel implements ActionListener {
    
    private CurrencyExchange cex;
    private JPanel topLevelPanel;
    private JPanel topText;
    private JPanel combos;
    private JPanel middleText;
    private JPanel newRatePanel;
    private JPanel bottomPanel;

    private JLabel headerLabel;
    private JButton backButton;
    private JLabel informationLabel1;
    private JLabel curr1Label;
    private JLabel curr2Label;
    private JComboBox<String> curr1Combo;
    private JComboBox<String> curr2Combo;
    private JButton setCurrenciesButton;
    private JLabel confirmCurrenciesLabel;
    private JLabel informationLabel2;
    private JTextField newExchangeRate;
    private JLabel newExchangeRateLabel;
    private JButton submitRate;
    private String curr1;
    private String curr2;
    private String amount;

    // BACKEND
    private Admin admin;
    private String[] currencies;

    public UpdateExchange(CurrencyExchange cex, Admin admin) {
        this.cex = cex;
        this.admin = admin;
        this.currencies = admin.getCurrencyCodes();
        
        this.topLevelPanel = new JPanel();
        this.topLevelPanel.setLayout(new GridLayout(0, 1));
        this.topLevelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.topLevelPanel);
    }

    private void addComponentsToScreen() {
        // HEADER LABEL AND INFO
        topText = new JPanel();
        topText.setLayout(new BoxLayout(topText, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(topText);

        headerLabel = new JLabel("Update Exchange Rates");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        topText.add(headerLabel);

        topText.add(Box.createVerticalGlue());
        
        informationLabel1 = new JLabel("<html><font color='red'>1.</font> Select the exchange rate you wish to update.</html>");
        informationLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        topText.add(informationLabel1);
        /////

        // COMBO BOXES AND APPLY BUTTON
        combos = new JPanel();
        combos.setLayout(new BoxLayout(combos, BoxLayout.LINE_AXIS));
        this.topLevelPanel.add(combos);

        curr1Label = new JLabel("<html><font color='green'>Currency 1:</font></html>");
        curr1Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        curr2Label = new JLabel("<html><font color='orange'>Currency 2:</font></html>");
        curr2Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
    
        curr1Combo = new JComboBox<String>(currencies);
        curr1Combo.setPreferredSize(new Dimension(100, 40));
        curr1Combo.setMaximumSize(new Dimension(100, 40));
        curr2Combo = new JComboBox<String>(currencies);     
        curr2Combo.setPreferredSize(new Dimension(100, 40));
        curr2Combo.setMaximumSize(new Dimension(100, 40));

        setCurrenciesButton = new JButton("Apply");
        setCurrenciesButton.setPreferredSize(new Dimension(100, 30));
        setCurrenciesButton.setMaximumSize(new Dimension(100, 30));
        setCurrenciesButton.addActionListener(this);
        setCurrenciesButton.setActionCommand("setCurrencies");

        combos.add(curr1Label);
        combos.add(curr1Combo);
        combos.add(Box.createRigidArea(new Dimension(100, 0)));
        combos.add(curr2Label);
        combos.add(curr2Combo);
        combos.add(Box.createRigidArea(new Dimension(150, 0)));
        combos.add(setCurrenciesButton);
        combos.add(Box.createRigidArea(new Dimension(300, 0)));
        /////

        // CONFIRMATION AND STEP 2
        middleText = new JPanel();
        middleText.setLayout(new BoxLayout(middleText, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(middleText);
        
        confirmCurrenciesLabel = new JLabel();
        confirmCurrenciesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        middleText.add(confirmCurrenciesLabel);

        middleText.add(Box.createVerticalGlue());

        informationLabel2 = new JLabel("<html><font color='red'>2.</font> Enter the new exchange rate.</html>");
        informationLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        middleText.add(informationLabel2);
        /////

        // NEW EXCHANGE RATE
        newRatePanel = new JPanel();
        newRatePanel.setLayout(new BoxLayout(newRatePanel, BoxLayout.LINE_AXIS));
        this.topLevelPanel.add(newRatePanel);

        newExchangeRateLabel = new JLabel("Enter rate:");
        newExchangeRateLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        
        newExchangeRate = new JTextField(10);
        newExchangeRate.setPreferredSize(new Dimension(100, 30));
        newExchangeRate.setMaximumSize(new Dimension(100, 30));
        
        submitRate = new JButton("Submit");
        submitRate.setPreferredSize(new Dimension(100, 30));
        submitRate.setMaximumSize(new Dimension(100, 30));
        submitRate.addActionListener(this);
        submitRate.setActionCommand("submitRate");

        newRatePanel.add(newExchangeRateLabel);
        newRatePanel.add(Box.createRigidArea(new Dimension(50, 0)));
        newRatePanel.add(newExchangeRate);
        newRatePanel.add(Box.createRigidArea(new Dimension(50, 0)));
        newRatePanel.add(submitRate);
        /////

        // BACK BUTTON
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        this.topLevelPanel.add(bottomPanel);

        ImageIcon upIcon = new ImageIcon(this.getClass().getResource("upFolder.png").getPath());
        backButton = new JButton("Back", upIcon);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setMaximumSize(new Dimension(100, 40));
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
        bottomPanel.add(backButton);
        /////
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.topLevelPanel.setVisible(false);
            this.cex.getWelcomeScreen().getAdminPortal().getAdminPortalPanel().setVisible(true);
        } else if (e.getActionCommand().equals("setCurrencies")) {
            this.curr1 = (String) this.curr1Combo.getSelectedItem();
            this.curr2 = (String) this.curr2Combo.getSelectedItem();
            printConfirmStatement();
        } else if (e.getActionCommand().equals("submitRate")) {
            this.amount = this.newExchangeRate.getText();
            Double amtDouble = Double.parseDouble(this.amount);
            this.admin.addExchange(curr1, curr2, amtDouble);
            printSuccessPopup();
        }
    }

    private void addSpace(JPanel jp, int y) {
        jp.add(Box.createRigidArea(new Dimension(0, y)));
    }

    private void printConfirmStatement() {
        StringBuilder s1 = new StringBuilder();
        s1.append("<html>You have selected the exchange <font color='green'>");
        s1.append(this.curr1);
        s1.append("</font>/<font color='orange'>");
        s1.append(this.curr2);
        s1.append("</font>.</html>");
        this.confirmCurrenciesLabel.setText(s1.toString());
    }

    private void printSuccessPopup() {
        JOptionPane.showMessageDialog(this.topLevelPanel,
                "Successfully updated the exchange rate."
            );
    }

}
