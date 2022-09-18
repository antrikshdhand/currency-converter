package CurrencyConverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertTimeout;
// import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.*;


public class BasicUserTesting {

    BasicUser user;
    CurrManager db;

    @BeforeEach
    public void createUser(){
        this.db = new CurrManager();
        this.user = new BasicUser( this.db);
    }

    @Test
    public void testTrivalConversions(){
        double result1 = user.convert("AUD","AUD",200);
        assertEquals(200, result1);

        double result2 = user.convert("USD", "AUD", 200);
        assertNotEquals( 200, result2 );
        assertEquals(0, result2);

    }

    @Test
    public void simpleIntegrationTestConvert(){
        this.db.openConn();
        this.db.addCurrency("SNG", "Singapore");
        this.db.closeConn();

        assertEquals(0, user.convert("SNG", "AUD", 200));
        assertTrue(-1 < user.convert("AUD", "SNG", 300));

    }


}
