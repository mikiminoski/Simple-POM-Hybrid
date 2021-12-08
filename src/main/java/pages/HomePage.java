package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import static DriverFactory.DriverFactory.getChromeDriver;
import static SupportVariables.URLs.BASE_URL;

public class HomePage {

    private WebDriver driver = getChromeDriver();


    private HomePage(){
    }

    public static HomePage getHomePage() {
        return new HomePage();
    }

    WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));


    /**
     * Here are all WebElements we use, and we declare them as private WebElements that returns the driver.findElement(By...)
     *
     */
    private WebElement loginButton() {
        return driver.findElement(By.id("the_login_button_id"));
    }

    private WebElement userMenuLogout() {
        return driver.findElement(By.id("user_menu_logout_link"));
    }


    /**
     * Here are all the methods examples from this class
     * Every method that takes action and stays on the page should return this class so we can do a method chaining
     */
    public void click_login() {
        wait.until(ExpectedConditions.visibilityOf(loginButton()));

        Assert.assertTrue(loginButton().isEnabled());

        loginButton().click();

        wait.until(ExpectedConditions.urlToBe("LOGIN_URL"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "LOGIN_URL");
    }
}