
package CurrencyConverter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.meta.Exhaustive;

public class CurrManager {

    private Connection dbConn = null; 
    private Statement openStatement = null;

    public CurrManager() {
        openConn();
        closeConn();

        printSeperator();

    }


    public int openConn() {
        try {
            // create a database connection
            dbConn = DriverManager.getConnection("jdbc:sqlite:currency.db");
            openStatement = dbConn.createStatement();
            openStatement.setQueryTimeout(30);  // set timeout to 30 sec.

            openStatement.executeUpdate("create table if not exists currency (currency_code char(3) primary key, currency_name var_char(20))");
            openStatement.executeUpdate("create table if not exists exchange (currency_from char(3) references currency(currency_code), currency_to char(3) references currency(currency_code), currency_ex_code char(6), conv_val float, time_added datetime default (CURRENT_TIMESTAMP), primary key (currency_ex_code, time_added), constraint check_not_equal check (currency_from != currency_to))");
            return 0;
            
        } catch(SQLException e) {          
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return 1;
            
        }

    }

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
            return 1;
        }
    }


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
        }
        return 1;

    }

    public void displayCurrencies() {
        try{ 
            ResultSet query = openStatement.executeQuery("select * from currency");

            while(query.next())
            {
                // read the result set
                System.out.println("currency = " + query.getString("currency_name"));
                System.out.println("code = " + query.getString("currency_code"));
                System.out.println("---LINE---");
            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        printSeperator();
    }


    public int addExchange(String currOne, String currTwo, double convValue) {

        // Add error handelling
        try {
            
            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(String.format("insert into exchange values('%s', '%s', '%s', %f, CURRENT_TIMESTAMP)", currOne, currTwo, currOne+currTwo, convValue));
            statement.executeUpdate(String.format("insert into exchange values('%s', '%s', '%s', %f, CURRENT_TIMESTAMP)", currTwo, currOne, currTwo+currOne, 1/convValue));

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return 1;

    }

    public void displayExhanges() {
        try{ 
            ResultSet query = openStatement.executeQuery("select * from exchange");

            while(query.next())
            {
                System.out.println("From ID = " + query.getString("currency_from"));
                System.out.println("To ID = " + query.getString("currency_to"));
                System.out.println("Conversion Code = " + query.getString("currency_ex_code"));
                System.out.println("Conversion = " + query.getFloat("conv_val"));
                System.out.println("Time = " + query.getTimestamp("time_added"));
                System.out.println("---LINE---");
            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        printSeperator();
    }

    public void displayLatestExchanges() {
        try{ 
            ResultSet query = openStatement.executeQuery("select t1.* from exchange t1 inner join (select currency_ex_code, max(time_added) as 'time_added' from exchange group by currency_ex_code) t2 ON (t1.currency_ex_code = t2.currency_ex_code and t1.time_added = t2.time_added)");

            while(query.next())
            {
                System.out.println("From = " + query.getString("currency_from"));
                System.out.println("To = " + query.getString("currency_to"));
                System.out.println("Conversion Code = " + query.getString("currency_ex_code"));
                System.out.println("Conversion = " + query.getFloat("conv_val"));
                System.out.println("Time = " + query.getTimestamp("time_added"));
                System.out.println("---LINE---");
            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        printSeperator();
    }


    public void printSeperator() {
        System.out.println();
        System.out.println("================================");
        System.out.println();
    }


    
}