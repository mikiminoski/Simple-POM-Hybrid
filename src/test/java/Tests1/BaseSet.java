package Tests1;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static DriverFactory.DriverFactory.getChromeDriver;
import static SupportVariables.Urls.BASE_URL;

public class BaseSet {
    WebDriver driver;

    /**
     * Setting before suite and getting the ChromeDriver
     *
     * @return void
     */
    @BeforeSuite
    public void setUp() {
        driver = getChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @BeforeClass
    public void nullPointer(){
        driver = getChromeDriver();
    }

    @BeforeMethod
    public void goToUrl() {
        driver.get(BASE_URL);
    }

    /**
     * Closing the browser
     * @return void
     */
    @AfterSuite
    public void quit() {
        driver.quit();
    }
}
