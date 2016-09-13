/**
 * Created by eyal.neumann on 9/13/2016.
 */

import api.appium.AppiumProcess;
import api.manual.STMProcess;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketOptions;

/**
 *
 */
public class AppiumOnManualTest {
    public static final String IP = "127.0.0.1";//"192.168.1.14";
    public static final String ID = "dcd3b654";
    public static final int PORT = 4723;
    public static final int BOOT_STRAP_PORT = 4751;
    private STMProcess stmProcess;
    private AppiumProcess appiumProcess;

    @Before
    public void setUp() throws Exception {
        stmProcess= new STMProcess();
        stmProcess.waitForLaunch();
        Thread.sleep(5000);
        int reserve = stmProcess.reserve(ID);
        System.out.println("Reserve Device with id :"+ID+" Result :"+ reserve);
        if(reserve!=0){
            throw new Exception("Failed to reserve device with id "+ID+" Returned value is "+reserve);
        }
        System.out.println("Starting New Appium process");

        appiumProcess = new AppiumProcess(PORT,BOOT_STRAP_PORT,ID);

    }

    @Test
    public void testAppiumOnManual() throws MalformedURLException, InterruptedException {
        System.out.println("Starting Test Proper");
        DesiredCapabilities capabilities = Basic.getDesiredCapabilitiesEriBank();
        Basic.basicEriBankTest(capabilities,IP,4723);
    }

    @After
    public void tearDown(){
        appiumProcess.destroy();
        stmProcess.destroy();
    }
}

