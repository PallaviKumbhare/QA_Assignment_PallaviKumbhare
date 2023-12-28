package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage{
    WebDriver driver;
    WebElement userIDInput_locator = driver.findElement(By.id("identifierId"));
    WebElement passwordInput_locator = driver.findElement(By.name("password"));
    WebElement logInButton_locator = driver.findElement(By.xpath("//button[@onclick=\"logIn()\"]"));
    WebElement logOutButton_locator = driver.findElement(By.xpath("xpath of logout button"));
    WebElement errorMessage_locator = driver.findElement(By.id("alertmsg"));

    WebElement emailInput = driver.findElement(By.id("inputFieldId"));
    WebElement passwordInput = driver.findElement(By.name("password"));
    WebElement loginButton = driver.findElement(By.id("loginButtonId"));



    public LoginPage(WebDriver driver) { // create a constructor and pass the driver instance
        this.driver=driver;
       // wait = new WebDriverWait(driver, 10);
    }


    public void enterUsername(String username) { //pass a parameter so we don't hardcode values in the object class.
            enterFieldValue(username, userIDInput_locator);
            System.out.println("Username is entered successfully."+ username);
    }

    public void enterPassword(String password) {
        enterFieldValue(password, passwordInput_locator);
        System.out.println("Password is entered successfully."+ password);
    }

    public void clickSignIn() {
        clickOnButton(logInButton_locator);
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public String verifyErrorMsg() {
        return getTextValue(errorMessage_locator);
    }

    public void clickLogOut() {
        clickOnButton(logOutButton_locator);
        System.out.println("Log out done successfully");
    }

    public void clearFieldValue(WebElement locator){
        locator.clear();
    }

}

