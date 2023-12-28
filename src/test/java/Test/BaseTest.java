package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

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
