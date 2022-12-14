package CurrencyConverter;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayPopular extends JFrame implements ActionListener {

    private CurrencyExchange cex;
    private JPanel topLevelPanel;
    private JPanel topText;
    private JScrollPane tablePanel;
    private JPanel bottomPanel;

    private JLabel headerLabel;
    private JTable table;
    private JButton backButton;
    
    // BACKEND
    private BasicUser user;

    public DisplayPopular(CurrencyExchange cex, BasicUser user) {
        this.cex = cex;
        this.user = user;

        this.topLevelPanel = new JPanel();
        this.topLevelPanel.setLayout(new GridLayout(0, 1));
        this.topLevelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.topLevelPanel);
    }

    private void addComponentsToScreen() {

        // TITLE
        topText = new JPanel();
        topText.setLayout(new BoxLayout(topText, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(topText);

        headerLabel = new JLabel("Popular Currencies");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        topText.add(headerLabel);
        /////

        // TABLE
        String[] columns = this.user.getPopular4Header();
        String[][] data = this.user.getPopular4Data();

        boolean isFilled = false;
        if (data[0][0] != null) isFilled = true;
        
        if (!isFilled) {
            JLabel errorLabel = new JLabel("No popular currencies have been set by the admin.");
            this.topLevelPanel.add(errorLabel);
        } else {
            MyTableModel mtm = new MyTableModel(columns, data);
            table = new JTable(mtm);
            table.setFillsViewportHeight(true);
            table.getTableHeader().setReorderingAllowed(false);
            tablePanel = new JScrollPane(table);
            this.topLevelPanel.add(tablePanel);
            /////
        }        

        addSpace(this.topLevelPanel, 20);

        // BACK BUTTON
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(bottomPanel);

        ImageIcon upIcon = new ImageIcon("./src/main/resources/CurrencyConverter/upFolder.png");
        backButton = new JButton("Back", upIcon);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setMaximumSize(new Dimension(100, 40));
        backButton.addActionListener(this);
        backButton.setActionCommand("back"); // for mouse-click register
        bottomPanel.add(backButton);
        /////
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.topLevelPanel.setVisible(false);
            this.cex.getWelcomeScreen().getWelcomePanel().setVisible(true);
        }
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames;
        private String[][] data;

        public MyTableModel(String[] columnNames, String[][] data) {
            super();
            this.columnNames = columnNames;
            this.data = data;
        }

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
