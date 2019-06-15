import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        MainClass main = new MainClass();
        assertEquals("#getLocalNumber should return 14", 14, main.getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        MainClass main = new MainClass();
        assertTrue("#getClassNumber should return > 45", main.getClassNumber() > 45);
    }
}