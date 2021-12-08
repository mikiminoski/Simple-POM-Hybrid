package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;

import static DriverFactory.DriverFactory.getChromeDriver;

public class MethodExamples {

    /**
     * Getting the driver
     */
    private WebDriver driver = getChromeDriver();

    /**
     * Setting an object through JavascriptExecutor interface
     * to help up execute JavaScript through Selenium Webdriver
     */
    JavascriptExecutor javaScript = (JavascriptExecutor) driver;

    /**
     * Implicit wait
     */
    WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));




    /**
     * WebElement declared
     * @return the web element
     */
    private WebElement iFrameSelector() {
        return driver.findElement(By.cssSelector("#card-element > div > iframe"));
    }

    /**
     * WebElement declared
     * @return WebElement
     */
    private WebElement cardNumberTextField(){
        return driver.findElement(By.id("some_id"));
    }

    /**
     * This method switch the frame to iFrame in the HTML
     * Then execute via Javascript and returns to the main frame
     * @param cardNumber
     * @return this class
     */
    public MethodExamples iFrameHandlingExamplePaymentMethod(String cardNumber) {
        wait.until(ExpectedConditions.visibilityOf(iFrameSelector()));

        driver.switchTo().frame(iFrameSelector());

        javaScript.executeScript("arguments[0].scrollIntoView();",cardNumberTextField());

        cardNumberTextField().sendKeys(cardNumber);

        driver.switchTo().parentFrame();

        return this;
    }


    /**
     * This is helper method that gets the attribute for the Web Element given as a parameter
     * @param web_element
     * @return String "true" or empty String
     */
    private String checkAttributeAria(WebElement web_element){
        String aria_checked = "aria-checked";

        return web_element.getAttribute(aria_checked);
    }

    /**
     * This method clicks and asserts the functionality of any checkbox given as a param
     * The method is selecting and unselecting the checkboxes and assert them
     *
     * @param checkbox (public class method that returns Web Element)
     * @param trueOrFalse (if you want to check the checkbox or not you give param true or false)
     * @return
     */
    public MethodExamples selectCheckbox(WebElement checkbox, boolean trueOrFalse) {
        String true_ = "true";
        String no_attribute = "";
        String check_attribute = checkAttributeAria(checkbox);


        if (trueOrFalse) {
            if (check_attribute.equals(true_)) {

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertEquals(check_attribute,no_attribute);

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertSame(check_attribute, true_);

            } else {
                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertSame(check_attribute, true_);

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertEquals(check_attribute, no_attribute);

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertSame(check_attribute, true_);
            }
        }
        else {
            if (check_attribute.equals(true_)) {
                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertEquals(check_attribute,no_attribute);

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertSame(check_attribute,true_);

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertEquals(check_attribute,no_attribute);

            } else {
                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertSame(check_attribute, true_);

                checkbox.click();

                check_attribute = checkAttributeAria(checkbox);

                Assert.assertEquals(check_attribute,no_attribute);
            }
        }

        return this;
    }

    /**
     * And here we provide all the checkboxes we Assert them and then select if we want to check them or not via trueOrFalse boolean
     * @param trueOrFalse
     * @return
     */
    public MethodExamples checkCheckboxes(boolean trueOrFalse) {
        selectCheckbox(someCheckbox1(),trueOrFalse);
        selectCheckbox(someCheckbox2(),trueOrFalse);

        return this;
    }

    public WebElement someCheckbox1() {
        return driver.findElement(By.xpath("the checkbox xpath"));
    }

    public WebElement someCheckbox2() {
        return driver.findElement(By.xpath("the checkbox xpath"));
    }



    /**
     * First we are trying to click the button regularly if we have ClickInterceptedException then we click the button via JavascriptExecutor
     * @return
     */
    public MethodExamples handlingElementClickInterceptedException() {
        String radioButton = radioButton().getText();
        Assert.assertTrue(radioButton().isDisplayed());
        Assert.assertTrue(radioButton().isEnabled());
        Assert.assertEquals(radioButton,"Name of the radio button");

        wait.until(ExpectedConditions.elementToBeClickable(radioButton()));
        try {
            radioButton().click();
        } catch (ElementClickInterceptedException e) {
            WebElement element = radioButton();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        }
        radioButton().click();


        return this;
    }

    /**
     * Here we declare the radio button
     * @return WebElement
     */
    public WebElement radioButton() {
        return driver.findElement(By.xpath("some xpath"));
    }


    /**
     * We use the Action class to perform a hover over an element.
     * @return MethodExamples
     */
    public MethodExamples hoverTooltipExample() {
        Actions actions = new Actions(driver);

        actions.moveToElement(tooltip()).perform();

        return this;
    }

    /**
     * Here we declare the tooltip element
     * @return WebElement
     */
    public WebElement tooltip() {
        return driver.findElement(By.xpath("some xpath"));
    }



    /**
     * Example of handling multiple tabs and asserting them.
     * After clicking some button that opens a new tab we set an Array list that get the windows of that driver session
     * then we switch to the tab we like to
     * then asserting the tab.
     * @return MethodExamples
     */
    public MethodExamples newTabHandlingExample() {
        buttonForNewTab().click();

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(newTab.get(1));

        Assert.assertEquals(driver.getCurrentUrl(), "some url");

        //here we do all the other assertions...

        driver.close();

        driver.switchTo().window(newTab.get(0));

        return this;
    }

    private WebElement buttonForNewTab(){
        return driver.findElement(By.id("the element id"));
    }


    /**
     * Example of dynamic method for choosing just one option
     * @param someTypeOfSomethingToChooseBetween
     * @return MethodExamples
     */
    public MethodExamples selectContinue(String someTypeOfSomethingToChooseBetween) {
        String Info = typeInfo().getText();

        if (someTypeOfSomethingToChooseBetween.equals("TYPE1")) {
            type1().click();

            Assert.assertEquals(Info, "TYPE2-INFO");
        }

        if (someTypeOfSomethingToChooseBetween.equals("TYPE2")) {
            type2().click();

            Assert.assertEquals(Info, "TYPE2-INFO");
        }

        return this;
    }

    private WebElement typeInfo() {
        return driver.findElement(By.id("the id here"));
    }

    private WebElement type1() {
        return driver.findElement(By.id("the id here"));
    }

    private WebElement type2() {
        return driver.findElement(By.id("the id here"));
    }
}
