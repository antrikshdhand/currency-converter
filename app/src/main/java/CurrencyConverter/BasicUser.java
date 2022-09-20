package CurrencyConverter;

import java.util.*;

public class BasicUser {

    protected CurrManager database;

    /**
     * Constuctor of class
     * @param database a CurrManager object which queries the database
     */
    public BasicUser(CurrManager database) {
        this.database = database;
    }


    /**
     * Returns the CurrManager
     * @return the current CurrManager object
     */
    public CurrManager getDatabase() {
        return database;
    }
    

    /**
     * Converts an amount from one currency to another.
     * @param from currency from which one wishes to convert form
     * @param to currency to which one wishes to convert to
     * @param amount the amount one wishes to convert
     * @return the converted amount
     */
    public double convert(String from, String to, double amount) {
        if (from.equals(to)) return amount;

        double result = 0;
        database.openConn();
        double rate = database.getExchange(from,to);
        database.closeConn();
        if ((int)rate == -1) return 0;

        result = rate * amount;

        return result;
    }


    /**
     * Provides the most up-to-date
     * list of currencies currently stored in the
     * database.
     * @return an array of all current currency codes
     */
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
     * Returns the history of an exchange
     * between two chosen currencies between two dates.
     * @param currOne the currency from
     * @param currTwo the currency to
     * @param startDate the start date in format "YYYY-MM-DD"
     * @param endDate the end date in format "YYYY-MM-DD"
     * @return an array of [timestamp, exchange rate]
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
     * Calculates the median exchange rate 
     * between 2 specified currencies within a given time frame.
     * @param currOne currency from
     * @param curTwo currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date one wants rates before, in format "YYYY-MM-DD" inclusive
     * @return the median exchange rate
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
     * Calculates the average exchange rate between 
     * 2 specified currencies within a given time frame
     * @param currOne currency from
     * @param currTwo currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date one wants rates before, in format "YYYY-MM-DD" inclusive
     * @return the average exchange rate
     */
    public double getAverage(String currOne, String currTwo ,String startDate, String endDate) {
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return map.get("Average");
    }


    /**
     * Calculates the minimum exchange rate between 
     * 2 specified currencies within a given time frame
     * @param currOne currency from
     * @param currTwo currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date one wants rates before, in format "YYYY-MM-DD" inclusive
     * @return the minimum exchange rate
     */
    public double getMinimum(String currOne, String currTwo ,String startDate, String endDate) {
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return map.get("Min");
    }


    /**
     * Calculates the maximum exchange rate between 
     * 2 specified currencies within a given time frame.
     * @param currOne currency from
     * @param currTwo currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date one wants rates before, in format "YYYY-MM-DD" inclusive
     * @return the maximum exchange rate
     */
    public double getMaximum(String currOne, String currTwo ,String startDate, String endDate) {
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return map.get("Max");
    }


    /**
     * Calculates the standard deviation
     * of exchange rates between 2 specified currencies
     * within a given time frame.
     * @param currOne currency from
     * @param currTwo currency to
     * @param startDate the date one wants rates after, in format "YYYY-MM-DD" inclusive
     * @param endDate the date one wants rates before, in format "YYYY-MM-DD" inclusive
     * @return the standard deviation exchange rate
     */
    public double getSD(String currOne, String currTwo ,String startDate, String endDate) {
        HashMap<String, Double> map = this.database.getSummaries(currOne, currTwo, startDate, endDate);
        return Math.sqrt(map.get("Var"));
    }


    /**
     * Returns the header of the popular 4 currencies
     * to be used in JTable.
     * @return an array list of format [From/To, curr1, curr2, curr3, curr4]
     */
    public String[] getPopular4Header() {
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
     * Returns the 4 popular currencies and their 
     * current relative exchange rates
     * in a [4][5] matrix to be used in JTable.
     * @return matrix of the popular 4 currencies and their relative exchange rates
     */
    public String [][] getPopular4Data() {
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
    

    /**
     * Returns whether a currency's exchange rate has
     * increased (I) or decreased (D) since the previous rate,
     * or whether a currency is newly added (NEW).
     * @param currOne
     * @param currTwo
     * @return symbol for increase, decrease or new
     */
    public String checkState(String currOne, String currTwo) {

        Double[] arr = this.database.getLatestThreeHist(currOne, currTwo);

        if (arr[1] == null) {
            return "(NEW)";
        }

        if (arr[0] > arr[1]) {
            return "(I)";
        } else {
            return "(D)";
        }

    }

}
