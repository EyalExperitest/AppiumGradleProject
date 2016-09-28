/**
 * Created by eyal.neumann on 9/26/2016.
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SimpleRun {

    private static AndroidDriver<WebElement> driver;
    final static String[] ERIBANK_PROP = {"com.experitest.ExperiBank.LoginActivity.2.apk", "com.experitest.ExperiBank",".LoginActivity"};


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/Apps/");
        File app = new File(appDir, "com.experitest.ExperiBank.LoginActivity.2.apk");
        //File app = new File(appDir, ERIBANK_PROP[0]);

        // To create an object of Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Name of mobile web browser to automate. It should be an empty string, as we are automation an app
        capabilities.setCapability("deviceName", "adb:OnePlus A0001");
        //capabilities.setCapability("platformVersion", "4.4.2");
        //capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", ERIBANK_PROP[1]);
        capabilities.setCapability("appActivity", ERIBANK_PROP[2]);


        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

        //driver.findElementByXPath("//*[@id='usernameTextField']").sendKeys("company");
        //driver.findElementByXPath("//*[@id='passwordTextField']").sendKeys("company");

        WebElement userNameTextField = driver.findElementById("com.experitest.ExperiBank:id/usernameTextField");
        userNameTextField.click();
        userNameTextField.sendKeys("company");

        WebElement passwordTextField = driver.findElementById("com.experitest.ExperiBank:id/passwordTextField");
        passwordTextField.click();
        passwordTextField.sendKeys("company");

        WebElement login = driver.findElementByXPath("//*[@text='Login']");
        login.click();

        Thread.sleep(2000);

        WebElement makePayment = driver.findElementByXPath("//*[@text='Make Payment']");
        makePayment.click();
        WebElement phoneTextField = driver.findElementByXPath("//*[@text='Phone']");
        WebElement nameTextField = driver.findElementByXPath("//*[@text='Name']");
        WebElement amountTextField = driver.findElementByXPath("//*[@text='Amount']");
        WebElement select = driver.findElementByXPath("//*[@text='Select']");
        WebElement sendPayment = driver.findElementByXPath("//*[@text='Send Payment']");

        phoneTextField.click();
        phoneTextField.sendKeys("089674569809");
        nameTextField.click();
        nameTextField.sendKeys("My Name");
        amountTextField.click();
        amountTextField.sendKeys("100");
        select.click();
        Thread.sleep(2000);
        WebElement india = driver.findElementByXPath("//*[@text='India']");
        india.click();
        Thread.sleep(2000);

        sendPayment.click();
        WebElement yes = driver.findElementByXPath("//*[@text='Yes']");
        Thread.sleep(2000);
        yes.click();







        Thread.sleep(10000);
        //driver.quit();

    }

}
