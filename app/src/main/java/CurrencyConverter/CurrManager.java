
package CurrencyConverter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;

//////////////////////// README ////////////////////////
/*
 * This is a basic instruction manual for this Curr-
 * Manager.
 * 
 * Everytime you would like to access any of these func
 * -tions, please openConn(), then after you are done
 * closeConn().
 * 
 * Each function has comments explaining all of the 
 * parameters and what the function does.
*/
/////////////////////////////////////////////////////// 

public class CurrManager {

    private Connection dbConn = null;
    private Statement openStatement = null;
    private String[][] BasicCurrencies = new String[][] {
        {"AUD", "Australian Dollar"},
        {"USD", "US Dollar"},
        {"NPR", "Nepalese Rupee"},
        {"INR", "Indian Rupee"},
        {"GBP", "Great Britan Pound"},
        {"SGD", "Singaporean Dollar"}
    };

    private String[][] BasicExchanges = new String[][] {
        {"AUD", "USD", "0.67118245"},
        {"AUD", "NPR", "85.633999"},
        {"AUD", "INR", "53.502302"},
        {"AUD", "GBP", "0.58787265"},
        {"AUD", "SGD", "0.94533497"},
        {"USD", "NPR", "127.5877"},
        {"USD", "INR", "79.704953"},
        {"USD", "GBP", "19.42119"},
        {"USD", "SGD", "1.4080513"},
        {"NPR", "INR", "0.62470717"},
        {"NPR", "GBP", "0.0068644026"},
        {"NPR", "SGD", "0.011035578"},
        {"INR", "GBP", "0.010988192"},
        {"INR", "SGD", "0.017665202"},
        {"GBP", "SGD", "1.6077844"},
    };


    /**
     * Constructor for this class.
     * Attempts to access the data base.
     */
    public CurrManager() {
        openConn();
        closeConn();

        // printSeperator();

    }


    /**
     * Opens connection with a database.
     * To be called before any other function by calling class.
     *
     * @return Returns 0 if successful and -1 if unsuccessful
     */
    public int openConn() {
        try {
            // create a database connection
            dbConn = DriverManager.getConnection("jdbc:sqlite:currency.db");
            openStatement = dbConn.createStatement();
            openStatement.setQueryTimeout(30);  // set timeout to 30 sec.

            openStatement.executeUpdate("create table if not exists currency (currency_code char(3) primary key, currency_name var_char(20))");
            openStatement.executeUpdate("create table if not exists exchange (currency_from char(3) references currency(currency_code), currency_to char(3) references currency(currency_code), currency_ex_code char(6), conv_val float, time_added datetime default (CURRENT_TIMESTAMP), primary key (currency_ex_code, time_added), constraint check_not_equal check (currency_from != currency_to))");
            openStatement.executeUpdate("create table if not exists popularFour (currency_from char(3) references currency(currency_code))");

            return 0;

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;

        }

    }


    /**
     * Closes the connection with the database.
     * To be called after you are done with database.
     *
     * @return Returns 0 if successful and -1 if unsuccessful
     */
    public int closeConn() {

        try {
            if(dbConn != null) dbConn.close();
            this.dbConn = null;
            this.openStatement = null;
            return 0;

        } catch(SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
            // this.dbConn = null;
            return -1;
        }
    }

    /**
     * Deletes all table data from the database (that we will be using)
     * To only be called if you want to delete all data.
     *
     * @return Returns 0 if successful and -1 if unsuccessful
     */
    public int dropAllTables() {

        // Add error handelling
        try {

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("drop table currency; drop table exchange; drop table popularFour");

            //error handelling
            openConn();

            return 0;

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }

    }


    /**
     * Deletes popular four data from the database
     * To only be called if you want to delete all data.
     *
     * @return Returns 0 if successful and -1 if unsuccessful
     */
    public int dropPopularFour() {

        // Add error handelling
        try {

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("drop table currency; drop table exchange;");

            //error handelling
            openConn();

            return 0;

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }

    }


    // protected int deleteDatabase() {
    //     File deleteFile = new File("../../../../../currency.db");

