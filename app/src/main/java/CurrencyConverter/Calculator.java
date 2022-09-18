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

        if (currencyFrom == null || currencyTo == null) {
            return (Double) null;
        }

        if (amount == 0) {
            return 0;
        }

        double rate = app.getManager().getExchange(currencyFrom, currencyTo);

        return amount * rate;

    }

}