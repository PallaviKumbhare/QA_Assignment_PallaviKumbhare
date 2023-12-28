package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class forgetPasswordPage extends BasePage{
    public static WebDriver driver;
    String url = "https://gmail.com";

    @BeforeMethod
    public void setup(ITestContext context) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // This suppresses the Severe Timed out receiving messages
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        context.setAttribute("WebDriver", driver);
    }

    @AfterClass(alwaysRun=true)
    public void tearDown() {
        driver.quit();
    }

    //    WebDriver driver;
    WebDriverWait wait;
    WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password"));
    WebElement emailInput1 = driver.findElement(By.id("inputFieldId"));
    WebElement recoveryPassword = driver.findElement(By.id("button"));
    WebElement successMessage = driver.findElement(By.id("success-message"));
    WebElement newPasswordInput = driver.findElement(By.xpath("xpath of input box"));
    WebElement reEnterNewPassword =  driver.findElement(By.xpath("xpath of  second input box"));
    WebElement resetButton = driver.findElement(By.xpath("xpath of reset button"));
    WebElement logOutButton = driver.findElement(By.xpath("xpath of logout button"));
    WebElement emailIDInput_locator = driver.findElement(By.id("identifierId"));


    public forgetPasswordPage(WebDriver driver) { // create a constructor and pass the driver instance
        forgetPasswordPage.driver =driver;
         wait = new WebDriverWait(driver, 10);
    }


    public void testForgotPwdLink(){
        //Verify that clicking the 'Forgot Password' link redirects the user to the password recovery page.
        forgotPasswordLink.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("password_recovery_page"));

        // Verify that a success message is displayed indicating that an email with password recovery instructions has been sent.

        emailInput1.sendKeys("validEmailId");
        recoveryPassword.click();
        Assert.assertTrue(successMessage.isDisplayed());

        //Retrieve the recovery instructions and follow the specified steps to reset the password.
        newPasswordInput.sendKeys("newpassword123");
        reEnterNewPassword.sendKeys("newpassword123");
        resetButton.click();

        // Ensure that after successfully resetting the password, the user is redirected to the login page.
        String loginPageUrl = "your_login_page_url"; // Replace with the actual login page URL
        String currentUrl1 = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl1, loginPageUrl);
        logOutButton.click();

    }

    public void enterEmailId(String email) { //pass a parameter so we don't hardcode values in the object class.
        enterFieldValue(email, emailIDInput_locator);
        System.out.println("Username is entered successfully."+ email);
    }

}
