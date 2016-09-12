import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Basic {

    public static final String DEVICE_NAME = "GT-I9301I";
    public static final int ITERATIONS =2 ;
    private static AndroidDriver<WebElement> driver;
    final static String[] ERIBANK_PROP = {"com.experitest.ExperiBank.LoginActivity.2.apk", "com.experitest.ExperiBank",".LoginActivity"};


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        System.out.println("here");
        File classpathRoot = new File(System.getProperty("user.dir"));
        System.out.println("classpathRoot ="+classpathRoot.getAbsolutePath());

        File appDir = new File(classpathRoot, "/Apps/");
        System.out.println("appDir ="+appDir.getAbsolutePath());

        File app = new File(appDir, "com.experitest.ExperiBank.LoginActivity.2.apk");
        System.out.println("app ="+app.getAbsolutePath());



        //File app = new File(appDir, ERIBANK_PROP[0]);

        // To create an object of Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Name of mobile web browser to automate. It should be an empty string, as we are automation an app
        capabilities.setCapability("deviceName", DEVICE_NAME);
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
        WebElement passwordTextField = driver.findElementById("com.experitest.ExperiBank:id/passwordTextField");
        WebElement loginButton = driver.findElementByXPath("//*[@text='Login']");

        userNameTextField.sendKeys("company");
        passwordTextField.sendKeys("company");
        loginButton.click();
        Thread.sleep(2000);

        WebElement logoutButton = driver.findElementByXPath("//*[@text='Logout']");
        logoutButton.click();

        for (int i = 0; i< ITERATIONS; i++){
            userNameTextField.sendKeys("company");
            passwordTextField.sendKeys("company");
            loginButton.click();
            Thread.sleep(2000);
            logoutButton.click();

        }



        Thread.sleep(10000);
        //driver.quit();

    }


}
