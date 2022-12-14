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

    @Test
    public void getHistoryTest(){
        this.db.openConn();
        this.db.addCurrency("AUD", "Australia");
        this.db.addCurrency("USD", "America");
        this.db.addExchange("AUD", "USD", 1.5);
        this.db.addExchange("AUD", "USD", 1.55);
        this.db.closeConn();

        String [][] arr = user.getHistory("AUD", "USD", "2022-09-18", "2022-09-25");

        assertEquals(2, arr.length);
        assertEquals(1.55, Double.parseDouble(arr[0][1]));
        assertEquals(1.5, Double.parseDouble(arr[1][1]));


    }

    @Test
    public void getCodes() {
        this.db.openConn();
        this.db.addCurrency("AUD", "Australia");
        this.db.addCurrency("USD", "America");
        this.db.addExchange("AUD", "USD", 1.5);
        this.db.addExchange("AUD", "USD", 1.55);
        this.db.closeConn();

        String[] arr = user.getCurrencyCodes();

        assertEquals("AUD", arr[0]);
        assertEquals("USD", arr[1]);
    }

    @Test
    public void testSummaries(){
        this.db.openConn();
        this.db.addCurrency("AUD", "Australia");
        this.db.addCurrency("USD", "America");
        this.db.addExchange("AUD", "USD", 1.5);
        this.db.addExchange("AUD", "USD", 1.6);
        this.db.closeConn();

        double med = user.getMedian("AUD", "USD", "2022-09-18", "2022-09-25");
        double avg = user.getAverage("AUD", "USD", "2022-09-18", "2022-09-25");
        double max = user.getMinimum("AUD", "USD", "2022-09-18", "2022-09-25");
        double min = user.getMaximum("AUD", "USD", "2022-09-18", "2022-09-25");
        double SD = user.getSD("AUD", "USD", "2022-09-18", "2022-09-25");

        assertEquals(1.55 , med);
        assertEquals(1.55 , avg);
        assertNotEquals(0, max);
        assertNotEquals(0,min);
        assertNotEquals(0, SD);
    }


}
