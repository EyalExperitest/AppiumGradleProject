import com.experitest.appium.SeeTestAndroidDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasicSeeTestClient {

    public static final String DEVICE_NAME = "adb:samsung SM-G920F";
    public static final int ITERATIONS =2 ;
    public static final String IP = "127.0.0.1";//"192.168.1.14";
    private static final String UDID ="041604eb94801702";
    //private static AndroidDriver<AndroidElement> driver;
    private static  SeeTestAndroidDriver<AndroidElement> driver;

    final static String[] ERIBANK_PROP = {"com.experitest.ExperiBank.LoginActivity.2.apk", "com.experitest.ExperiBank",".LoginActivity"};


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //findAppPath();



        //File app = new File(appDir, ERIBANK_PROP[0]);

        // To create an object of Desired Capabilities

        DesiredCapabilities capabilities = getDesiredCapabilitiesEriBank();
        basicEriBankTest(capabilities,IP,4723);



        Thread.sleep(10000);
        //driver.quit();

    }

    public static void findAppPath() {
        System.out.println("here");
        File classpathRoot = new File(System.getProperty("user.dir"));
        System.out.println("classpathRoot ="+classpathRoot.getAbsolutePath());

        File appDir = new File(classpathRoot, "/Apps/");
        System.out.println("appDir ="+appDir.getAbsolutePath());

        File app = new File(appDir, "com.experitest.ExperiBank.LoginActivity.2.apk");
        System.out.println("app ="+app.getAbsolutePath());
        System.out.println("");
    }

    public static void basicEriBankTest(DesiredCapabilities capabilities,String ip, int port) throws MalformedURLException, InterruptedException {
        //driver = new AndroidDriver<>(new URL("http://"+ ip + ":" + port + "/wd/hub"), capabilities);
        driver = new SeeTestAndroidDriver<>(new URL("http://localhost:8889"), capabilities);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);






        //driver.findElementByXPath("//*[@id='usernameTextField']").sendKeys("company");
        //driver.findElementByXPath("//*[@id='passwordTextField']").sendKeys("company");



        AndroidElement userNameTextField = driver.findElementById("com.experitest.ExperiBank:id/usernameTextField");

        AndroidElement passwordTextField = driver.findElementById("com.experitest.ExperiBank:id/passwordTextField");

        AndroidElement loginButton = driver.findElementByXPath("//*[@text='Login']");

        userNameTextField.sendKeys("company");

        passwordTextField.sendKeys("company");

        loginButton.click();
        Thread.sleep(2000);
        AndroidElement logoutButton = driver.findElementByXPath("//*[@text='Logout']");
        logoutButton.click();
        System.out.println("Waiting For Element That is not there");
        driver.findElementByXPath("//*[@text='NOP NOP']");
/*
        for (int i = 0; i< ITERATIONS; i++){
            userNameTextField.sendKeys("company");
            passwordTextField.sendKeys("company");
            loginButton.click();
            Thread.sleep(2000);
            logoutButton.click();

        }*/
    }

    public static DesiredCapabilities getDesiredCapabilitiesEriBank() {
        DesiredCapabilities capabilities = new DesiredCapabilities();


        // Name of mobile web browser to automate. It should be an empty string, as we are automation an app
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("udid", UDID);

        //capabilities.setCapability("platformVersion", "4.4.2");
        //capabilities.setCapability("platformName", "Android");
        //autoLaunch
        capabilities.setCapability("autoLaunch", "true");

        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", ERIBANK_PROP[1]);
        capabilities.setCapability("appActivity", ERIBANK_PROP[2]);
        return capabilities;
    }


}
