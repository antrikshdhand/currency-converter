package CurrencyConverter;

public class Calculator {

    private Database database;

    public Calculator(Database database) {
        this.database = database;
    }

    public double calculateConverstion(Currency currencyFrom, Currency currencyTo, double amount) {

        if (currencyFrom == null || currencyTo == null) {
            return (Double) null;
        }

    }

}