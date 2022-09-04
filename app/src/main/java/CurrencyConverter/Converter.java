package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter {
    
    CurrencyExchange cex;
    JPanel parentPanel;

    public Converter(CurrencyExchange cex) {
        this.cex = cex;

        // set up panel which lives inside the JInternalFrame
        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.PAGE_AXIS));
        parentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        cex.add(this.parentPanel);
    }

    private void addComponentsToScreen() {

        // HEADER LABEL
        JLabel headerLabel = new JLabel("Currency Converter");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        parentPanel.add(headerLabel);

        // AMOUNT
        JPanel conversionPanel = new JPanel();
        conversionPanel.setLayout(new BoxLayout(conversionPanel, BoxLayout.LINE_AXIS));
        parentPanel.add(conversionPanel);
        JLabel amountLabel = new JLabel("Amount");
        JTextField amount = new JTextField(10);
        conversionPanel.add(amountLabel);
        conversionPanel.add(amount);

        // FROM
        JPanel fromPanel = new JPanel();
        fromPanel.setLayout(new BoxLayout(fromPanel, BoxLayout.LINE_AXIS));
        parentPanel.add(fromPanel);
        JLabel fromLabel = new JLabel("From");
        String[] arr = new String[] {
            "AUD",
            "EUR",
            "INR",
            "USD",
            "NZD",
            "JPY",
            "GBP"
        };
        JComboBox<String> fromComboBox = new JComboBox<String>(arr);
        fromPanel.add(fromLabel);
        fromPanel.add(fromComboBox);

        // TO
        JPanel toPanel = new JPanel();
        toPanel.setLayout(new BoxLayout(toPanel, BoxLayout.LINE_AXIS));
        parentPanel.add(toPanel);
        JLabel toLabel = new JLabel("To");
        JComboBox<String> toComboBox = new JComboBox<String>(arr);
        toPanel.add(toLabel);
        toPanel.add(toComboBox);

        // CONVERT
        JButton button = new JButton("Convert!");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        parentPanel.add(button);

        String conversionSentence = amount.getText() + (String) fromComboBox.getSelectedItem() + " to ";
        JLabel conversionLabel = new JLabel(conversionSentence);
        parentPanel.add(conversionLabel);
    }

}
