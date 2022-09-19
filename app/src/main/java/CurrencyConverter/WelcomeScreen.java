package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener, ItemListener {

    private JPanel welcomeScreenPanel;
    private CurrencyExchange cex;
    
    private JLabel headerLabel;
    private JCheckBox adminRights;
    private JButton convertButton;
    private JButton popularButton;
    private JButton historyButton;
    private JButton adminPortalButton;

    private Converter converter;
    private DisplayPopular displayPopular;
    private History history;
    private AdminPortal adminPortal;

    
    public WelcomeScreen(CurrencyExchange cex) {
        this.cex = cex;

        // set up panel which lives inside the JInternalFrame
        welcomeScreenPanel = new JPanel();
        welcomeScreenPanel.setLayout(new BoxLayout(welcomeScreenPanel, BoxLayout.PAGE_AXIS));
        welcomeScreenPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.welcomeScreenPanel);
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
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeScreenPanel.add(headerLabel);

        addSpace(welcomeScreenPanel, 20);

        // CONVERT CURRENCIES BUTTON
        convertButton = new JButton("Convert currencies");
        convertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        convertButton.addActionListener(this);
        convertButton.setActionCommand("convertApp"); // for mouse-click register
        welcomeScreenPanel.add(convertButton);

        addSpace(welcomeScreenPanel, 10);

        // VIEW POPULAR CURRENCIES BUTTON
        popularButton = new JButton("Popular currencies");
        popularButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        popularButton.addActionListener(this);
        popularButton.setActionCommand("popular"); // for mouse-click register
        welcomeScreenPanel.add(popularButton);

        addSpace(welcomeScreenPanel, 10);

        // VIEW HISTORICAL RATES BUTTON
        historyButton = new JButton("Historical rates");
        historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyButton.addActionListener(this);
        historyButton.setActionCommand("history"); // for mouse-click register
        welcomeScreenPanel.add(historyButton);

        addSpace(welcomeScreenPanel, 10);

        // ADMIN PORTAL BUTTON
        adminPortalButton = new JButton("Admin portal");
        adminPortalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminPortalButton.addActionListener(this);
        adminPortalButton.setActionCommand("admin"); // for mouse-click register
        welcomeScreenPanel.add(adminPortalButton);
        adminPortalButton.setVisible(false);

    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("convertApp")) {
            this.welcomeScreenPanel.setVisible(false); // first hide WelcomeScreen panel
            converter = new Converter(this.cex); // then show Converter panel
            
            // application control now moves to Converter.java
        } else if (e.getActionCommand().equals("popular")) {
            this.welcomeScreenPanel.setVisible(false);
            displayPopular = new DisplayPopular(this.cex);
            
            // application control now moves to Popular.java
        } else if (e.getActionCommand().equals("history")) {
            this.welcomeScreenPanel.setVisible(false);
            history = new History(this.cex);
            
            // application control now moves to History.java
        } else if (e.getActionCommand().equals("admin")) {
            this.welcomeScreenPanel.setVisible(false);
            adminPortal = new AdminPortal(this.cex);
            
            // application control now moves to AdminPortal.java
        } 
    }
    
    public void itemStateChanged(ItemEvent e) {
        
        JCheckBox jcb = (JCheckBox) e.getItemSelectable();
        
        if (jcb.isSelected()) {
            this.adminPortalButton.setVisible(true);
        } else {
            this.adminPortalButton.setVisible(false);
        }
    }
    
    public JPanel getWelcomePanel() {
        return this.welcomeScreenPanel;
    }

    public AdminPortal getAdminPortal() {
        return this.adminPortal;
    }
    
    private void addSpace(JPanel jp, int y) {
        jp.add(Box.createRigidArea(new Dimension(0, y)));
    }
}
