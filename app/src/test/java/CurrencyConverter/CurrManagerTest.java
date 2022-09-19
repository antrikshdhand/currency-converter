package CurrencyConverter;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTimeout;
// import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;



public class CurrManagerTest {
    private CurrManager currManager;

    @BeforeEach
    public void createNewCurrManager() {
        currManager = new CurrManager();
        currManager.openConn();
        currManager.dropAllTables();
    }

    @AfterEach
    public void deleteCurrManager() {
        currManager.closeConn();
        currManager = null;
    }


    @Test
    public void testTest() {
        assertTrue(true);
    }

    @Test
    public void testAddCurrency1() {
        currManager.addCurrency("TSC", "Test Currency");

        assertTrue(currManager.getAllCurrencies().containsKey("TSC"));
    }

    @Test
    public void testAddCurrency2() {
        currManager.addCurrency("TSC", "Test Currency");

        assertEquals("Test Currency", currManager.getCurrName("TSC"));
    }

    @Test
    public void addExchangeRateTest() {
        currManager.addCurrency("TC1", "Test Currency 1");
        currManager.addCurrency("TC2", "Test Currency 2");
        currManager.addExchange("TC1", "TC2", 20);

        assertEquals("Test Currency 1", currManager.getCurrName("TC1"));
        assertEquals("Test Currency 2", currManager.getCurrName("TC2"));    

        assertEquals(20.0, currManager.getExchange("TC1", "TC2"), String.format("Was %f expected %f", 20.0, currManager.getExchange("TC1", "TC2")));
        assertEquals(1/ 20.0, currManager.getExchange("TC2", "TC1"), String.format("Was %f expected %f", 1 / 20.0, currManager.getExchange("TC2", "TC1")));

    }


    @Test
    public void getExchangeRateTest() { //tests that function gets latest exchange
        currManager.addCurrency("TC1", "Test Currency 1");
        currManager.addCurrency("TC2", "Test Currency 2");
        currManager.addExchange("TC1", "TC2", 20);
        

        assertEquals("Test Currency 1", currManager.getCurrName("TC1"));
        assertEquals("Test Currency 2", currManager.getCurrName("TC2"));
        
        currManager.addExchange("TC1", "TC2", 25);

        assertEquals(25.0, currManager.getExchange("TC1", "TC2"), String.format("Was %f expected %f", 25.0, currManager.getExchange("TC1", "TC2")));
        assertEquals(1/ 25.0, currManager.getExchange("TC2", "TC1"), String.format("Was %f expected %f", 1 / 25.0, currManager.getExchange("TC2", "TC1")));

    }

    @Test
    public void getExchangeRateHistTest() { //tests that function gets latest exchange
        currManager.addCurrency("TC1", "Test Currency 1");
        currManager.addCurrency("TC2", "Test Currency 2");
        currManager.addExchange("TC1", "TC2", 20);
        

        assertEquals("Test Currency 1", currManager.getCurrName("TC1"));
        assertEquals("Test Currency 2", currManager.getCurrName("TC2"));
        
        currManager.addExchange("TC1", "TC2", 25);

        ArrayList<ArrayList<String>> hist = currManager.getExchangeHist("TC1", "TC2", "2022-09-18", "2022-09-19");

        // for (ArrayList<String> ar : hist) {
        //     for (String s : ar) {
        //         System.out.print(s + ", ");
        //     }

        //     System.out.println("");
        // }

        // String[][] stringArray = hist.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

        // System.out.println(stringArray);

        assertEquals(2, hist.size(), String.format("Expected 2 but was %d", hist.size()));
    }

}
