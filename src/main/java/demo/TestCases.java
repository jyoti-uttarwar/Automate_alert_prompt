package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
// import org.openqa.selenium.Keys;
// import org.openqa.selenium.remote.BrowserType;
// import org.openqa.selenium.remote.DesiredCapabilities;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.ui.Select;
///


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void TC_AutomateAlertPrompt() throws InterruptedException{
        System.out.println("Start Test case: TC_AutomateAlertPrompt");
        String strAlert = "Jyoti";
        // Load the URL  "https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt"
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
        // Switch to frame Using Locator "ID" "iframeResult"
        driver.switchTo().frame("iframeResult");
        // Locate & click "Try It" button Using Locator "XPath" "//button[contains(text(),'Try')]"
        WebElement eleTryButton = driver.findElement(By.xpath("//button[contains(text(),'Try')]"));
        eleTryButton.click();
        // Switch to Alert
        Alert alert = driver.switchTo().alert();
        // Get the Alert text & Print it
        String eleAlertText = alert.getText();
        System.out.println("Alert text : " + eleAlertText);
        // Enter your name in the Alert  "Jyoti"
        alert.sendKeys(strAlert);
        // Click on Alert "Ok"
        alert.accept();
        // Verify the entered string printed ot not
        String eleMessage = driver.findElement(By.id("demo")).getText();
        if(eleMessage.contains(strAlert))
            System.out.println("Name printed");
        else
            System.out.println("Name does not printed");
        // Switch to main window
        driver.switchTo().defaultContent();
        System.out.println("end Test case: TC_AutomateAlertPrompt");
    }


}
