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

    private WebElement passwordTextField() {
        return driver.findElement(By.id("password"));
    }

    private WebElement rememberMeCheckbox() {
        return driver.findElement(By.id("remember"));
    }

    private WebElement forgotPasswordButton() {
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

    private WebElement passwordErrorMessage() {
        return driver.findElement(By.id("validation_password_message"));
    }

    private WebElement emailErrorMessage() {
        return driver.findElement(By.id("validation_email_message"));
    }



    /**
     * Here are all methods examples from this class
     * Every method that takes action and stays on the page should return this class so we can do a method chaining
     */
    public LoginPage enterEMail(String email) {
        emailTextField().sendKeys(email);

        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordTextField().sendKeys(password);

        return this;
    }


    public void clickSignIn() {
        signInButton().click();
    }

    public LoginPage clickRememberMeButton() {
        Assert.assertFalse(rememberMeCheckbox().isSelected());

        rememberMeCheckbox().click();

        Assert.assertTrue(rememberMeCheckbox().isSelected());

        return this;
    }

    public LoginPage checkErrorMessage(String expectedErrorMessage) {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageField()));

            String errorMessage = errorMessageField().getText();

            Assert.assertEquals(errorMessage,expectedErrorMessage);
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.stalenessOf(errorMessageField()));

            String errorMessage = errorMessageField().getText();

            Assert.assertEquals(errorMessage,expectedErrorMessage);
        }

        return this;
    }

    public LoginPage checkPasswordErrorMessage(String expectedErrorMessage) {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage()));

        String pleaseEnterPass = passwordErrorMessage().getText();

        Assert.assertTrue(passwordErrorMessage().isDisplayed());

        Assert.assertEquals(pleaseEnterPass, expectedErrorMessage);

        return this;
    }

    public void clickForgetPassword() {
        forgotPasswordButton().click();

        wait.until(ExpectedConditions.urlToBe("SOME URL"));

    }

    public LoginPage checkMailErrorMessage(String expectedErrorMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage()));

        String errorMessage = emailErrorMessage().getText();

        Assert.assertTrue(emailErrorMessage().isDisplayed());

        Assert.assertEquals(errorMessage, expectedErrorMessage);

        return this;
    }

    public LoginPage checkLoginPage() {
        Assert.assertTrue(registerButton().isDisplayed());
        Assert.assertTrue(registerButton().isEnabled());


        Assert.assertTrue(emailTextField().isEnabled());
        Assert.assertTrue(emailTextField().isDisplayed());

        Assert.assertTrue(passwordTextField().isDisplayed());
        Assert.assertTrue(passwordTextField().isEnabled());

        Assert.assertTrue(rememberMeCheckbox().isEnabled());
        Assert.assertTrue(rememberMeCheckbox().isDisplayed());

        Assert.assertTrue(forgotPasswordButton().isDisplayed());
        Assert.assertTrue(forgotPasswordButton().isEnabled());

        Assert.assertTrue(signInButton().isEnabled());
        Assert.assertTrue(signInButton().isDisplayed());

        return this;
    }
}
