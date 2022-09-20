package CurrencyConverter;

import java.util.*;

public class BasicUser {

    /**
     * Attribute database : CurrManager
     */
    protected CurrManager database;

    /**
     * Constuctor of class
     * @param Database : take in Currmanager which queries a database.
     */
    public BasicUser(CurrManager database) {
        this.database = database;
    }


    /**
     * Function that return the CurrManager
     */
    public CurrManager getDatabase() {
        return database;
    }
    

    /**
     * Function that convert an amount form one currency to another.
     * @param from : currency from which one wishes to convert form (String)
     * @param to : currency to which one wishes to convert to (String)
     * @param amount : the amount one wishes to convert (double)
     * @return, return the converted amount as a ( double)
     */
    public double convert(String from, String to, double amount){
        // here goes the logic of querying the from one currency, to another.

        if (from.equals(to)) return amount;

        double result = 0;
        database.openConn();
        double rate = database.getExchange(from,to);
        database.closeConn();
        if ((int)rate == -1) return 0;

        result = rate * amount;

        return result;
    }

    public String[] getCurrencyCodes() {

        this.database.openConn();
        HashMap<String, String> allCurrencies = this.database.getAllCurrencies();
        this.database.closeConn();

        String[] result = new String [allCurrencies.size()];

        int count = 0;
        for (String s : allCurrencies.keySet()){
            result[count] = s;
            count += 1;
        }
        return result;
    
    }

    /**
     * Function that returns the History between 2 chosen currencies between 2 date.
     * @param currOne: the currency from
     * @param currTwo: the currency to
     * @param startDate : the date form in format "YYYY-MM-DD"
     * @param endDate : the date to in format "YYYY-MM-DD"
     * @return A 2 dimensional array where the index is the timestamp and the index 1 is the exchange rate at the time.
     */
    public String[][] getHistory(String currOne, String currTwo, String startDate, String endDate) {
        database.openConn();
        ArrayList<ArrayList<String>> map = this.database.getExchangeHist(currOne, currTwo, startDate, endDate);
        database.closeConn();
        int length = map.size();

        String[][] result = new String[length][2];

        int count = 0;
        for (ArrayList<String> val : map){
            String[] temp = {val.get(0), val.get(1)};
            result[count] = temp;
            count += 1;
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
        else {
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
        String[] temp = new String[5];
        temp[0] = "From/To";

        this.database.openConn();
        String[] data = this.database.getPopularFour();
        this.database.closeConn();

        int count = 1;
        for (String s : data){
            temp[count] = s;
            count += 1;
        }
        return temp;
    }

    /**
     * The function gets the popular 4 currencies and their current exchange rate between  each in a [4][5] matrix
     * @return string[][] matrix of the popular 4 currencies and their currencies.
     */
    public String [][] getPopular4Data(){
        String[][] temp = new String[4][5];
        String[] header = this.getPopular4Header();

        boolean first = true;
        int count = 1;
        for(String[] s : temp){
            s[0] = header[count];
            count += 1;
        }

        this.database.openConn();

        temp[0][1] = "-";
        temp[1][2] = "-";
        temp[2][3] = "-";
        temp[3][4] = "-";

        // Row 2
        temp[0][0] = header[1];
        temp[0][2] = this.database.getExchangeToDisp(temp[0][0], header[2]) + " " + this.checkState(temp[0][0], header[2]);
        temp[0][3] = this.database.getExchangeToDisp(temp[0][0], header[3]) + " " + this.checkState(temp[0][0], header[3]);
        temp[0][4] = this.database.getExchangeToDisp(temp[0][0], header[4]) + " " + this.checkState(temp[0][0], header[4]);

        //Row 3;
        temp[1][0] = header[2];
        temp[1][1] = this.database.getExchangeToDisp(temp[1][0], header[1]) + " " + this.checkState(temp[1][0], header[1]);
        temp[1][3] = this.database.getExchangeToDisp(temp[1][0], header[3]) + " " + this.checkState(temp[1][0], header[3]);
        temp[1][4] = this.database.getExchangeToDisp(temp[1][0], header[4]) + " " + this.checkState(temp[1][0], header[4]);

        // Row 4
        temp[2][0] = header[3];
        temp[2][1] = this.database.getExchangeToDisp(temp[2][0], header[1]) + " " + this.checkState(temp[2][0], header[1]);
        temp[2][2] = this.database.getExchangeToDisp(temp[2][0], header[2]) + " " + this.checkState(temp[2][0], header[2]);
        temp[2][4] = this.database.getExchangeToDisp(temp[2][0], header[4]) + " " + this.checkState(temp[2][0], header[4]);;

        // Row 5
        temp[3][0] = header[4];
        temp[3][1] = this.database.getExchangeToDisp(temp[3][0], header[1]) + " " + this.checkState(temp[3][0], header[1]);
        temp[3][2] = this.database.getExchangeToDisp(temp[3][0], header[2]) + " " + this.checkState(temp[3][0], header[2]);
        temp[3][3] = this.database.getExchangeToDisp(temp[3][0], header[3]) + " " + this.checkState(temp[3][0], header[3]);

        this.database.closeConn();

        return temp;

    }


    public String checkState(String currOne, String currTwo) {

        Double[] arr = this.database.getLatestThreeHist(currOne, currTwo);

        if (arr[1] == null) {
            return "(NEW)";
        }

        if (arr[0] > arr[1]) {
            return "I!";
        } else {
            return "D¡";

        }

    }

}
