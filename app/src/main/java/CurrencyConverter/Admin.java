package CurrencyConverter;

import java.util.*;

public class Admin extends BasicUser {


    public Admin(CurrManager database) {
        super(database);
    }


    /**
     * Adds currency into the database.
     * Includes the currency code and the currency name.
     * @param exhcode the short form code of the currency
     * @param name full name of the currency
     */
    public void addCurrency(String exhcode, String name) {
        database.openConn();
        int result = this.database.addCurrency(exhcode, name);
        database.closeConn();
    }


    /**
     * Function that adds an exchange rate to the database.
     * Currency must be in the database.
     * @param from short code for the currency form
     * @param to short code for the currency to
     * @param rate rate of the exchange from one currency to other.
     */
    public void addExchange(String from, String to, double rate) {
        database.openConn();
        database.addExchange(from, to , rate);
        database.closeConn();
    }


    /**
     * Sets the popular 4 currencies.
     * @param arr an array containing 4 exchange codes
     */
    public void addPopularCurrency(String[] arr) {
        database.openConn();
        this.database.setPopularFour(arr);
        database.closeConn();
    }


    /**
     * Function resets the database and adds all the original currencies
     */
    public void reset() {
        this.database.openConn();
        this.database.dropAllTables();
        this.database.addBasicSix();
        this.database.openConn();
    }


}
