package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static DriverFactory.DriverFactory.getChromeDriver;
import static DriverFactory.DriverFactory.getFirefoxDriver;
import static SupportVariables.URLs.BASE_URL;

public class LoginPage {

    private WebDriver driver = getChromeDriver();

    protected LoginPage() {
    }

    public static LoginPage getLoginPage() {
        return new LoginPage();
    }

    WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(20));


    /**
     * Here are all WebElements we use, and we declare them as private WebElements that returns the driver.findElement(By...)
     * @return WebElement
     */
    private WebElement emailTextField() {
        return driver.findElement(By.xpath("The mail text field xpath"));
    }

    private WebElement passTextField() {
        return driver.findElement(By.id("password"));
    }

    private WebElement rememberMeCheckbox() {
        return driver.findElement(By.id("remember"));
    }

    private WebElement forgotPassButton() {
        return driver.findElement(By.id("forgot_password_request_link"));
    }

    private WebElement signInButton() {
        return driver.findElement(By.id("sign_in_button_id"));
    }

    private WebElement registerButton() {
        return driver.findElement(By.id("register_link"));
    }

    private WebElement errorMessageField() {
        return driver.findElement(By.id("info_message"));
    }

    private WebElement passwordErrorMSG() {
        return driver.findElement(By.id("validation_password_message"));
    }

    private WebElement emailErrorMSG() {
        return driver.findElement(By.id("validation_email_message"));
    }



    /**
     * Here are all methods examples from this class
     * Every method that takes action and stays on the page should return this class so we can do a method chaining
     */
    public LoginPage enterMail(String email) {
        emailTextField().sendKeys(email);

        return this;
    }

    public LoginPage enterPass(String password) {
        passTextField().sendKeys(password);

        return this;
    }


    public void clickSignIn() {
        signInButton().click();
    }

    public LoginPage clickRememberMeButton() {
        Assert.assertTrue(!rememberMeCheckbox().isSelected());

        rememberMeCheckbox().click();

        Assert.assertTrue(rememberMeCheckbox().isSelected());

        return this;
    }

    public LoginPage checkErrorMessage(String expectedErrorMessage) {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageField()));

            String errorMsg = errorMessageField().getText();

            Assert.assertEquals(errorMsg,expectedErrorMessage);
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.stalenessOf(errorMessageField()));

            String errorMsg = errorMessageField().getText();

            Assert.assertEquals(errorMsg,expectedErrorMessage);
        }

        return this;
    }

    public LoginPage checkPasswordErrorMessage(String expectedErrorMessage) {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMSG()));

        String plsEnterPass = passwordErrorMSG().getText();

        Assert.assertTrue(passwordErrorMSG().isDisplayed());

        Assert.assertEquals(plsEnterPass, expectedErrorMessage);

        return this;
    }

    public void clickForgetPassword() {
        forgotPassButton().click();

        wait.until(ExpectedConditions.urlToBe("SOME URL"));

    }

    public LoginPage checkMailErrorMessage(String expectedErrorMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMSG()));

        String errorMessage = emailErrorMSG().getText();

        Assert.assertTrue(emailErrorMSG().isDisplayed());

        Assert.assertEquals(errorMessage, expectedErrorMessage);

        return this;
    }

    public LoginPage checkLoginPage() {
        Assert.assertTrue(registerButton().isDisplayed());
        Assert.assertTrue(registerButton().isEnabled());


        Assert.assertTrue(emailTextField().isEnabled());
        Assert.assertTrue(emailTextField().isDisplayed());

        Assert.assertTrue(passTextField().isDisplayed());
        Assert.assertTrue(passTextField().isEnabled());

        Assert.assertTrue(rememberMeCheckbox().isEnabled());
        Assert.assertTrue(rememberMeCheckbox().isDisplayed());

        Assert.assertTrue(forgotPassButton().isDisplayed());
        Assert.assertTrue(forgotPassButton().isEnabled());

        Assert.assertTrue(signInButton().isEnabled());
        Assert.assertTrue(signInButton().isDisplayed());

        return this;
    }
}
