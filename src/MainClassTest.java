import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        MainClass main = new MainClass();
        assertEquals("#testGetLocalNumber should return 14", 14, main.getLocalNumber());
    }
}