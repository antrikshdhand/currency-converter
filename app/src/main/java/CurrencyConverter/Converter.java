package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter extends JPanel implements ActionListener {
    
    
    private CurrencyExchange cex;
    private JPanel converterPanel;

    private final int PADDING = 10;
    private final int JWIDTH = 100;
    private final int JHEIGHT = 40;

    private JLabel headerLabel;
    private JLabel amountLabel;
    private JTextField amount;
    private JLabel fromLabel;
    private JComboBox<String> fromComboBox;
    private JLabel toLabel;
    private JComboBox<String> toComboBox;
    private JButton convertButton;
    private JLabel conversionResultPreamble;
    private JLabel conversionResultLabel;
    private JButton backButton;

    // BACKEND
    private BasicUser user;

    // temporary array just for testing UI
    // this will be replaced with our actual array of currencies
    private String[] currenciesArr;


    public Converter(CurrencyExchange cex, BasicUser user) {
        this.cex = cex;
        this.user = user;
        this.currenciesArr = user.getCurrencyCodes();

        // set up panel which lives inside the JInternalFrame
        converterPanel = new JPanel(new BorderLayout());
        converterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.converterPanel);

    }


    private void addComponentsToScreen() {

        // HEADER LABEL
        headerLabel = new JLabel("Currency Converter");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        converterPanel.add(headerLabel, BorderLayout.NORTH);
        /////

        // AMOUNT
        amountLabel = new JLabel("<html><font color='orange'>Amount</font></html>");
        amountLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        amountLabel.setBounds(
            20, // set initial x and y
            85, // other components will automatically follow
            this.JWIDTH, 
            this.JHEIGHT);

        amount = new JTextField(10);
        
        // only allow numerical input
        // modified from https://stackhowto.com/how-to-make-jtextfield-accept-only-numbers/
        amount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    if (c != '.') e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        amount.setBounds(
            (int) amountLabel.getBounds().getX() + (int) amountLabel.getBounds().getWidth() + this.PADDING, 
            (int) amountLabel.getBounds().getY(), 
            this.JWIDTH, 
            this.JHEIGHT
        );

        converterPanel.add(amountLabel);
        converterPanel.add(amount);
        /////

        // FROM
        fromLabel = new JLabel("<html><font color='green'>From</font></html>");
        fromLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        fromLabel.setBounds(
            (int) amountLabel.getBounds().getX(), 
            (int) amountLabel.getBounds().getY() + (int) amountLabel.getBounds().getHeight() + this.PADDING, 
            this.JWIDTH, 
            this.JHEIGHT
        );

        fromComboBox = new JComboBox<String>(currenciesArr);
        fromComboBox.setBounds(
            (int) fromLabel.getBounds().getX() + (int) fromLabel.getBounds().getWidth() + this.PADDING, 
            (int) fromLabel.getBounds().getY(), 
            this.JWIDTH, 
            this.JHEIGHT
        );

        converterPanel.add(fromLabel);
        converterPanel.add(fromComboBox);
        /////

        // TO
        toLabel = new JLabel("<html><font color='purple'>To</font></html>");
        toLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        toLabel.setBounds(
            (int) fromLabel.getBounds().getX(), 
            (int) fromLabel.getBounds().getY() + (int) fromLabel.getBounds().getHeight() + this.PADDING, 
            this.JWIDTH, 
            this.JHEIGHT
        );

        toComboBox = new JComboBox<String>(currenciesArr);
        toComboBox.setBounds(
            (int) toLabel.getBounds().getX() + (int) toLabel.getBounds().getWidth() + this.PADDING, 
            (int) toLabel.getBounds().getY(), 
            this.JWIDTH, 
            this.JHEIGHT
        );
        
        converterPanel.add(toLabel);
        converterPanel.add(toComboBox);
        /////

        // CONVERT BUTTON
        convertButton = new JButton("Convert!");
        convertButton.setBounds(
            (int) toLabel.getBounds().getX(),
            (int) toLabel.getBounds().getY() + (int) toLabel.getBounds().getHeight() + 2*this.PADDING, 
            this.JWIDTH, 
            this.JHEIGHT
        );
        convertButton.addActionListener(this);
        convertButton.setActionCommand("convertFunction"); // for mouse-click register
        converterPanel.add(convertButton);
        /////

        // CONVERSION PREAMBLE
        conversionResultPreamble = new JLabel();
        conversionResultPreamble.setBounds(
            (int) convertButton.getBounds().getX(),
            (int) convertButton.getBounds().getY() + (int) convertButton.getBounds().getHeight() + 3*this.PADDING,
            1000,
            this.JHEIGHT
        );
        conversionResultPreamble.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        converterPanel.add(conversionResultPreamble);

        // CONVERSION RESULT
        conversionResultLabel = new JLabel();
        conversionResultLabel.setBounds(
            450,
            (int) conversionResultPreamble.getBounds().getY() + (int) conversionResultPreamble.getBounds().getHeight() + this.PADDING,
            700,
            100
        );
        conversionResultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        conversionResultLabel.setForeground(Color.RED);
        converterPanel.add(conversionResultLabel);

        // BACK BUTTON
        ImageIcon upIcon = new ImageIcon("./src/main/resources/CurrencyConverter/upFolder.png");
        backButton = new JButton("Back", upIcon);
        backButton.setBounds(
            (int) conversionResultPreamble.getBounds().getX(),
            (int) conversionResultLabel.getBounds().getY() + (int) conversionResultLabel.getBounds().getHeight() + 6*this.PADDING,
            this.JWIDTH,
            this.JHEIGHT
        );
        backButton.addActionListener(this);
        backButton.setActionCommand("back"); // for mouse-click register
        converterPanel.add(backButton);

        converterPanel.add(Box.createVerticalGlue());

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("convertFunction")) {
            String currFrom = (String) this.fromComboBox.getSelectedItem();
            String currTo = (String) this.toComboBox.getSelectedItem();
            if (amount.getText().equals("")) return;
            double amount = Double.parseDouble(this.amount.getText());
            double result = this.user.convert(currFrom, currTo, amount);
            
            printConversionStatement(result);
        } else if (e.getActionCommand().equals("back")) {
            this.converterPanel.setVisible(false);
            this.cex.getWelcomeScreen().getWelcomePanel().setVisible(true);
        }
    }


    private void printConversionStatement(double result) {

        StringBuilder s1 = new StringBuilder();
        s1.append("<html>Converting <font color='orange'>");
        s1.append(this.amount.getText());
        s1.append("</font> <font color='green'>");
        s1.append(this.fromComboBox.getSelectedItem());
        s1.append("</font> to <font color='purple'>");
        s1.append(this.toComboBox.getSelectedItem());
        s1.append("</font> gives you </html>");
        this.conversionResultPreamble.setText(s1.toString());

        StringBuilder s2 = new StringBuilder();
        s2.append(String.format("%.2f", result));
        s2.append(" ");
        s2.append(this.toComboBox.getSelectedItem());
        this.conversionResultLabel.setText(s2.toString());

    }

}
