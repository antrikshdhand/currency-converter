package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener {

    JPanel panel;
    CurrencyExchange cex;
    
    public WelcomeScreen(CurrencyExchange cex) {
        this.cex = cex;

        // set up panel which lives inside the JInternalFrame
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        cex.add(this.panel);
    }

    private void addComponentsToScreen() {
        // EMPTY VERT SPACE
        panel.add(Box.createVerticalGlue());

        // HEADER LABEL
        JLabel headerLabel = new JLabel("Welcome to the Currency Exchange.");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(headerLabel);

        // EMPTY VERT SPACE
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // CONVERT CURRENCIES BUTTON
        JButton convert = new JButton("Convert currencies");
        convert.setAlignmentX(Component.CENTER_ALIGNMENT);
        convert.addActionListener(this);
        convert.setActionCommand("convert"); // for mouse-click register
        panel.add(convert);

        // EMPTY VERT SPACE
        panel.add(Box.createVerticalGlue());

        // GIF
        ImageIcon moneyGif = new ImageIcon(this.getClass().getResource("donaldResized.gif").getPath());
        JLabel gif = new JLabel();
        gif.setIcon(moneyGif);
        gif.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(gif, BorderLayout.PAGE_END);

        // EMPTY VERT SPACE
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("convert")) {
            this.panel.setVisible(false); // first hide WelcomeScreen panel
            Converter converter = new Converter(this.cex); // then show Converter panel

            // application control now moves to Converter.java
        }   
    }

}
