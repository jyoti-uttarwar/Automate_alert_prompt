/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo;
import java.io.IOException;
import java.net.MalformedURLException;


public class App {
    public void getGreeting() throws InterruptedException, MalformedURLException, IOException {
        TestCases tests = new TestCases(); // Initialize your test class

        // // INTV-1/Session-7/2/Activity 7: Automate_nested_frames_text
        // tests.TC_AutomateNestedFramesText();

        // // INTV-1/Session-7/3/Automate_Alert_Prompt
        // tests.TC_AutomateAlertPrompt();

        // INTV-1/Session-7/5/Activity 10: Automate_window_handle
        tests.TC_AutomateWindowHandle();

        //END Tests


        tests.endTest(); // End your test by clearning connections and closing browser
    }
    
    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException{
        new App().getGreeting();
    }
}
