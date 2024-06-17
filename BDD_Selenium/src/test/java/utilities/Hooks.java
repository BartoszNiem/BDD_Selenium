package utilities;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.BeforeClass;

public class Hooks {

    public static BrowserDriver driver;

    @Before
    public void setUp(){
        driver = new BrowserDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
