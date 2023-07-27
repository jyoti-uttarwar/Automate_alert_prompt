package demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    
    // INTV-1/Session-7/3/Automate_Alert_Prompt
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

    // INTV-1/Session-7/2/Activity 7: Automate_nested_frames_text
    public  void TC_AutomateNestedFramesText() throws InterruptedException{
        System.out.println("Start Test case: TC_AutomateNestedFramesText");
        // Load the URL  "https://the-internet.herokuapp.com/nested_frames"
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        // Switch to top frame
        driver.switchTo().frame("frame-top");
        // switch to left frame By Name "frame-left"
        //get the Text & print it
        driver.switchTo().frame("frame-left");
        String eleLeftText = driver.findElement(By.xpath("//body")).getText();
        System.out.println("Left Frame Text : " + eleLeftText);
        // Switch back to parent frame
        driver.switchTo().parentFrame();
        // Switch to Middle frame by name "frame-middle"
        //get the Text & print it
        driver.switchTo().frame("frame-middle");
        String eleMiddleText = driver.findElement(By.xpath("//body")).getText();
        System.out.println("Middle Frame Text : " + eleMiddleText);
        // Switch back to parent frame
        driver.switchTo().parentFrame();
        // Switch to Middle frame by name "frame-right"
        //get the Text & print it
        driver.switchTo().frame("frame-right");
        String eleRightText = driver.findElement(By.xpath("//body")).getText();
        System.out.println("Right Frame Text : " + eleRightText);
        // Switch to main page/ DOM
        driver.switchTo().defaultContent();
        // Switch to bottom frame by Name "frame-bottom"
        //get the Text & print it
        driver.switchTo().frame("frame-bottom");
        String eleBottomText = driver.findElement(By.xpath("//body")).getText();
        System.out.println("Bottom Frame Text : " + eleBottomText);
        // Switch to main page/ DOM
        driver.switchTo().defaultContent();
        System.out.println("end Test case: TC_AutomateNestedFramesText");
    }

    // INTV-1/Session-7/5/Activity 10: Automate_window_handle
    public  void TC_AutomateWindowHandle() throws InterruptedException, IOException{
        System.out.println("Start Test case: TC_AutomateWindowHandle");
        // Load the URL "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open"
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        // Switch to frame Using Locator "ID" "iframeResult"
        driver.switchTo().frame("iframeResult");
        // Locate & click "Try It" button Using Locator "XPath" "//button[contains(text(),'Try')]"
        WebElement eleTryButton = driver.findElement(By.xpath("//button[contains(text(),'Try')]"));
        eleTryButton.click();
        //Get handles of the windows
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for(String currentWindow : allWindows)
        {
           if(!mainWindow.equalsIgnoreCase(currentWindow))
           {
                //Switch to child window
                driver.switchTo().window(currentWindow);  
                // print the URL of new window              
                System.out.println("Newly opened window URL : "+ driver.getCurrentUrl());
                //Print the Title of the new window
                System.out.println("Newly opened window Title : "+ driver.getTitle());
                
                //Take screenshot
                TakesScreenshot srcshot = ((TakesScreenshot)driver);
                File srcFile = srcshot.getScreenshotAs(OutputType.FILE);

                //If directory is not present, create a directory to save the screenshot
                String dirPath = System.getProperty("user.dir") + "\\ScreenShot\\";
                File file = new File(dirPath);
                if(!file.exists())
                    file.mkdirs();

                //Generate unique Filename using timestamp to save screenshot 
                String timestamp = new SimpleDateFormat("yyyyMMdd__hh_mm_ss").format(new Date());
                String fileName = "ScreenShot_" + timestamp + ".png"; 
                
                //Generate a file to save screenshot at defined directory
                File destFile = new File(dirPath + fileName);

                //Copy the screenshot from source file to destination file
                FileUtils.copyFile(srcFile,destFile);
                //Close the newly opened window
                driver.close();
                //Switch to main window
                driver.switchTo().window(mainWindow);
            }
        }        
        System.out.println("end Test case: TC_AutomateWindowHandle");
    }

}
