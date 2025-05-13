import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {

    @Test
     void CheckProductIdTest() {
        var Test = new WestminsterShoppingManager();
        assertEquals(true,Test.CheckProductId("A0001"));

    }












}