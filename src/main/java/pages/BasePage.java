package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class BasePage {
   // to do

    /**
     * This method is used to enter the username
     * @param fieldValue
     */
    public void enterFieldValue(String fieldValue, WebElement locator) { //pass a parameter so we don't hardcode values in the object class.
        try{
            locator.sendKeys(fieldValue);
            System.out.println("Field value is entered successfully."+ fieldValue);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void clickOnButton(WebElement locator){
        try {
            locator.click();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getTextValue(WebElement locator) {
        String textValue = null;
        try {
            textValue = locator.getText();
            System.out.println(textValue);

        } catch (Exception e) {
            System.out.println(e);
        }
        return textValue;
    }
}
