package CurrencyConverter;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

class Desktop extends JDesktopPane implements ActionListener {
    
    private JDesktopPane desktop;
    private JFrame desktopFrame;
    private BufferedImage img;
    private boolean cexOpened = false;
    

    public Desktop() {
        try {
            img = ImageIO.read(new File("./src/main/resources/CurrencyConverter/windowsXP.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(img.getWidth(), img.getHeight());
            }
        };
        
        // Make dragging faster by setting drag mode to Outline
        desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);

        desktopFrame = new JFrame("");
        desktopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktopFrame.setContentPane(desktop);
        desktopFrame.setLayout(new BorderLayout());
        desktopFrame.add(createDesktopMenuBar(), BorderLayout.SOUTH);
        desktopFrame.pack();
        desktopFrame.setResizable(false);
        desktopFrame.setVisible(true);
    }

    private JMenuBar createDesktopMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(36, 93, 218));

        // Applications menu
        JMenu applicationMenu = new JMenu("");
        ImageIcon windowsXPIcon = new ImageIcon("./src/main/resources/CurrencyConverter/startMenu.png");
        applicationMenu.setIcon(windowsXPIcon);
        menuBar.add(applicationMenu);

        // Currency Exchange
        ImageIcon cexIcon = new ImageIcon("./src/main/resources/CurrencyConverter/dollarSignTransparent.png");
        JMenuItem cexItem = new JMenuItem("Currency Exchange", cexIcon);   
        cexItem.addActionListener(this);
        cexItem.setActionCommand("Open CEX"); // for mouse-click register
        applicationMenu.add(cexItem);

        // Quit
        ImageIcon quitIcon = new ImageIcon("./src/main/resources/CurrencyConverter/powerOff.png");
        JMenuItem quit = new JMenuItem("Turn Off Computer", quitIcon);

        // Quit Button
        quit.addActionListener(this);
        quit.setActionCommand("quit"); // for mouse-click register
        applicationMenu.add(quit);

        // Horizontal space
        menuBar.add(Box.createHorizontalGlue());

        // Time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
        JLabel timeLabel = new JLabel(LocalTime.now().format(dtf));
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        timeLabel.setForeground(Color.WHITE);
        menuBar.add(timeLabel);

        // Horizontal space
        menuBar.add(Box.createRigidArea(new Dimension(20, 0)));

        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Open CEX")) {
            // // only open a maximum of one application window 
            // if (!cexOpened){
            //     openCEXApplication();
            //     cexOpened = true;
            // }
            openCEXApplication();
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        }
    }

    private void openCEXApplication() {
        CurrencyExchange cex = new CurrencyExchange(desktop);
        
        // application control now moves to CurrencyExchange.java

    }

}
