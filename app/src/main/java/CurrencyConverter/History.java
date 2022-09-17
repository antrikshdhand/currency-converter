package CurrencyConverter;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class History extends JFrame implements ActionListener {
    
    private CurrencyExchange cex;
    private JPanel historyPanel;
    private JPanel selectionPanel;

    private JLabel headerLabel;
    private JTable table;
    private JButton backButton;

    private String[] currencies = new String[] {
        "AUD",
        "EUR",
        "INR",
        "USD",
        "NZD",
        "JPY",
        "GBP"
    };

    public History(CurrencyExchange cex) {
        this.cex = cex;

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.PAGE_AXIS));
        historyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.historyPanel);
    }

    private void addComponentsToScreen() {
        // HEADER LABEL
        headerLabel = new JLabel("Exchange Rate History");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        historyPanel.add(headerLabel);
        /////

        addSpace(historyPanel, 20);
        
        // SELECT EXCHANGE RATE
        JLabel informationLabel1 = new JLabel("Select the exchange rate for which you wish to see the history of.");
        informationLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        historyPanel.add(informationLabel1);
        addSpace(historyPanel, 10);

        JLabel curr1Label = new JLabel("Currency 1:");
        JLabel curr2Label = new JLabel("Currency 2:");
        JComboBox<String> curr1Combo = new JComboBox<String>(currencies);
        JComboBox<String> curr2Combo = new JComboBox<String>(currencies);
        
        historyPanel.add(curr1Label);
        historyPanel.add(curr1Combo);
        historyPanel.add(curr2Label);
        historyPanel.add(curr2Combo);        

        addSpace(historyPanel, 20);

        JLabel informationLabel2 = new JLabel("Select the date range you are interested in.");
        informationLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        historyPanel.add(informationLabel2);

        addSpace(historyPanel, 20);
        
        // // DATE PANEL
        // datePanel = new JPanel();
        // datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.LINE_AXIS));
        // datePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // historyPanel.add(datePanel);
        
        
        
        // JLabel hi = new JLabel("HEllo WORLDDDDD");
        // JLabel hi2 = new JLabel("ARIGATOU GOZAIMASHITA");
        // JLabel hi3 = new JLabel("DENWA?");
        
        // datePanel.add(hi);
        // datePanel.add(hi2);
        // datePanel.add(hi3);


        // TABLE
        table = new JTable(new MyTableModel());
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        historyPanel.add(scrollPane);
        /////

        addSpace(historyPanel, 20);

        // BACK BUTTON
        ImageIcon upIcon = new ImageIcon(this.getClass().getResource("upFolder.png").getPath());
        backButton = new JButton("Back", upIcon);
        backButton.addActionListener(this);
        backButton.setActionCommand("back"); // for mouse-click register
        historyPanel.add(backButton);
        /////

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.historyPanel.setVisible(false);
            this.cex.getWelcomeScreen().getWelcomePanel().setVisible(true);
        }
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = new String[] {
            "Date/Time",
            "Curr1/Curr2",
        };

        private String[][] data = new String[][] {
            {"12/08/22 07:05:33", "0.98"},
            {"13/08/22 13:22:24", "0.99"},
            {"13/08/22 18:54:12", "1.02"},
            {"14/08/22 02:03:59", "1.00"},
            {"15/08/22 11:17:32", "0.98"},
        };

        public int getColumnCount() {
            return columnNames.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
           return false;
        }

    }

    private void addSpace(JPanel jp, int y) {
        jp.add(Box.createRigidArea(new Dimension(0, y)));
    }
}
