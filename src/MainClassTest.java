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
        int actual = main.getClassNumber();
        assertTrue("#getClassNumber should return > 45, returned " + actual, main.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString(){
        MainClass main = new MainClass();
        String actual = main.getClassString();
        assertTrue("#getClassString should return 'Hello, world' with substring 'Hello' or 'hello', but returned " + actual,
                actual.contains("Hello") || actual.contains("hello"));
    }
}