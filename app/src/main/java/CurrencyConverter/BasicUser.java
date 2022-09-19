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
    * function that returns the History between 2 chosen currencies between 2 dates.
    * @Param currOne: the currency from
    * @Param currTwo: the currency to
    * @Param startDate : the date form in format "YYYY-MM-DD"
    * @Param endDate : the date to in format "YYYY-MM-DD"
    * @return an array of string, where the string is in the format currency from, currency to, exchange rate, Date.
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
            result[0] = temp;
            count += 0;
        }

        return result;
    }

    public String[] getPopular4Header(){
        String [] temp = new String [5];
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

        this.database.closeConn();

        return temp;

    }



}
