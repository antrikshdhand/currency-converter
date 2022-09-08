package CurrencyConverter;

import javax.swing.*;
import javax.swing.UIManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App extends JFrame {

    private Desktop desktop;

    public App() {
        desktop = new Desktop();
    }

    public static void main(String[] args) {
        if (false) {
        Connection connection = null;
        try
        {
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:currency.db");
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          statement.executeUpdate("create table if not exists currency (currency_id integer primary key, currency_code char(3) unique, currency_name var_char(20))");
          statement.executeUpdate("create table if not exists exchange (currency_from integer references currency(currency_id), currency_to integer references currency(currency_id), currency_ex_code char(6), conv_val float, time_added datetime default (CURRENT_TIMESTAMP), primary key (currency_ex_code, time_added), constraint check_not_equal check (currency_from != currency_to))");
        //   statement.executeUpdate("insert into currency values(1, 'USD', 'American Dollar')");
        //   statement.executeUpdate("insert into currency values(2, 'AUD', 'Australian Dollar')");


          statement.executeUpdate("insert into exchange values(2, 1, 'USDAUD', 0.625, CURRENT_TIMESTAMP)");

          System.out.println();
          System.out.println("================================");
          System.out.println();

          ResultSet currency = statement.executeQuery("select * from currency");

          while(currency.next())
          {
            // read the result set
            System.out.println("id = " + currency.getInt("currency_id"));
            System.out.println("currency = " + currency.getString("currency_name"));
            System.out.println("code = " + currency.getString("currency_code"));
            System.out.println("---LINE---");
          }
          
          System.out.println();
          System.out.println("================================");
          System.out.println();

          ResultSet exchange = statement.executeQuery("select * from exchange");

          while(exchange.next())
          {
            // read the result set
            System.out.println("From ID = " + exchange.getInt("currency_from"));
            System.out.println("To ID = " + exchange.getInt("currency_to"));
            System.out.println("Conversion Code = " + exchange.getString("currency_ex_code"));
            System.out.println("Conversion = " + exchange.getFloat("conv_val"));
            System.out.println("Time = " + exchange.getTimestamp("time_added"));
            System.out.println("---LINE---");
          }

          System.out.println();
          System.out.println("================================");
          System.out.println();

        //   ResultSet query = statement.executeQuery("select * from exchange t1 inner join (select t2.currency_ex_code, max(time_added) FROM exchange WHERE t1.currency_ex_code = t2.currency_ex_code and t1.currency_ex_code = t2.currency_ex_code GROUP BY bucket_id) t2");

          ResultSet query = statement.executeQuery("select t1.* from exchange t1 inner join (select currency_ex_code, max(time_added) as 'time_added' from exchange group by currency_ex_code) t2 ON (t1.currency_ex_code = t2.currency_ex_code and t1.time_added = t2.time_added)");
          while(query.next())
          {
            // read the result set
            System.out.println("From ID = " + query.getInt("currency_from"));
            System.out.println("To ID = " + query.getInt("currency_to"));
            System.out.println("Conversion Code = " + query.getString("currency_ex_code"));
            System.out.println("Conversion = " + query.getFloat("conv_val"));
            System.out.println("Time = " + query.getTimestamp("time_added"));
            System.out.println("---LINE---");
          }

          System.out.println();
          System.out.println("================================");
          System.out.println();
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
      }


        CurrManager manager = new CurrManager();
        manager.openConn();
        manager.addCurrency("AUD", "Australian Dollar");
        manager.addCurrency("USD", "American Dollar");
        manager.addExchange("AUD", "USD", 1.3);
        manager.displayCurrencies();
        manager.displayExhanges();
        manager.displayLatestExchanges();
        manager.closeConn();
        // set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        
        App app = new App();

    }

}
