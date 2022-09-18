package CurrencyConverter;

public class Calculator {

    // private Database database;

    public Calculator() {
    // public Calculator(Database database) {
        // this.database = database;
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

        double rate = 1;
        //double rate = database.getRate(currencyFrom.getCode(), currencyTo.getCode())

        return amount * rate;

    }

}