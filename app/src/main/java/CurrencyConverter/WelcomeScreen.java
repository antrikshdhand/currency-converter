package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener, ItemListener {

    private JPanel welcomeScreenPanel;
    private CurrencyExchange cex;
    private JLabel headerLabel;
    private JCheckBox adminRights;
    private JButton convert;
    private JButton popular;
    private JButton history;
    private JButton adminPortal;

    
    public WelcomeScreen(CurrencyExchange cex) {
        this.cex = cex;

        // set up panel which lives inside the JInternalFrame
        welcomeScreenPanel = new JPanel();
        welcomeScreenPanel.setLayout(new BoxLayout(welcomeScreenPanel, BoxLayout.PAGE_AXIS));
        welcomeScreenPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        cex.add(this.welcomeScreenPanel);
    }

    private void addComponentsToScreen() {
        // EMPTY VERT SPACE
        addSpace(welcomeScreenPanel, 50);

        // PERMISSIONS SELECTOR
        adminRights = new JCheckBox("Admin");
        adminRights.addItemListener(this);
        adminRights.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeScreenPanel.add(adminRights);

        addSpace(welcomeScreenPanel, 50);

        // HEADER LABEL
        headerLabel = new JLabel("Welcome to the Currency Exchange.");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeScreenPanel.add(headerLabel);

        addSpace(welcomeScreenPanel, 10);

        // CONVERT CURRENCIES BUTTON
        convert = new JButton("Convert currencies");
        convert.setAlignmentX(Component.CENTER_ALIGNMENT);
        convert.addActionListener(this);
        convert.setActionCommand("convertApp"); // for mouse-click register
        welcomeScreenPanel.add(convert);

        addSpace(welcomeScreenPanel, 10);

        // VIEW POPULAR CURRENCIES BUTTON
        popular = new JButton("Popular currencies");
        popular.setAlignmentX(Component.CENTER_ALIGNMENT);
        popular.addActionListener(this);
        popular.setActionCommand("popular"); // for mouse-click register
        welcomeScreenPanel.add(popular);

        addSpace(welcomeScreenPanel, 10);

        // VIEW HISTORICAL RATES BUTTON
        history = new JButton("Historical rates");
        history.setAlignmentX(Component.CENTER_ALIGNMENT);
        history.addActionListener(this);
        history.setActionCommand("history"); // for mouse-click register
        welcomeScreenPanel.add(history);

        addSpace(welcomeScreenPanel, 10);

        // ADMIN PORTAL BUTTON
        adminPortal = new JButton("Admin portal");
        adminPortal.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminPortal.addActionListener(this);
        adminPortal.setActionCommand("admin"); // for mouse-click register
        welcomeScreenPanel.add(adminPortal);
        adminPortal.setVisible(false);

    }

    private void addSpace(JPanel jp, int y) {
        jp.add(Box.createRigidArea(new Dimension(0, y)));
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("convertApp")) {
            this.welcomeScreenPanel.setVisible(false); // first hide WelcomeScreen panel
            Converter converter = new Converter(this.cex); // then show Converter panel
            
            // application control now moves to Converter.java
        } else if (e.getActionCommand().equals("popular")) {
            this.welcomeScreenPanel.setVisible(false); // first hide WelcomeScreen panel
            Popular popular = new Popular(this.cex); // then show Converter panel
            
            // application control now moves to Popular.java
        }
            
        // } else if (e.getActionCommand().equals("permissions")) {
        //     if (this.permissionsSelector.getSelectedItem().equals("Admin")) {
        //         System.out.println("HEKKIIIII");
        //         welcomeScreenPanel.add(this.adminPortal);
        //     } else {
        //         System.out.println("NOOOO");
        //         welcomeScreenPanel.remove(this.adminPortal);
        //     }
        //     // // EMPTY VERT SPACE
        //     // addSpace(welcomeScreenPanel, 50);
        // }
    }

    public void itemStateChanged(ItemEvent e) {
        
        JCheckBox jcb = (JCheckBox) e.getItemSelectable();

        if (jcb.isSelected()) {
            adminPortal.setVisible(true);
        } else {
            adminPortal.setVisible(false);
        }
    }

    public JPanel getWelcomePanel() {
        return this.welcomeScreenPanel;
    }

}
