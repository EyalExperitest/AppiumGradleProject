package api.appium;

import utils.ProcessReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by eyal.neumann on 9/12/2016.
 */
public class AppiumProcess extends Process {
    public static final String APPIUM_JS = "\"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js\"";
    private Process process;
    private ProcessReader processReader;
    @Override
    public OutputStream getOutputStream() {
        return process.getOutputStream();
    }
    public AppiumProcess(int port, int bootStrapPort, String id) throws IOException, InterruptedException {
//        String command = "node " + APPIUM_JS + " --port " + port + " --bootstrap-port " + bootStrapPort + " --udid " + id;
//        System.out.println("Command : "+command);

        ProcessBuilder pb = new ProcessBuilder()
                .redirectErrorStream(true)
                //.directory(new File(""))
                .command(
                    "node", APPIUM_JS, "--port", ""+port, "--bootstrap-port", ""+bootStrapPort, "--udid", id
                );

//        this.process =  Runtime.getRuntime().exec(command);
        process = pb.start();
        System.out.println("Command : Launched");
        processReader =new ProcessReader(process,"Appium :"+port);
        Thread thread =new Thread(processReader);
        thread.start();
        Thread.sleep(5000);
    }

    @Override
    public InputStream getInputStream() {
        return process.getInputStream();
    }

    @Override
    public InputStream getErrorStream() {
        return process.getErrorStream();
    }

    @Override
    public int waitFor() throws InterruptedException {
        return process.waitFor();
    }



    @Override
    public int exitValue() {
        return process.exitValue();
    }

    @Override
    public void destroy() {
        process.destroy();
    }

    @Override
    public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException {
        return this.process.waitFor(timeout, unit);
    }

    @Override
    public Process destroyForcibly() {
        return this.process.destroyForcibly();
    }

    @Override
    public boolean isAlive() {
        return this.process.isAlive();
    }
}