    //     if (deleteFile.delete()) {
    //         return 0;
    //       } else {
    //         return 1;
    //       }

    // }

    /**
     * Adds a currency to the currency table.
     * Need to call openConn before this function.
     *
     * @param exchCode The currency code for the currency as a string: e.g. "AUD".
     * @param currName The currency name of the code: e.g. "Australian Dollar".
     * @return Returns 0 if successful and -1 if unsuccessful.
     */
    public int addCurrency(String exchCode, String currName) {

        // Add error handelling
        try {

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(String.format("insert into currency values('%s', '%s')", exchCode, currName));

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
        return 0;

    }


    /**
     * Adds a currency to the currency table.
     * Need to call openConn before this function.
     *
     * @param exchCode The currency code for the currency as a string: e.g. "AUD".
     * @param currName The currency name of the code: e.g. "Australian Dollar".
     * @return Returns 0 if successful and -1 if unsuccessful.
     */
    public int addBasicSix() {

        // Add error handelling
        try {

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            for(String[] currency : BasicCurrencies) {
                statement.executeUpdate(String.format("insert into currency values('%s', '%s')", currency[0], currency[1]));
            }

            for(String[] exchange : BasicExchanges) {
                openStatement.executeUpdate(String.format("insert into exchange values('%s', '%s', '%s', %f, CURRENT_TIMESTAMP)", exchange[0], exchange[1], exchange[0] + exchange[1], exchange[2]));
            }


        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
        return 0;

    }



    /**
     * Given a currency code returns the currency name see {@link #addCurrency(String, String) addCurrency}.
     *
     * @param exchCode The currency code of a currency to be queried.
     * @return The currency name.
     */
    public String getCurrName(String exchCode) {

        try{
            ResultSet query = openStatement.executeQuery(String.format("select currency_name from currency where currency_code = '%s'", exchCode));

            if(query.next()) {
                return query.getString("currency_name");

            } else {
                return null;

            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return null;

    }


    /**
     * Retrives all currency codes and names.
     *
     * @return Returns all currencies in the form of a {@link java.util.HashMap HashMap}. The keys are the currency codes, and the values are the currency names.
     */
    public HashMap<String, String> getAllCurrencies() {

        HashMap<String, String> allCurrencies = new HashMap<String, String>();

        try{
            ResultSet query = openStatement.executeQuery(String.format("select * from currency"));

            while(query.next()) {
                String currCode = query.getString("currency_code");
                String currName = query.getString("currency_name");

                allCurrencies.put(currCode, currName);
            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return allCurrencies;

    }

    /**
     * Sets the popular currency codes.
     *
     * @return Returns 0 if successful and -1 if unsuccessful.
     */
    public int setPopularFour(String[] currencyList) {

        try{
            dropPopularFour();

            for(int i = 0; i < 4; i++) {
                String currency = currencyList[i];
                openStatement.executeUpdate(String.format("insert into popularFour values('%s')", currency));
            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;

        }

        return 0;

    }


    /**
     * Retrives popular currency codes.
     *
     * @return Returns all currencies in the form of a {@link java.util.HashMap HashMap}. The keys are the currency codes, and the values are the currency names.
     */
    public String[] getPopularFour() {

        String[] popCurrencies = new String[4];

        try{
            ResultSet query = openStatement.executeQuery(String.format("select * from popularFour"));


            int i = 0;
            while(query.next() && i < 0) {
                String currCode = query.getString("currency_code");

                popCurrencies[i] = currCode;
                i++;
            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return popCurrencies;

    }


    // public void displayCurrencies() {
    //     try{
    //         ResultSet query = openStatement.executeQuery("select * from currency");

    //         while(query.next())
    //         {
    //             // read the result set
    //             System.out.println("currency = " + query.getString("currency_name"));
    //             System.out.println("code = " + query.getString("currency_code"));
    //             System.out.println("---LINE---");
    //         }

    //     } catch(SQLException e) {
    //         // if the error message is "out of memory",
    //         // it probably means no database file is found
    //         System.err.println(e.getMessage());

    //     }

    //     printSeperator();
    // }


    /**
     * Adds an echange rate from one currency to another.
     * Also adds the reverse from currency 2 back to currency 1 into the exchange table.
     * So you do not have to add both AUD -> USD, and USD -> AUD manually. Only need to add one.
     *
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param convValue  Conversion rate from currency one to currency 2
     * @return Returns 0 if successful and -1 if unsuccessful
     */
    public int addExchange(String currOne, String currTwo, double convValue) {

        // We are doing this because otherwise for somereasonn Java time doesnt pass and the next time we add an exhange it has the same timestamp
        try {
            Thread.sleep(2000);

        } catch(InterruptedException e) {
            // this part is executed when an exception (in this example InterruptedException) occurs

        }


        // Add error handelling
        try {

            openStatement.setQueryTimeout(30);  // set timeout to 30 sec.
            openStatement.executeUpdate(String.format("insert into exchange values('%s', '%s', '%s', %f, CURRENT_TIMESTAMP)", currOne, currTwo, currOne+currTwo, convValue));
            openStatement.executeUpdate(String.format("insert into exchange values('%s', '%s', '%s', %f, CURRENT_TIMESTAMP)", currTwo, currOne, currTwo+currOne, 1/convValue));

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }

        return 0;

    }



    /**
     * Gets latest the conversion value for a particular exchange.
     *
     * @param currOne Currency from
     * @param currTwo Currency to
     * @return Returns the conversion rate from currency one to currency two.
     */
    public double getExchange(String currOne, String currTwo) { // Bug fixed - now it gets latest currency value

        try{
            ResultSet query = openStatement.executeQuery(String.format("select conv_val from exchange where currency_ex_code = '%s' and time_added in (select MAX(time_added) from exchange where currency_ex_code = '%s' group by currency_ex_code)", currOne + currTwo, currOne + currTwo));

            if(query.next()) {
                return query.getDouble("conv_val");

            } else {
                return -1;

            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;

        }

    }


    /**
     * Gets full history of the conversion values for a particular exchange between 2 dates.
     *
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param dayOne date one wants the exchange rate after, in format "YYYY-MM-DD" inclusive.
     * @param dayTwo date one wants the exchange rate before, in format "YYYY-MM-DD" inclusive.
     * @return ArrayList<ArrayList<String>> containing the full history of a particular exchange rate each list within contains {tiimeStamp , exchange rate} both as string.
     */
    public ArrayList<ArrayList<String>> getExchangeHist(String currOne, String currTwo, String dayOne, String dayTwo) { // Bug fixed - now it gets latest currency value //YYYY-MM-DD

        ArrayList<ArrayList<String>> historyList = new ArrayList<ArrayList<String>>();

        try{
            ResultSet query = openStatement.executeQuery(String.format("select time_added, conv_val from exchange where currency_ex_code = '%s' and time_added >= '%s 00.00.00' and time_added <= '%s 23.59.59' order by time_added DESC", currOne + currTwo, dayOne, dayTwo));

            while(query.next()) {

                ArrayList<String> tempList = new ArrayList<String>();
                String time_added = query.getTimestamp("time_added") + "";
                String conv_value = query.getDouble("conv_val") + "";

                tempList.add(time_added);
                tempList.add(conv_value);
                historyList.add(tempList);

            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return historyList;

    }


    /**
     *  Gets Average, Minimum , Maximum, and Varience of the conversion values for a particular exchange between 2 dates.
     *
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param dayOne date one wants the exchange rate after, in format "YYYY-MM-DD" inclusive.
     * @param dayTwo date one wants the exchange rate before, in format "YYYY-MM-DD" inclusive.
     * @return HashMap<String, Double> containing the full the Summaries of a particular exchange rate, in format {Summary Name e.g Average, double value}.
     */
    public HashMap<String, Double> getSummaries(String currOne, String currTwo, String dayOne, String dayTwo){

        HashMap<String, Double> map = new HashMap<String, Double>();

        String avgQuery = String.format("select avg(conv_val) as \"Average\" from exchange where currency_ex_code = '%s' and time_added >= '%s 00.00.00' and time_added <= '%s 23.59.59' order by time_added DESC", currOne + currTwo, dayOne, dayTwo);
        String minQuery = String.format("select min(conv_val) as \"Min\" from exchange where currency_ex_code = '%s' and time_added >= '%s 00.00.00' and time_added <= '%s 23.59.59' order by time_added DESC", currOne + currTwo, dayOne, dayTwo);
        String maxQuery = String.format("select max(conv_val) as \"Max\" from exchange where currency_ex_code = '%s' and time_added >= '%s 00.00.00' and time_added <= '%s 23.59.59' order by time_added DESC", currOne + currTwo, dayOne, dayTwo);

        try{

            ResultSet query1 = openStatement.executeQuery(avgQuery);
            map.put("Average", query1.getDouble("Average"));

            ResultSet query2 =  openStatement.executeQuery(minQuery);
            map.put("Min", query2.getDouble("Min"));

            ResultSet query3 = openStatement.executeQuery(maxQuery);
            map.put("Max", query3.getDouble("Max"));
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return map;
    }


    // public void displayExhanges() {
    //     try{
    //         ResultSet query = openStatement.executeQuery("select * from exchange");

    //         while(query.next())
    //         {
    //             System.out.println("From ID = " + query.getString("currency_from"));
    //             System.out.println("To ID = " + query.getString("currency_to"));
    //             System.out.println("Conversion Code = " + query.getString("currency_ex_code"));
    //             System.out.println("Conversion = " + query.getFloat("conv_val"));
    //             System.out.println("Time = " + query.getTimestamp("time_added"));
    //             System.out.println("---LINE---");
    //         }

    //     } catch(SQLException e) {
    //         // if the error message is "out of memory",
    //         // it probably means no database file is found
    //         System.err.println(e.getMessage());

    //     }

    //     printSeperator();
    // }

    // /**
    //  * Gets the latest exhange rates for all exchanges.
    //  *
    //  * @deprecated
    //  * @return Returns a {@link java.util.HashMap HashMap}, where the keys are the currency exchange codes ("USDAUD") and the values and the conversion rates.
    //  */
    // @Deprecated
    // public HashMap<String, Double> getLatestExchanges() {

    //     HashMap<String, Double> latestExchanges = new HashMap<String, Double>();

    //     try{
    //         ResultSet query = openStatement.executeQuery("select t1.* from exchange t1 inner join (select currency_ex_code, max(time_added) as 'time_added' from exchange group by currency_ex_code) t2 ON (t1.currency_ex_code = t2.currency_ex_code and t1.time_added = t2.time_added)");

    //         while(query.next())
    //         {
    //             String currExchCode = query.getString("currency_ex_code");
    //             Double conValue = (double) query.getFloat("conv_val");

    //             latestExchanges.put(currExchCode, conValue);
    //         }

    //     } catch(SQLException e) {
    //         // if the error message is "out of memory",
    //         // it probably means no database file is found
    //         System.err.println(e.getMessage());
    //         return null;
    //     }

    //     return latestExchanges;
    // }


    // public void displayLatestExchanges() {
    //     try{
    //         ResultSet query = openStatement.executeQuery("select t1.* from exchange t1 inner join (select currency_ex_code, max(time_added) as 'time_added' from exchange group by currency_ex_code) t2 ON (t1.currency_ex_code = t2.currency_ex_code and t1.time_added = t2.time_added)");

    //         while(query.next())
    //         {
    //             System.out.println("From = " + query.getString("currency_from"));
    //             System.out.println("To = " + query.getString("currency_to"));
    //             System.out.println("Conversion Code = " + query.getString("currency_ex_code"));
    //             System.out.println("Conversion = " + query.getFloat("conv_val"));
    //             System.out.println("Time = " + query.getTimestamp("time_added"));
    //             System.out.println("---LINE---");
    //         }

    //     } catch(SQLException e) {
    //         // if the error message is "out of memory",
    //         // it probably means no database file is found
    //         System.err.println(e.getMessage());

    //     }

    //     printSeperator();
    // }


    // public void printSeperator() {
    //     System.out.println();
    //     System.out.println("================================");
    //     System.out.println();
    // }


    
}