/**
 * Created by eyal.neumann on 9/13/2016.
 */

import api.appium.AppiumProcess;
import api.manual.STMProcess;
import api.remotedebug.STRDProcess;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

/**
 *
 */
public class AppiumOnManualTest {
    public static final String APPIUM_IP = "127.0.0.1";//"192.168.1.14";
    public static final String ID = "015d28568f541e0f";
    public static final int APPIUM_PORT = 4723;
    public static final int BOOT_STRAP_PORT = 4751;
    public static final String CLOUD_IP = "192.168.1.210";
    public static final String CLOUD_PORT = "80";
    public static final String PROJECT_NAME = "AppiumTest";
    public static final String USER_NAME = "appiumuser";
    public static final String PASSWORD = "Experitest2012";
    private STMProcess stmProcess;
    private STRDProcess strdProcess;

    private AppiumProcess appiumProcess;

    @Before
    public void setUp() throws Exception {
        /*
        stmProcess= new STMProcess(false);
        stmProcess.waitForLaunch();*/
        strdProcess =new STRDProcess(true,ID, CLOUD_IP, CLOUD_PORT, PROJECT_NAME, USER_NAME, PASSWORD);
        Thread.sleep(5000);
        //int reserve = stmProcess.reserve(ID);
        // System.out.println("Reserve Device with id :"+ID+" Result :"+ reserve);
/*        if(reserve!=0){
            throw new Exception("Failed to reserve device with id "+ID+" Returned value is "+reserve);
        }*/
        System.out.println("Starting New Appium process");

        appiumProcess = new AppiumProcess(APPIUM_PORT,BOOT_STRAP_PORT,ID,15000);

    }

    @Test
    public void testAppiumOnManual() throws MalformedURLException, InterruptedException {
        System.out.println("Starting Test Proper");
        DesiredCapabilities capabilities = Basic.getDesiredCapabilitiesEriBank();
        Basic.basicEriBankTest(capabilities, APPIUM_IP,APPIUM_PORT);
    }

    @After
    public void tearDown(){
        appiumProcess.destroy();
        strdProcess.destroy();
        System.out.println("Done");

//        stmProcess.destroy();
    }
}

