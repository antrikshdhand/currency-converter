package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener {

    private JPanel welcomeScreenPanel;
    private CurrencyExchange cex;
    
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
        welcomeScreenPanel.add(Box.createVerticalGlue());

        // HEADER LABEL
        JLabel headerLabel = new JLabel("Welcome to the Currency Exchange.");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeScreenPanel.add(headerLabel);

        // EMPTY VERT SPACE
        welcomeScreenPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // CONVERT CURRENCIES BUTTON
        JButton convert = new JButton("Convert currencies");
        convert.setAlignmentX(Component.CENTER_ALIGNMENT);
        convert.addActionListener(this);
        convert.setActionCommand("convertApp"); // for mouse-click register
        welcomeScreenPanel.add(convert);

        // EMPTY VERT SPACE
        welcomeScreenPanel.add(Box.createVerticalGlue());

        // GIF
        ImageIcon moneyGif = new ImageIcon(this.getClass().getResource("donaldResized.gif").getPath());
        JLabel gif = new JLabel();
        gif.setIcon(moneyGif);
        gif.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeScreenPanel.add(gif, BorderLayout.PAGE_END);

        // EMPTY VERT SPACE
        welcomeScreenPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("convertApp")) {
            this.welcomeScreenPanel.setVisible(false); // first hide WelcomeScreen panel
            Converter converter = new Converter(this.cex); // then show Converter panel

            // application control now moves to Converter.java
        }   
    }

    public JPanel getWelcomePanel() {
        return this.welcomeScreenPanel;
    }

}
