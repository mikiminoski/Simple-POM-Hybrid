package Tests1;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static SupportVariables.Credentials.ACCOUNT_MAIL;
import static SupportVariables.Credentials.ACCOUNT_PASS;
import static SupportVariables.ErrorMessages.ERROR_MESSAGE;
import static pages.HomePage.getHomePage;
import static pages.LoginPage.getLoginPage;

public class LoginTests extends BaseSet {

    HomePage homePage = getHomePage();
    LoginPage loginPage = getLoginPage();


    @Test (priority = 0, groups = "LoginTests")
    public void open_page_then_login_with_unregistered_mail() {
        homePage.click_login();

        loginPage.enterEMail("UNREGISTERED_MAIL")
                .enterPassword("INVALID_PASSWORD")
                .clickSignIn();

        loginPage.checkErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void open_page_then_login_with_wrong_password() {
        homePage.click_login();

        loginPage.enterEMail(ACCOUNT_MAIL)
                .enterPassword("INVALID_PASSWORD")
                .clickSignIn();

        loginPage.checkErrorMessage(ERROR_MESSAGE);

    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_login_without_email_and_password() {
        homePage.click_login();

        loginPage.clickSignIn();

        loginPage.checkMailErrorMessage(ERROR_MESSAGE)
                .checkPasswordErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_login_without_email() {
        homePage.click_login();

        loginPage.enterPassword(ACCOUNT_PASS)
                .clickSignIn();

        loginPage.checkMailErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_login_without_password() {
        homePage.click_login();

        loginPage.enterEMail(ACCOUNT_MAIL)
                .clickSignIn();

        loginPage.checkPasswordErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_log_in_without_symbol() {
        homePage.click_login();

        loginPage.enterEMail("MAIL_WITHOUT_SYMBOL")
                .enterPassword(ACCOUNT_PASS)
                .clickSignIn();

        loginPage.checkMailErrorMessage(ERROR_MESSAGE);
    }

    @Test(priority = 0, groups = "LoginTests")
    public void try_to_log_in_with_capital_password_letters() {
        homePage.click_login();

        loginPage.enterEMail(ACCOUNT_MAIL)
                .enterPassword("VALID_PASSWORD_WITH_CAPITAL")
                .clickSignIn();

        loginPage.checkErrorMessage(ERROR_MESSAGE);
    }

    /**
     * Successful login test with
     * validation for successful login url and message
     * The priority of the test is set to 1 so the test will run last
     */
    @Test (priority = 1, groups = "LoginTests")
    public void open_page_then_login_with_valid_credentials() {
        homePage.click_login();

        loginPage.enterEMail(ACCOUNT_MAIL)
                .enterPassword(ACCOUNT_PASS)
                .clickRememberMeButton()
                .clickSignIn();
    }

}
