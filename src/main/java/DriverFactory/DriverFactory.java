package DriverFactory;

import junitx.util.PropertyManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    static String chrome_driver = PropertyManager.getProperty("CHROME_DRIVER");
    static String chrome_driver_path = PropertyManager.getProperty("CHROME_DRIVER_PATH");
    static String firefox_driver = PropertyManager.getProperty("FIREFOX_DRIVER");
    static String firefox_driver_path = PropertyManager.getProperty("FIREFOX_DRIVER_PATH");
    static boolean headless = Boolean.parseBoolean(PropertyManager.getProperty("HEADLESS"));

    /**
     * Prevent instantiation
     */
    private DriverFactory() {
    }

    /**
     * This method prevents NullPointerException
     * It returns the ChromeDriver
     *
     * @return WebDriver
     */
    public static WebDriver getChromeDriver() {
        if (driver == null) {
            System.setProperty(chrome_driver, chrome_driver_path);

            if (headless) {
                ChromeOptions options = new ChromeOptions();

                options.addArguments("--headless").addArguments("--disable-gpu").addArguments("--window-size=1920,1080").addArguments("--start-maximized");

                driver = new ChromeDriver(options);
            } else {
                driver = new ChromeDriver();
            }

        }

        return driver;
    }

    /**
     * This method prevents NullPointerException
     * It returns the FirefoxDriver
     *
     * @return WebDriver
     */
    public static WebDriver getFirefoxDriver() {
        if (driver == null) {
            System.setProperty(firefox_driver,firefox_driver_path);

            if (headless) {
                FirefoxOptions options = new FirefoxOptions();

                options.addArguments("--headless").addArguments("--disable-gpu").addArguments("--window-size=1920,1080").addArguments("--start-maximized");

                driver = new FirefoxDriver(options);
            } else {
                driver = new FirefoxDriver();
            }

        }

        return driver;
    }
}
