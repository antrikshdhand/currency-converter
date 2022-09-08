package CurrencyConverter;

public class Currency {
    
    // Two main attributes of currency
    private String code; // Stores the currency Code
    private String symbol; // Stores the symbol that denotes the currency


    /*
     * Constructor for the currency class
     * 
     * @params code - takes the currency code that represents a currency and assigns it to the instance class
     * @params symbol - takes the currency symbol that represents a currency and assigns it to the instance class
     */
    public Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }


    /*
     * Getter method for currency code
     */
    public String getCode() {
        return this.code;
    }

    /*
     * Getter method for currency name
     */
    public String getName() {
        return this.symbol;
    }
    
    public static void main(String[] args) {
        
    }
}