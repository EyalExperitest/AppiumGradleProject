import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.experitest.appium.SeeTestAndroidDriver;


public class BasicChrome {

    public static final String DEVICE_NAME = "SM-T813";
    public static final int ITERATIONS =2 ;
    public static final String IP = "127.0.0.1";//"192.168.1.14";
    public static final String BROSER_NAME = "Chrome";
    private static AndroidDriver<AndroidElement> driver;
    final static String[] ERIBANK_PROP = {"com.experitest.ExperiBank.LoginActivity.2.apk", "com.experitest.ExperiBank",".LoginActivity"};
    final static String[] CHROME_PROP = {"", "com.android.chrome",""};
    private static String adress;


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
        driver = new AndroidDriver<>(new URL("http://"+ ip + ":" + port + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        adress = "http://192.168.4.85:8060/html-tests/WebPageTests/WebPageTests.html";
        URL url= new URL (adress);

        driver.get(adress+"/");


    }

    public static DesiredCapabilities getDesiredCapabilitiesEriBank() {
        DesiredCapabilities capabilities = new DesiredCapabilities();


        // Name of mobile web browser to automate. It should be an empty string, as we are automation an app
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("browserName", BROSER_NAME);
        //capabilities.setCapability("autoWebview", true);

        //capabilities.setCapability("platformVersion", "4.4.2");
        //capabilities.setCapability("platformName", "Android");
        //autoLaunch
        capabilities.setCapability("autoLaunch", "true");

        // capabilities.setCapability("app", app.getAbsolutePath());
        //capabilities.setCapability("appPackage", ERIBANK_PROP[1]);
        //.setCapability("appActivity", ERIBANK_PROP[2]);
        capabilities.setCapability("appPackage","com.android.chrome" );
        capabilities.setCapability("appActivity","com.google.android.apps.chrome.Main");

        return capabilities;
    }


}
