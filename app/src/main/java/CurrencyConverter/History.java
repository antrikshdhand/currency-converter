package CurrencyConverter;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class History extends JFrame implements ActionListener {
    
    private CurrencyExchange cex;
    private JPanel topLevelPanel;
    private JPanel topText;
    private JPanel combos;
    private JPanel middleText;
    private JPanel datePanel;
    private JScrollPane tablePanel;

    private JLabel headerLabel;
    private JTable table;
    private JButton backButton;
    private JLabel curr1Label;
    private JLabel curr2Label;
    private JComboBox<String> curr1Combo;
    private JComboBox<String> curr2Combo;
    private JButton setCurrenciesButton;
    private JLabel confirmCurrenciesLabel;
    private JLabel informationLabel1;
    private JLabel informationLabel2;
    private JLabel dateFromLabel;
    private JLabel dateToLabel;
    private JTextField dateFromField;
    private JTextField dateToField;
    private JButton setDatesButton;

    
    // BACKEND
    private BasicUser user;
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    private String[] currenciesArr;
    private String[] columnNames;
    private String[][] data;

    public History(CurrencyExchange cex, BasicUser user) {
        this.cex = cex;
        this.user = user;
        this.currenciesArr = user.getCurrencyCodes();
        this.columnNames = new String[] {"Date", "Curr1/Curr2"};
        this.data = new String[][] {
            {"1970-01-01", "1.00"}
        };

        this.topLevelPanel = new JPanel();
        this.topLevelPanel.setLayout(new GridLayout(0, 1));
        this.topLevelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addComponentsToScreen();
        
        this.cex.add(this.topLevelPanel);
    }

    private void addComponentsToScreen() {
        
        // HEADER LABEL AND STEP 1 TEXT
        topText = new JPanel();
        topText.setLayout(new BoxLayout(topText, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(topText);

        headerLabel = new JLabel("Exchange Rate History");
        headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        topText.add(headerLabel);

        topText.add(Box.createVerticalGlue());
        
        informationLabel1 = new JLabel("<html><font color='red'>1.</font> Select the exchange rate for which you wish to see the history of.</html>");
        informationLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        topText.add(informationLabel1);
        /////
        
        // COMBO BOXES AND APPLY BUTTON
        combos = new JPanel();
        combos.setLayout(new BoxLayout(combos, BoxLayout.LINE_AXIS));
        this.topLevelPanel.add(combos);

        curr1Label = new JLabel("<html><font color='green'>Currency 1:</font></html>");
        curr1Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        curr2Label = new JLabel("<html><font color='orange'>Currency 2:</font></html>");
        curr2Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
    
        curr1Combo = new JComboBox<String>(currenciesArr);
        curr1Combo.setPreferredSize(new Dimension(100, 40));
        curr1Combo.setMaximumSize(new Dimension(100, 40));
        curr2Combo = new JComboBox<String>(currenciesArr);     
        curr2Combo.setPreferredSize(new Dimension(100, 40));
        curr2Combo.setMaximumSize(new Dimension(100, 40));

        setCurrenciesButton = new JButton("Apply");
        setCurrenciesButton.setPreferredSize(new Dimension(100, 30));
        setCurrenciesButton.setMaximumSize(new Dimension(100, 30));
        setCurrenciesButton.addActionListener(this);
        setCurrenciesButton.setActionCommand("setCurrencies");

        combos.add(curr1Label);
        combos.add(curr1Combo);
        combos.add(Box.createRigidArea(new Dimension(100, 0)));
        combos.add(curr2Label);
        combos.add(curr2Combo);
        combos.add(Box.createRigidArea(new Dimension(150, 0)));
        combos.add(setCurrenciesButton);
        combos.add(Box.createRigidArea(new Dimension(300, 0)));
        /////
        
        // CONFIRMATION AND STEP 2 TEXT
        middleText = new JPanel();
        middleText.setLayout(new BoxLayout(middleText, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(middleText);
        
        confirmCurrenciesLabel = new JLabel();
        confirmCurrenciesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        middleText.add(confirmCurrenciesLabel);

        middleText.add(Box.createVerticalGlue());
        
        informationLabel2 = new JLabel("<html><font color='red'>2.</font> Select the date range you are interested in. <strong>Input in yyyy-mm-dd</strong>.</html>");
        informationLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        middleText.add(informationLabel2);
        /////
    
        // DATE FROM, DATE TO, AND APPLY BUTTON
        datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.LINE_AXIS));
        this.topLevelPanel.add(datePanel);

        dateFromLabel = new JLabel("<html><font color='blue'>Date from:</font></html>");
        dateFromLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        dateToLabel = new JLabel("<html><font color='blue'>Date to:</font></html>");
        dateToLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        dateFromField = new JTextField(10);
        dateFromField.setPreferredSize(new Dimension(100, 30));
        dateFromField.setMaximumSize(new Dimension(100, 30));
        dateToField = new JTextField(10);
        dateToField.setPreferredSize(new Dimension(100, 30));
        dateToField.setMaximumSize(new Dimension(100, 30));

        setDatesButton = new JButton("Apply");
        setDatesButton.setPreferredSize(new Dimension(100, 30));
        setDatesButton.setMaximumSize(new Dimension(100, 30));
        setDatesButton.addActionListener(this);
        setDatesButton.setActionCommand("setDates");

        datePanel.add(dateFromLabel);
        datePanel.add(dateFromField);
        datePanel.add(Box.createRigidArea(new Dimension(100, 0)));
        datePanel.add(dateToLabel);
        datePanel.add(dateToField);
        datePanel.add(Box.createRigidArea(new Dimension(150, 0)));
        datePanel.add(setDatesButton);
        datePanel.add(Box.createRigidArea(new Dimension(300, 0)));
        /////

        // TABLE
        MyTableModel mtm = new MyTableModel(this.columnNames, this.data);
        table = new JTable(mtm);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        tablePanel = new JScrollPane(table);
        this.topLevelPanel.add(tablePanel);
        /////
        
        // BACK BUTTON
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        this.topLevelPanel.add(bottomPanel);

        bottomPanel.add(Box.createVerticalGlue());
        
        ImageIcon upIcon = new ImageIcon(this.getClass().getResource("upFolder.png").getPath());
        backButton = new JButton("Back", upIcon);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setMaximumSize(new Dimension(100, 40));
        backButton.addActionListener(this);
        backButton.setActionCommand("back"); // for mouse-click register
        bottomPanel.add(backButton);
        /////

        bottomPanel.add(Box.createVerticalGlue());

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.topLevelPanel.setVisible(false);
            this.cex.getWelcomeScreen().getWelcomePanel().setVisible(true);
        } else if (e.getActionCommand().equals("setCurrencies")) {
            printConfirmStatement();
        } else if (e.getActionCommand().equals("setDates")) {
            String currOne = (String) this.curr1Combo.getSelectedItem();
            String currTwo = (String) this.curr2Combo.getSelectedItem();
    
            String dateFrom = dateFromField.getText();
            String dateTo = dateToField.getText();
            
            String[] dates = new String[] {dateFrom, dateTo};
            boolean valid = areDatesValid(dates);
            if (!valid) {
                JOptionPane.showMessageDialog(this.topLevelPanel,
                    "Date format not valid. Please try again.",
                    "Date formatting error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                this.data = this.user.getHistory(currOne, currTwo, dateFrom, dateTo);
                this.table = new JTable(new MyTableModel(this.columnNames, this.data));
            }
        }
    }

    private boolean areDatesValid(String[] dates) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setLenient(false);
        try {
            for (String s : dates) {
                df.parse(s);
            }
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private void printConfirmStatement() {
        StringBuilder s1 = new StringBuilder();
        s1.append("<html>You have selected the exchange <font color='green'>");
        s1.append(this.curr1Combo.getSelectedItem());
        s1.append("</font>/<font color='orange'>");
        s1.append(this.curr2Combo.getSelectedItem());
        s1.append("</font>.</html>");
        this.confirmCurrenciesLabel.setText(s1.toString());
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
