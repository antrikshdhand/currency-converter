package CurrencyConverter;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectPopular extends JPanel implements ActionListener {
    
    private CurrencyExchange cex;
    private JPanel topLevelPanel;

    private JLabel headerLabel;
    private JButton backButton;
    private JButton applyButton;
    private JCheckBox[] boxes;

    // BACKEND
    private Admin admin;
    private String[] currenciesArr;

    public SelectPopular(CurrencyExchange cex, Admin admin) {
        this.cex = cex;
        this.admin = admin;
        this.currenciesArr = admin.getCurrencyCodes();

        this.topLevelPanel = new JPanel();
        this.topLevelPanel.setLayout(new BoxLayout(topLevelPanel, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.topLevelPanel);
    }

    private void addComponentsToScreen() {
        // HEADER LABEL
        headerLabel = new JLabel("<html><font color='orange'>Select 4 popular currencies</font></html>");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        this.topLevelPanel.add(headerLabel);

        addSpace(topLevelPanel, 20);

        // CHECKBOXES
        boxes = new JCheckBox[currenciesArr.length]; 
        
        // make a new checkbox for each currency
        for (int i = 0; i < boxes.length; i++)
            boxes[i] = new JCheckBox(currenciesArr[i]); 
        
        // add all checkboxes to JPanel
        for (JCheckBox box : boxes) {
            this.topLevelPanel.add(box);
        }

        addSpace(topLevelPanel, 20);

        applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(100, 30));
        applyButton.setMaximumSize(new Dimension(100, 30));
        applyButton.addActionListener(this);
        applyButton.setActionCommand("setPopular");
        this.topLevelPanel.add(applyButton);

        addSpace(topLevelPanel, 40);

        // BACK BUTTON
        ImageIcon upIcon = new ImageIcon(this.getClass().getResource("upFolder.png").getPath());
        backButton = new JButton("Back", upIcon);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setMaximumSize(new Dimension(100, 40));
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
        this.topLevelPanel.add(backButton);
        /////
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.topLevelPanel.setVisible(false);
            this.cex.getWelcomeScreen().getAdminPortal().getAdminPortalPanel().setVisible(true);
        } else if (e.getActionCommand().equals("setPopular")) {
            int numberSelected = getNumberSelected();

            if (numberSelected != 4) {
                JOptionPane.showMessageDialog(this.topLevelPanel,
                    "Please select EXACTLY 4 popular currencies.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                String[] popularCurrencies = getChosenCurrencies();
                JOptionPane.showMessageDialog(this.topLevelPanel,
                    "Successfully selected 4 popular currencies."
                );
                // GIVE THIS STRING[] TO BACKEND
            }
        }
    }

    private int getNumberSelected() {
        int selected = 0;
        for (JCheckBox box : this.boxes) {
            if (box.isSelected()) selected++;
        }
        return selected;
    }

    private String[] getChosenCurrencies() {
        // get the names of all selected currencies into an ArrayList
        ArrayList<String> popular = new ArrayList<String>();
        for (JCheckBox box : this.boxes) {
            if (box.isSelected()) {
                popular.add(box.getText());
            }
        }

        // convert ArrayList to String[]
        String[] popularCurrencies = new String[4];
        for (int i = 0; i < 4; i++) {
            popularCurrencies[i] = popular.get(i);
        }
        return popularCurrencies;
    }

    private void addSpace(JPanel jp, int y) {
        jp.add(Box.createRigidArea(new Dimension(0, y)));
    }

}
