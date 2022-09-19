package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertTimeout;
// import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class BasicUserTesting {

    BasicUser user;
    CurrManager db;
    @BeforeEach
    public void createNewCurrManager() {
        db = new CurrManager();
        db.openConn();
        db.dropAllTables();
        this.user = new BasicUser( this.db);
    }

    @AfterEach
    public void deleteCurrManager() {
        db = null;
        user = null;
    }


    @Test
    public void testTrivalConversions(){
        // converting from same to same currency shnould return the unchanged amount.
        double result1 = user.convert("AUD","AUD",200);
        assertEquals(200, result1);

        // the database does not have singapore dollar in it yet, so should return nothing just 0.

        double result2 = user.convert("USD", "AUD", 200);
        assertNotEquals( 200, result2 );
        assertEquals(0, result2);


    }

    @Test
    public void onlyCurrencyIntegrationTestConvert(){
        this.db.openConn();
        this.db.addCurrency("SNG", "Singapore");
        this.db.closeConn();

        // Only 1 currency has been added to so should return -1 and the wrong result
        assertEquals(0, user.convert("SNG", "AUD", 200));

        this.db.openConn();
        this.db.addCurrency("AUD", "Australia");
        this.db.closeConn();

        // 2 Currencies but no exchange rate between the currency, hence should return -1 and wrong  result.
        assertEquals(0, user.convert("AUD", "SNG", 200));
        }


    @Test
    public void onlyExchangeIntergrationTestConvert(){
        this.db.openConn();
        int result1= this.db.addExchange("AUD", "USD", 1.69);
        HashMap<String, String> name = db.getAllCurrencies();
        this.db.closeConn();

        // only exchange rate are being added and the actual currencies do not exist in the db yet. Hence, should return -1.
        assertEquals(0, name.size());

    }

    @Test
    public void fullExchangeIntegrationTesting(){
        this.db.openConn();
        int result1 = db.addCurrency("SNG","Singapore");
        int result2 = db.addCurrency("AUD", "Australia");
        this.db.closeConn();

        assertEquals(0, result2);
        assertEquals(0, result1);

        this.db.openConn();
        int result3 = db.addExchange("AUD", "SNG", 1.3 );
        this.db.closeConn();

        assertEquals(0, result3);

        double result = user.convert("AUD", "SNG", 200);
        double expected = (double) 1.3 * (double) 200;
        assertEquals(expected, result);
    }



}
