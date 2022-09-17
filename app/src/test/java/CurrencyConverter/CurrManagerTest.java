package CurrencyConverter;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTimeout;
// import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;



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




}
