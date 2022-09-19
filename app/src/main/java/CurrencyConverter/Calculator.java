package CurrencyConverter;

public class Calculator {

    //private Database database;

    private App app;


    public Calculator(App app) {
    // public Calculator(Database database) {
        // this.database = database;
        this.app = app;
    }


    /**
     * @param currencyFrom
     * @param currencyTo
     * @param amount
     * @return
     */
    public double calculateConverstion(String currencyFrom, String currencyTo, double amount) {

        if (
        // currencyFrom == null 
        // || currencyTo == null
        // || amount == (Double) null
        currencyFrom.equalsIgnoreCase("")
        || currencyTo.equalsIgnoreCase("")
        || amount == 0) {
            return -1;
        }

        app.getManager().openConn();
        double rate = app.getManager().getExchange(currencyFrom, currencyTo);
        app.getManager().closeConn();

        return amount * rate;

    }

    public void hello() {
        System.out.println("hello");
    }

}