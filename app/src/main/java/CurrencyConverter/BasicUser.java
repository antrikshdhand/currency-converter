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



    public String []  getCurrencyCodes(){

        HashMap<String, String> allCurrencies = this.database.getAllCurrencies();

        String [] result = new String [allCurrencies.size()];


        int count = 0;
        for (String s : allCurrencies.keySet()){
            result [count] = s;
            count += 1;
        }
        return result;
    }

    /**
    * function that returns the History between 2 chosen currencies between 2 date.
    * @Param currOne: the currency from
    * @Param currTwo: the currency to
    * @Param startDate : the date form in format "YYYY-MM-DD"
    * @Param endDate : the date to in format "YYYY-MM-DD"
    * @return  A 2 dimensional array where the index is the timestamp and the index 1 is the exchange rate at the time.
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

    /**
     * Function calculates the Median exchange rate between 2 specified currency within a given time frame.
     * @param currOne Currency from
     * @param curTwo Currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date onw wants rates before, in format "YYYY-MM-DD" inclusive.
     * @return double which is the median exchange rate between these values.
     */
     public double getMedian(String currOne, String curTwo ,String startDate, String endDate) {
         ArrayList<ArrayList<String>> map = this.database.getExchangeHist(currOne, curTwo, startDate, endDate);
         int length = map.size();
         int fl = length % 2;
         double result = 0;
         if (fl == 0 ){
             int val1 = (int) Math.floor(length/2) ;
             int val2 = (int) Math.ceil(length/2);
             result = (Double.parseDouble(map.get(val1).get(1)) + Double.parseDouble(map.get(val2).get(1)));
         }
         else{
             int val1 = (int) length/2;
             result = Double.parseDouble(map.get(val1).get(1));
         }
         return result;

     }

    /**
     * Function calculates the average exchange rate between 2 specified currency within a given time frame.
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date onw wants rates before, in format "YYYY-MM-DD" inclusive.
     * @return double which is the average exchange rate between these values.
     */
     public double getAverage(String currOne, String currTwo ,String startDate, String endDate){
         HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
         return map.get("Average");
     }

    /**
     * Function calculates the Minimum exchange rate between 2 specified currency within a given time frame.
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date onw wants rates before, in format "YYYY-MM-DD" inclusive.
     * @return double which is the Minimum exchange rate between these values.
     */
    public double getMinimum(String currOne, String currTwo ,String startDate, String endDate){
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return map.get("Min");
    }

    /**
     * Function calculates the Maximum exchange rate between 2 specified currency within a given time frame.
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date onw wants rates before, in format "YYYY-MM-DD" inclusive.
     * @return double which is the Maximum exchange rate between these values.
     */

    public double getMaximum(String currOne, String currTwo ,String startDate, String endDate){
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return map.get("Max");
    }

    /**
     * Function calculates the Standard Deviation exchange rate between 2 specified currency within a given time frame.
     * @param currOne Currency from
     * @param currTwo Currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date onw wants rates before, in format "YYYY-MM-DD" inclusive.
     * @return double which is the Deviation exchange rate between these values.
     */
    public double getSD(String currOne, String currTwo ,String startDate, String endDate){
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return Math.sqrt(map.get("Var"));
    }


    /**
     * This function used to get the header of the popular 4 currencies
     * @return an array list with tht header of the popular 4 currencies, format [From/To, curr1, curr2, curr3, curr4]
     */
    public String[] getPopular4Header(){
         String [] temp = new String [5];
         temp[0] = "From/To";

         String[] data = this.database.getPopularFour();

         int count = 1;
         for (String s : data){
             temp[count] = s;
             count += 1;
         }
         return temp;
    }

    /**
     * The function gets the popular 4 currencies and their current exchange rate between  each in a [4][5] matrix
     * @return stirng [][] matrix of the popular 4 currencies and their currencies.
     */
    public String [][] getPopular4Data(){
         String [][] temp = new String [4][5];
         String [] header = this.getPopular4Header();

         boolean first = true;
         int count = 1;
         for(String [] s : temp){
             s[0] = header[count];
             count += 1;
         }

         temp [0][1] = "-";
         temp [1][2] = "-";
         temp [2][3] = "-";
         temp [3][4] = "-";

         // Row 2
         temp[0][2] = this.database.getExchange(temp[0][0], header[2]) + "";
         temp[0][3] = this.database.getExchange(temp[0][0], header[3]) + "";
         temp[0][4] = this.database.getExchange(temp[0][0], header[4]) + "";

        //Row 3;
        temp[1][1] = this.database.getExchange(temp[0][0], header[1]) + "";
        temp[1][3] = this.database.getExchange(temp[0][0], header[3]) + "";
        temp[1][4] = this.database.getExchange(temp[0][0], header[4]) + "";

        // Row 4
        temp[2][1] = this.database.getExchange(temp[0][0], header[1]) + "";
        temp[2][2] = this.database.getExchange(temp[0][0], header[2]) + "";
        temp[2][4] = this.database.getExchange(temp[0][0], header[4]) + "";

        // Row 5
        temp[3][1] = this.database.getExchange(temp[0][0], header[1]) + "";
        temp[3][2] = this.database.getExchange(temp[0][0], header[2]) + "";
        temp[3][3] = this.database.getExchange(temp[0][0], header[3]) + "";

        return temp;

    }







}
