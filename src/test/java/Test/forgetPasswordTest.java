package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.forgetPasswordPage;

public class forgetPasswordTest extends BaseTest {

    WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password"));
    WebElement recoveryPassword = driver.findElement(By.id("button"));
    WebElement successMessage = driver.findElement(By.id("success-message"));
    WebElement newPasswordInput = driver.findElement(By.xpath("xpath of input box"));
    WebElement reEnterNewPassword =  driver.findElement(By.xpath("xpath of  second input box"));
    WebElement resetButton = driver.findElement(By.xpath("xpath of reset button"));
    WebElement logOutButton = driver.findElement(By.xpath("xpath of logout button"));

    @Test(priority=1)
    public void enterNewPassword(){
        LoginPage login = new LoginPage(driver);
        //Verify that clicking the 'Forgot Password' link redirects the user to the password recovery page.
        login.clickOnButton(forgotPasswordLink);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("password_recovery_page"));

        // Verify that a success message is displayed indicating that an email with password recovery instructions has been sent.
        forgetPasswordPage forget= new forgetPasswordPage(driver);
        forget.enterEmailId("validEmailId");
        forget.clickOnButton(recoveryPassword);
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed.");
        forget.enterFieldValue("newpassword123", newPasswordInput);
        forget.enterFieldValue("newpassword123", reEnterNewPassword);
        forget.clickOnButton(resetButton);
        forget.testForgotPwdLink();
        // Ensure that after successfully resetting the password, the user is redirected to the login page.
        String loginPageUrl = "your_login_page_url";
        String currentUrl1 = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl1, loginPageUrl);
        forget.clickOnButton(logOutButton);
    }

    @Test(priority=2)
    public void logInwithNewCredential(){
        LoginPage login = new LoginPage(driver);
        login.enterUsername("abc_id@gmail.com");
        login.enterPassword("new_password");
        login.clickSignIn();
        Assert.assertEquals(login.getHomePageTitle(),"welcome xyz");
        System.out.println("Log in done successfully");
        login.clickLogOut();
    }
}
