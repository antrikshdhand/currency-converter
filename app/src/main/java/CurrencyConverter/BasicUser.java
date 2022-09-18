package CurrencyConverter;
import java.util.*;

public class BasicUser {


    /**
     * Attribute database : CurrManager
     */
    protected CurrManager database;
    // protected Calculator calc;
    // protected boolean displayPop4;
    // protected ArrayList<Currency> uniqueCurrencies;


    /**
    * Constuctor of class
    * @Param Database : take in Currmanager which queries a databse.
     */
    public BasicUser(CurrManager database) {
        this.database = database;
    }

    /**
    * Fucntion that return the CurrManager
     */
    public CurrManager getDatabase() {
        return database;
    }

// public boolean checkDisplay(){
    //reutrn this.displaypop4;}

    // public void changeDisplaySettings(){
    // if ( this.displayPop4) this.displayPop4 = false;
    // else this.displayPop4 = true;

    // User wants to convert from one currency to another.

    /**
    * Funciton that convert an amount form one currency to another.
    * @Param from : currency from which one wishes to convert form (String)
    * @Param to : currency to which one wishes to convert to (String)
    * @Param amount : the amount one wishes to convert (double)
    * @return, return the converted amount as a ( double)
     */
    public double convert( String from , String to, double amount){
        // here goes the logic of querying the from one currency, to another.

        if ( from.equals(to)) return amount;


        double result = 0;
        // result = this.clac.calculateConversion( from, to , amount);

        return result;
    }

    public void displayTop4(){
        // this is where you get the currencies of the 4 exchange
    }

    /**
    * function that returns the History of a chosen currency
    * @Param curr : the chosen currency (currency)
    * return an array of string, where the string is in the format currency from, currency to, exchange rate, Date.
     */
     public String [] getHistory(Currency curr){
         return new String[0];
     }




}
