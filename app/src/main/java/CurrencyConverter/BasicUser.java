package CurrencyConverter;
import java.util.*;

public class BasicUser {


    /**
     * Attribute database : CurrManager
     */
    protected CurrManager database;
    // protected ArrayList<Currency> uniqueCurrencies;


    /**
    * Constuctor of class
    * @Param Database : take in Currmanager which queries a database.
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
        database.openConn();
        double rate = database.getExchange(from,to);
        database.closeConn();
        if ((int)rate == -1) return 0;

        result = rate * amount;


        return result;
    }



    public String []  getAllCurrencies(){

        HashMap<String, String> allCurrencies = new HashMap<String, String>();

        String [] result = new String [allCurrencies.size()];


        int count = 0;
        for (String s : allCurrencies.keySet()){
            result [count] = s;
            count += 1;
        }
        return result;
    }
}
    public void displayTop4(){
        // this is where you get the currencies of the 4 exchange
    }

    /**
    * function that returns the History between 2 chosen currencies between 2 dates.
    * @Param currOne: the currency from
    * @Param currTwo: the currency to
    * @Param startDate : the date form in format "YYYY-MM-DD"
    * @Param endDate : the date to in format "YYYY-MM-DD"
    * return an array of string, where the string is in the format currency from, currency to, exchange rate, Date.
     */
     public String [][] getHistory(String currOne, String curTwo ,String startDate, String endDate){
         ArrayList<ArrayList<String>> map =  this.database.getExchangeHist(currOne, curTwo, startDate, endDate);
         int length = map.size();

         String [][] result = new String[length][2];

         int count = 0;
         for (ArrayList<String> val : map){
             String [] temp = {val.get(0), val.get(1)};
             result[0] = temp;
             count += 0;
         }

         return result;
     }

//     public double getMedian(String currOne, String curTwo ,String startDate, String endDate) {
//         ArrayList<ArrayList<String>> map = this.database.getExchangeHist(currOne, curTwo, startDate, endDate);
//         int length = map.size();
//         int fl = length % 2;
//         int midVal = 0;
//
//         if (fl = 0) {
//
//
//         }
//     }


     public String [][] getPopularCurrencies(){
         return null;
     }



}
