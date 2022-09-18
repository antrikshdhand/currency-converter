package CurrencyConverter;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CalculatorTest {
    private Calculator calculator;
    private CurrManager currManager;

    @BeforeEach
    public void createNewCalculator() {
        currManager = new CurrManager();
        App app = new App(currManager);
        calculator = new Calculator(app);
    }

    @AfterEach
    public void deleteCalculator() {
        calculator = null;
    }

    @Test
    public void testTest() {
        assertTrue(true);
    }

    @Test
    public void testCurrencyFromEmptyString() {
        assertEquals(-1, calculator.calculateConverstion("", "AUD", 100));
    }

    @Test
    public void testCurrencyFromToString() {
        assertEquals(-1, calculator.calculateConverstion("AUD", "", 100));
    }

    @Test
    public void testAmountIsZero() {
        assertEquals(-1, calculator.calculateConverstion("USD", "AUD", 0));
    }

    @Test
    public void testAUDToUSD() {
        currManager.openConn();
        currManager.dropAllTables();
        currManager.addCurrency("TC1", "Test Currency 1");
        currManager.addCurrency("TC2", "Test Currency 2");
        currManager.addExchange("TC1", "TC2", 1.3);
        assertEquals(130.0, calculator.calculateConverstion("TC1", "TC2", 100));
        currManager.closeConn();
    }
}