package CurrencyConverter;
import java.util.Scanner;
import java.util.*;
public class Admin extends BasicUser {


    public Admin(CurrManager database) {
        super(database);
        // this.displayPop4 = true;
    }

    /**
     * function adds currency in the database.
     * @param exhcode : the short form code of the currency.
     * @para name : full name of the currency
     * @return  nothing
     */
    public void addCurrency(String exhcode, String name ){
        // this function goes into the database and adds a new currency and its currency rate.
        // there will be no drop down menu for the admin,when the function is called the it will require the full name of the currencies and the abv.

        database.openConn();
        int result = this.database.addCurrency(exhcode, name);
        database.closeConn();


    }


    /**
     * funciton that adds an exchange rate to the database.
     * @param from : short code for the currency form
     * @param to : short code for the currency to
     * @param rate : rate of the exchange from one currency to other.
     */
    public void addExchange( String from, String to, double rate){

        database.openConn();
        database.addExchange(from, to , rate);
        database.closeConn();
    }


    /**
     * Function for setting the Popular 4 currencies.
     * @param currOne String
     * @param currTwo String
     * @param currThree String
     * @param currFour String
     */
    public void addPopularCurrency(String currOne, String currTwo, String currThree , String currFour){

        String[] arr = {currOne, currTwo, currThree ,currFour};
        this.database.setPopularFour(arr);


    }

    // /**
    //  * Function for setting the Popular 4 currencies.
    //  * @param arr String []
    //  */
    // public void addPopularCurrency(String [] arr){
    //     this.database.setPopularCurrencies(arr);
    // }



    /**
     * Function resets the database and adds all the original currencies
     */
    public void reset(){
        this.database.openConn();
        this.database.dropAllTables();
        this.database.addBasicSix();
        this.database.openConn();
    }
}
