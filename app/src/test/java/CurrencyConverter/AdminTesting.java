package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertTimeout;
// import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class AdminTesting {

    Admin user;
    CurrManager db;
    @BeforeEach
    public void createNewCurrManager() {
        db = new CurrManager();
        db.openConn();
        db.dropAllTables();
        this.user = new Admin( this.db);
    }

    @AfterEach
    public void deleteCurrManager() {
        db = null;
        user = null;
    }


    @Test
    public void basic6Testing(){
        this.user.reset();

        this.db.openConn();
        String name = this.db.getCurrName("AUD");
        this.db.closeConn();

        assertEquals("Australian Dollar",name);
    }

    @Test
    public void addCurrencyTesting(){
        this.user.addCurrency("DEN","Denmark Dollar");
        this.user.addCurrency("MEX", "Mexican Peso");

        this.db.openConn();
        String mex = this.db.getCurrName("MEX");
        String den = this.db.getCurrName("DEN");
        this.db.closeConn();
        assertEquals("Mexican Peso", mex);
        assertEquals("Denmark Dollar", den);
    }

    @Test
    public void addExchangeTesting(){
        this.user.addCurrency("DEN","Denmark Dollar");
        this.user.addCurrency("MEX", "Mexican Peso");

        this.user.addExchange("MEX", "DEN", 1.5 );
        this.db.openConn();
        double mex = this.db.getExchange("Mex", "DEN");
        this.db.closeConn();

        assertNotEquals(0, mex);

    }

    @Test
    public void setPopularHeadersTesting(){
        this.user.reset();

        String [] pop = {"GBP", "AUD", "USD", "SGD"};
        this.user.addPopularCurrency(pop);

        String [] header = this.user.getPopular4Header();
        assertEquals("From/To", header[0]);
        assertEquals("SGD", header[4]);
    }

    @Test
    public void getPopular4DataTesting(){
        this.user.reset();

        String [] pop = {"GBP", "AUD", "USD", "SGD"};
        this.user.addPopularCurrency(pop);

        String [][] data = this.user.getPopular4Data();

        assertEquals( "-", data[0][1]);
        assertEquals( pop[0], data[0][0]);
        assertEquals( pop[1], data[1][0]);
        assertNotEquals( "0", data[0][1]);
        assertNotEquals("0", data[1][4]);


    }

    @Test
    public void headerAndDataTesting(){
        this.user.reset();

        String [] pop = {"GBP", "AUD", "USD", "SGD"};
        this.user.addPopularCurrency(pop);

        String [] header = this.user.getPopular4Header();

        String [][] data = this.user.getPopular4Data();

        assertEquals( "-", data[0][1]);
        assertEquals(header[1], data[0][0]);
        assertEquals( header[2], data[1][0]);
        assertNotEquals( "0", data[0][1]);
        assertNotEquals("0", data[1][4]);


    }
}


