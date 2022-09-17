package CurrencyConverter;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Popular extends JFrame implements ActionListener {

    private CurrencyExchange cex;
    private JPanel popularPanel;

    private JLabel headerLabel;
    private JTable table;
    private JButton backButton;

    public Popular(CurrencyExchange cex) {
        this.cex = cex;

        popularPanel = new JPanel();
        popularPanel.setLayout(new BoxLayout(popularPanel, BoxLayout.PAGE_AXIS));
        popularPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();

        this.cex.add(this.popularPanel);
    }

    private void addComponentsToScreen() {
        // HEADER LABEL
        headerLabel = new JLabel("Popular Currencies");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        popularPanel.add(headerLabel, BorderLayout.NORTH);
        /////

        addSpace(popularPanel, 30);

        // TABLE
        table = new JTable(new MyTableModel());
        popularPanel.add(table.getTableHeader());
        popularPanel.add(table);
        /////

        addSpace(popularPanel, 30);

        // BACK BUTTON
        ImageIcon upIcon = new ImageIcon(this.getClass().getResource("upFolder.png").getPath());
        backButton = new JButton("Back", upIcon);
        backButton.addActionListener(this);
        backButton.setActionCommand("back"); // for mouse-click register
        popularPanel.add(backButton);
        /////
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.popularPanel.setVisible(false);
            this.cex.getWelcomeScreen().getWelcomePanel().setVisible(true);
        }
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = new String[] {
            "From/To",
            "Curr1Name",
            "Curr2Name",
            "Curr3Name",
            "Curr4Name"
        };

        private String[][] data = new String[][] {
            {"Curr1Name", "-", "Curr1/Curr2", "Curr1/Curr3", "Curr1/Curr4"},
            {"Curr2Name", "Curr2/Curr1", "-", "Curr2/Curr3", "Curr2/Curr4"},
            {"Curr3Name", "Curr3/Curr1", "Curr3/Curr2", "-", "Curr3/Curr4"},
            {"Curr4Name", "Curr4/Curr1", "Curr4/Curr2", "Curr4/Curr3", "-"}
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
