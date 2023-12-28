package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class loginPagetest extends BaseTest {

    WebElement emailInput = driver.findElement(By.id("inputFieldId"));
    WebElement passwordInput = driver.findElement(By.name("password"));
    WebElement loginButton = driver.findElement(By.id("loginButtonId"));


    @Test(priority=0, description = "Verify that a user with valid credentials can successfully log in and access the system.")
    public void verifyValidLoginCredentials(){
        LoginPage login = new LoginPage(driver);
        login.enterUsername("abc_id@gmail.com");
        login.enterPassword("pwd_123");
        login.clickSignIn();
        Assert.assertEquals(login.getHomePageTitle(),"welcome xyz");
        System.out.println("Log in done successfully");
        login.clickLogOut();
    }

    @Test(priority=1, description = "Validate that an error message is displayed when attempting to log in with invalid credentials.")
    public void verifyInvalidLoginCredentials() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("abcid@gmail.com");
        login.enterPassword("invalidPwd");
        login.clickSignIn();
        Assert.assertEquals(login.verifyErrorMsg(),"Invalid username or password");

    }

    @Test(priority=2, description = "Ensure that the login page retains the entered username and password fields&#39; values after a failed login attempt.")
    public void validateLoginbutton(){
        LoginPage login = new LoginPage(driver);
        login.enterUsername("abcid@gmail.com");
        login.enterPassword("invalidPwd");
        login.clickSignIn();
        Assert.assertEquals(getTextValue(emailInput),"abcid@gmail.com");
        Assert.assertEquals(getTextValue(passwordInput),"invalidPwd");

        //Test that the 'Login' button is disabled when both username and password fields are empty.
        login.clearFieldValue(emailInput);
        login.clearFieldValue(passwordInput);
        Assert.assertFalse(loginButton.isEnabled(), "Login button is still enabled, after clearing the fields value.");

        //Confirm that the 'Login' button is enabled only when both username and password fields are filled with valid input.
        login.enterUsername("abcid@gmail.com");
        Assert.assertFalse(loginButton.isEnabled(), "Login button is still enabled, after entering the one field value.");
        login.enterPassword("invalidPwd");
        Assert.assertTrue(loginButton.isEnabled(), "Login button is still disabled, after entering the fields value.");

    }



}
