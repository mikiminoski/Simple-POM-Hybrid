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

    HomePage homepage = getHomePage();
    LoginPage loginpage = getLoginPage();


    @Test (priority = 0, groups = "LoginTests")
    public void open_page_then_login_with_unregistered_mail() {
        homepage.click_login();

        loginpage.enterMail("UNREGISTERED_MAIL")
                .enterPass("INVALID_PASSWORD")
                .clickSignIn();

        loginpage.checkErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void open_page_then_login_with_wrong_password() {
        homepage.click_login();

        loginpage.enterMail(ACCOUNT_MAIL)
                .enterPass("INVALID_PASSWORD")
                .clickSignIn();

        loginpage.checkErrorMessage(ERROR_MESSAGE);

    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_login_without_email_and_password() {
        homepage.click_login();

        loginpage.clickSignIn();

        loginpage.checkMailErrorMessage(ERROR_MESSAGE)
                .checkPasswordErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_login_without_email() {
        homepage.click_login();

        loginpage.enterPass(ACCOUNT_PASS)
                .clickSignIn();

        loginpage.checkMailErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_login_without_password() {
        homepage.click_login();

        loginpage.enterMail(ACCOUNT_MAIL)
                .clickSignIn();

        loginpage.checkPasswordErrorMessage(ERROR_MESSAGE);
    }

    @Test (priority = 0, groups = "LoginTests")
    public void try_to_log_in_without_symbol() {
        homepage.click_login();

        loginpage.enterMail("MAIL_WITHOUT_SYMBOL")
                .enterPass(ACCOUNT_PASS)
                .clickSignIn();

        loginpage.checkMailErrorMessage(ERROR_MESSAGE);
    }

    @Test(priority = 0, groups = "LoginTests")
    public void try_to_log_in_with_capital_pass_letters() {
        homepage.click_login();

        loginpage.enterMail(ACCOUNT_MAIL)
                .enterPass("VALID_PASSWORD_WITH_CAPITAL")
                .clickSignIn();

        loginpage.checkErrorMessage(ERROR_MESSAGE);
    }

    /**
     * Successful login test with
     * validation for successful login url and message
     * The priority of the test is set to 1 so the test will run last
     */
    @Test (priority = 1, groups = "LoginTests")
    public void open_page_then_login_with_valid_credentials() {
        homepage.click_login();

        loginpage.enterMail(ACCOUNT_MAIL)
                .enterPass(ACCOUNT_PASS)
                .clickRememberMeButton()
                .clickSignIn();
    }

}
